package com.yc.自定义实现jdk动态代理;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/*
    用来生成代理类字节码的工具类
    Proxy.newProxyInstance()
    YcJdkProxy.newProxyInstance()
 */
public class YcJdkProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(YcClassLoader classLoader,
                                          Class<?>[] interfaces,
                                          YcInvocationHandler h) {
        try{
            //1.动态生成源代码.java文件
            String src = generateSrc(interfaces);
            System.out.println( src );

            //2.Java文件输出磁盘
            String filePath = YcJdkProxy.class.getResource("").getPath();
            System.out.println( filePath );

            filePath = URLDecoder.decode(filePath, "UTF-8");
            File f  = new File(filePath + "/$Proxy0.java");

            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //3.把生成的.java文件编译成class文件
            //获取系统编译器  javac
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            //获取java文件管理器
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(f);
            //启动编译任务
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            //4.编译生成的.class 文件加载到JVM中来
            classLoader = new YcClassLoader();
            Class proxyClass = classLoader.loadClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(YcInvocationHandler.class);
            f.delete();
            //5.返回字节码重组以后的新的代理对象
            return c.newInstance( h );
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
        针对这个接口拼接了一个java实现类  对在这个类的抽象方法做实现
          1.首先利用ribbon 访问eureka服务得到服务器列表  自动利用负载均衡取一个服务
          2.将服务名转为地址:  http://ip:端口/服务名/product/get/{id}
          3.利用网络访问框架 HttpClient ( OKhttp )
     */
    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        //根据接口的包名来生成
        sb.append("package com.yc.自定义实现jdk动态代理;"+ln );

        sb.append("import java.lang.reflect.*;"+ln );
        sb.append("public class $Proxy0 implements "+interfaces[0].getName()+" {" + ln );
            sb.append("YcInvocationHandler h;"+ ln );
            sb.append("public $Proxy0(YcInvocationHandler h) { "+ ln );
                sb.append("this.h=h;");
            sb.append("}"+ ln );
            for (Method m: interfaces[0].getMethods()){
                Class<?> [] params = m.getParameterTypes();

                StringBuffer paramNames = new StringBuffer(); //参数类型 参数名
                StringBuffer paramValues = new StringBuffer();//参数值
                StringBuffer paramClasses = new StringBuffer();

                for (int i = 0; i < params.length; i++) {
                    Class clazz = params[i];
                    String type = clazz.getName();//java.lang.String.class
                    String paramName = toLowerFirstCase( clazz.getSimpleName() );//string 即参数名
                    paramNames.append(type+ " "+ paramName+i);//java.lang.String string //参数类型 参数名
                    paramValues.append(paramName+i);
                    paramClasses.append(clazz.getName() + ".class");
                    if (i >= 0 && i< params.length - 1){
                        paramNames.append(",");//参数类型 参数名
                        paramValues.append(",");//参数值
                        paramClasses.append(",");//这个字符串通常是类的全限定名，例如 "java.lang.String"
                    }
                }

                sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln );
                    sb.append("try { " + ln );
                        sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\", new Class[]{"
                                + paramClasses.toString() + "});" + ln );
                        sb.append( (hasReturnValue(m.getReturnType())? "return " : ""));
                            sb.append(getCaseCode( "this.h.invoke(this, m, new Object[]{" + paramValues + "})",m.getReturnType()) + ";"+ln);
                    sb.append("} catch (Error _ex) { }");
                    sb.append("catch (Throwable e) { "+ ln);
                    sb.append("throw new UndeclaredThrowableException(e);" + ln );
                    sb.append("}"+ ln );
                    sb.append(getReturnEmptyCode(m.getReturnType()));
                sb.append("}"+ ln );
            }
        sb.append("}"+ ln );
        return sb.toString();
    }

    private static Map<Class,Class> mappings = new HashMap<>();
    static{
        mappings.put(int.class, Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnType) {
        if (mappings.containsKey(returnType)){
            return "return 0;";
        }else if ((returnType == void.class)){
            return "";
        }else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code, Class<?> returnType) {
        if ( mappings.containsKey(returnType)){
            return "(("+mappings.get(returnType).getName()+")"+code+")." + returnType.getSimpleName() + "Value()";
        }
        return code;
    }

    /*
        判断是否有返回值
     */
    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz!= void.class;
    }

    /*
        ASCII码中，大写字母 A 的值是65，小写字母 a 的值是97。因此，增加32可以将大写字母转换为相应的小写字母
        将输入字符串的首字母转换为小写字母
     */
    private static String toLowerFirstCase(String src) {
        char [] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
