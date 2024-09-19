package com.yc.project2.org.springframework.context;

import com.yc.project2.org.springframework.annotation.*;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//spring 基于注解配置的上下文
public class YcAnnotationConfigApplicationContext implements YcApplicationContext{
    // 存放bean对象的容器
    private Map<String,Object> beanMap = new ConcurrentHashMap<>();
    // 存放bean的定义信息  类的全路径  懒加载  单例  原型  作用域 等
    private Map<String,YcBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	public YcAnnotationConfigApplicationContext(Class<?> ... configClass) {
		for ( Class cls : configClass){
            //解析配置类中的@Bean注解
            parseBeanAnnotation(  cls );
            //解析配置类中的@ComponentScan注解中的value，得到要扫描的包
            //待扫描的包的路径不为空，则通过扫描
            if ( cls.isAnnotationPresent(YcComponentScan.class)){
                YcComponentScan ycComponentScan = (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                String[] basePackages = ycComponentScan.value();
                if (basePackages == null || basePackages.length <= 0){
                    basePackages = ycComponentScan.basePackages();
                }
                if (basePackages == null || basePackages.length <= 0){
                    basePackages = new String[1];
                    //默认扫描当前类所在的包
                    basePackages[0] = cls.getPackage().getName();
                }
                for (String basePackage : basePackages){
                    System.out.println( "待扫描的包:"+basePackage);
                }
                //递归扫描这个配置类中  basePackages指定的包及子包中的所有的带托管的类
                recursiveLoadBeanDefinition(   basePackages   );
            }
            //读取类的信息  创建BeanDefinition对象1
            try{
                //再 ioc
                createBean();
                //再 di
                doDi();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
	}

    private void doDi() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        //循环 beanDefinitionMap  创建bean
        for (Map.Entry<String,Object> entry : this.beanMap.entrySet()) {
            //获取这个beanId对应的类的对象
            String beanId = entry.getKey();
            Object beanObject = entry.getValue();
            //查看这个对象的属性是否有  di注解
            Field[] fields = beanObject.getClass().getDeclaredFields();
            for (Field f : fields){
                if (  f.isAnnotationPresent(YcResource.class)){
                    //取出  YcResource注解中的value 这个值是 beanId 代表 要装配的bean的id
                    String toDiBeanId =  f.getAnnotation(   YcResource.class ).value();
                    Object obj =  getToDiObject(  toDiBeanId  );
                    //设置  此 field为 可访问的  把私有的设置为公有的
                    f.setAccessible(true);
                    //取出YcResource注解中的value 这个值是 beanId 代表 要装配的bean的id
                    //反射注入 对象
                    f.set( beanObject , obj ); //this.uerDao = userDao;

                }else if ( f.isAnnotationPresent( YcAutowired.class )){
                    //TODO 1.  1.取出属性的类型 2.先从beanMap中是否有这种类型的bean 有 则返回 没有则看beanDefinitionMap中是否有这个类的对象 有则创建   如果没有则报错

                }
            }

        }
    }
    private Object getToDiObject(String toDiBeanId) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //如果beanMap中有  则直接返回
        if ( beanMap.containsKey( toDiBeanId )){
            return beanMap.get( toDiBeanId );
        }
        //判断beanDefinitionMap中是否有这个beanId的类的对象 有则创建   如果没有则报错
        if ( this.beanDefinitionMap.containsKey( toDiBeanId ) == false ){
            throw new RuntimeException( "找不到要注入的类:"+toDiBeanId );
        }
        YcBeanDefinition bd = this.beanDefinitionMap.get( toDiBeanId );
        //懒加载的话  第一次使用的时候才创建对象
        if (  bd.isLazy() ){
            String classpath = bd.getClassInfo();
            Object beanObject = Class.forName( classpath ).newInstance();
            beanMap.put( toDiBeanId, beanObject );
            return beanObject;
        }
        //原型模式  每次都要创建一个新的对象 所以存到beanMap中
        if ( bd.getScope().equalsIgnoreCase("prototype")){
            String classpath = bd.getClassInfo();
            Object beanObj =  Class.forName( classpath ).newInstance( );
            //因为prototype 模式 每次都要创建一个新的对象 所以没存到beanMap中
            return beanObj;
        }
        return null;
    }

    /*
       IOC
    */
    private void createBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //循环 beanDefinitionMap  创建bean
        for (Map.Entry<String, YcBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            //获取这个beanId对应的类的对象
            String beanId = entry.getKey();
            YcBeanDefinition bd = entry.getValue();
            //判断bd中是否有懒加载的注解 //懒加载 和 原型模式  不创建 beanMap中没有这个beanId对应的bean对象
            if (!bd.isLazy() && !bd.getScope().equalsIgnoreCase("prototype")) {
                //创建bean
                String classInfo = bd.getClassInfo();
                //创建 bean的方式有多种 这里只用到了通过无参构造 方法创建的方式
                Object beanObj = Class.forName(classInfo).newInstance();
                beanMap.put(beanId, beanObj);
            }
        }
    }


    /*
        递归 扫描 basePackages 中的路径  加载所有的托管类信息到 BeanDefinitionMap中
        basePackages={ "com.yc.project2","com.yc.project1" }
     */
    private void recursiveLoadBeanDefinition(String[] basePackages) {
        //循环此数组
        for (String basePackageName : basePackages){
            //加载包下.class文件  - > 流 -> 文件路径 -> com/yc/project2/Apple.class
            //替换包路径中的.-> /
            String packagePath = basePackageName.replaceAll("\\.", "/");
            try {
                //利用jvm加载 包中的class文件
                //线程上下文加载器
                Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources( packagePath );
                while (files.hasMoreElements()) {
                    URL url = files.nextElement();
                    System.out.println( "正在扫描的报下的文件路径为:  "+url.getFile()); //  com/yc/project2/Apple.class
                    //扫描这个包下所有的文件，筛选出  .class
                    findPackageClasses( url.getFile(), basePackageName); // basePackageName = com.yc.project2
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
        packagePath:代扫描的包的绝对路径  /c:/xxx/xxx/com/yc/
        basePackageName:包路径  com.yc
     */
    private void findPackageClasses(String packagePath, String packageName){
        if (  packagePath.startsWith("/")){
            packagePath = packagePath.substring(1);
        }
        //取这个packagePath 路径下所有的文件( 包括子包 )
        File file = new File( packagePath );
        //取所有的  .class文件 或是 是目录
        File[] classFiles  = file.listFiles( new FileFilter(){
            @Override
            public boolean accept(File pathname) {
                if ( pathname.getName().endsWith(".class") || pathname.isDirectory()){
                    return true;
                }else {
                    return false;
                }
            }
        });
        System.out.println("classFiles:"+ classFiles );
        //循环 classFiles 如果是 .class文件 则加载   如果是目录 则递归扫描
        if ( classFiles!= null && classFiles.length > 0){
            for ( File f : classFiles) {
                if (f.isDirectory()) {
                    // 目录 则递归扫描
                    findPackageClasses( f.getAbsolutePath(), packageName + "." + f.getName());
                } else {
                    //.class 文件 则加载
                    URLClassLoader uc = new URLClassLoader( new URL[]{} );
                    try {
                        //                    com.yc.di                 .class
                        Class cls = uc.loadClass(packageName + "." + f.getName().replace(".class", ""));
                        //判断这个cls类上是否 有 IOC 注解  如果有，则创建一个BeanDefinition对象 存到BeanDefinitionMap中
                        if (cls.isAnnotationPresent(YcComponent.class) ||
                                cls.isAnnotationPresent(YcController.class) ||
                                cls.isAnnotationPresent(YcRepository.class) ||
                                cls.isAnnotationPresent(YcService.class)
                        ) {
                            YcBeanDefinition bd = new YcBeanDefinition();
                            if (cls.isAnnotationPresent(YcLazy.class)) {
                                YcLazy lazy = (YcLazy) cls.getAnnotation(YcLazy.class);
                                boolean b = lazy.value();
                                bd.setLazy(b);
                            }
                            if (cls.isAnnotationPresent(YcScope.class)) {
                                YcScope scope = (YcScope) cls.getAnnotation(YcScope.class);
                                String scopeName = scope.value();
                                bd.setScope(scopeName);
                            }
                            bd.setClassInfo( packageName + "." + f.getName().replace(".class", ""));
                            //获取beanId
                            String beanId = getBeanId(cls);
                            //将这个BeanDefinition对象存到BeanDefinitionMap中
                            beanDefinitionMap.put(beanId,bd);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }


    }

    private String getBeanId(Class cls){
        String beanid = "";
        YcComponent ycComponent = (YcComponent) cls.getAnnotation(YcComponent.class);
        YcRepository ycRepository = (YcRepository) cls.getAnnotation(YcRepository.class);
        YcService ycService = (YcService) cls.getAnnotation(YcService.class);
        YcController ycController = (YcController) cls.getAnnotation(YcController.class);
        if ( ycComponent!= null){
            beanid = ycComponent.value();
        }else if ( ycRepository!= null){
            beanid = ycRepository.value();
        }else if ( ycService!= null){
            beanid = ycService.value();
        }else if ( ycController!= null){
            beanid = ycController.value();
        }
        //如果按value为空 则用类名
        if ( "".equalsIgnoreCase(beanid) || beanid == null){
            String typeName = cls.getSimpleName();
            beanid = typeName.substring(0,1).toLowerCase() + typeName.substring(1);
        }
        return beanid;
    }
    /*
        在cls配置类中找@Bean修饰的方法  托管对应的类的对象
     */
    private void parseBeanAnnotation(Class cls){
       try{
           //将类的信息  创建BeanDefinition对象
           Object obj = cls.newInstance();
           String clzsName = cls.getSimpleName();
           YcBeanDefinition bd = new YcBeanDefinition();
           bd.setLazy(false);
           bd.setClassInfo( cls.getName() );
           String beanid = clzsName.substring(0,1).toLowerCase()+clzsName.substring(1);
           beanDefinitionMap.put( beanid ,  bd );
           beanMap.put( beanid , obj  );

           //在这个配置类  cls中查找所有@YcBean注解 的方法  解析它们 ，托管对应的类的对象
           Method[] methods = cls.getDeclaredMethods();
           for ( Method m : methods){
               if (m.isAnnotationPresent(YcBean.class)){
                   YcBean ycBean = m.getAnnotation(YcBean.class);
                   String YcBeanId = ycBean.value();
                   if ( "".equals( YcBeanId )){
                       YcBeanId = m.getName();
                   }
                   //激活方法
                   Object o =  m.invoke( obj , null ); //o即apple对象
                   this.beanMap.put( YcBeanId, o );

                   YcBeanDefinition bd1 = new YcBeanDefinition();
                   bd1.setLazy(false);
                   bd.setScope("singleton");
                   bd1.setClassInfo( o.getClass().getName() ); //Apple类的全路径
                   this.beanDefinitionMap.put( YcBeanId,  bd1 );
               }
           }

       }catch (Exception e){
           e.printStackTrace();
       }

        //再ioc

        //再di

    }

    @Override
    public Object getBean(String beanid) {
        YcBeanDefinition bd = beanDefinitionMap.get( beanid );
        if ( bd == null){
            throw new RuntimeException( "没有找到这个beanid对应的对象" );
        }
        String scope = bd.getScope();
        if ( "prototype".equalsIgnoreCase(scope)){
            Object obj = null;
            try{
                obj = Class.forName(bd.getClassInfo()).newInstance();
                this.beanMap.put(beanid, obj);
                doDi();
                this.beanMap.remove(beanid, obj);
                return obj;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        if ( beanMap.containsKey( beanid)){
            return beanMap.get( beanid );
        }
        if ( bd.isLazy() ){
            Object obj = null;
            try{
                obj = Class.forName( bd.getClassInfo() ).newInstance();
                this.beanMap.put( beanid, obj );
                return obj;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


}
