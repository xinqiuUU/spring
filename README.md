# spring框架的学习

## 一.spring中IOC、DI的学习:

### 控制反转（IoC）

**控制反转**（Inversion of Control, IoC）是一种设计原则，它将对象的创建和依赖关系的管理从应用程序代码中移到外部容器中。通过这种方式，应用程序不再直接控制对象的创建和生命周期，而是由容器来管理。

#### IoC的实现方式

1. **依赖查找（Dependency Lookup）**：
   - 对象通过容器的API主动查找和获取所需的依赖。
   - 这种方式虽然降低了对象间的耦合，但仍然需要使用容器的API。
2. **依赖注入（Dependency Injection, DI）**：
   - 容器在运行时将对象的依赖关系注入到对象中。
   - 这种方式更加灵活，完全由容器管理依赖关系，避免了对象主动查找依赖。

### 依赖注入（DI）

**依赖注入**是IoC的一种具体实现方式。它通过将对象的依赖关系注入到对象中，使得对象不需要自己创建或查找依赖。Spring框架主要提供了以下几种依赖注入方式：

1. **构造函数注入**：
   - 通过构造函数将依赖传递给对象。
2. **Setter方法注入**：
   - 通过Setter方法将依赖注入到对象中。
3. **字段注入**：
   - 直接在字段上使用注解进行注入。



## 二.spring中基础注解的学习:

- `@Autowired`：自动注入依赖对象。
- `@Bean`：定义一个Spring管理的Bean。
- `@Component`：将类标识为Spring管理的组件。
- `@ComponentScan`：指定要扫描的包，以自动发现和注册Spring组件。
- `@Configuration`：标识配置类，包含一个或多个`@Bean`方法。
- `@Controller`：标识控制层组件，处理HTTP请求。
- `@Lazy`：延迟初始化Bean，只有在第一次使用时才创建Bean实例。
- `@Qualifier`：与`@Autowired`结合使用，按名称注入Bean。
- `@Repository`：用于数据访问层，标识数据仓库类。
- `@Resource`：按名称或类型注入Bean。
- `@Scope`：定义Bean的作用域，如单例或原型。
- `@Service`：用于业务逻辑层，标识服务类



## 三.自定义IOC容器:

### 1. **容器结构**

- **beanMap**: 存储实例化的bean对象。
- **beanDefinitionMap**: 存储bean的定义信息（如类的全路径、懒加载、作用域等）。

### 2. **构造函数**

- `YcAnnotationConfigApplicationContext(Class<?> ... configClass)`: 通过接收配置类的数组，解析其中的注解并加载bean定义。

### 3. **解析注解**

- **@YcComponentScan**: 解析该注解获取待扫描的包路径，并递归扫描加载带有IoC注解的类。
- **@YcBean**: 在配置类中查找带有此注解的方法，并将其返回的对象存入beanMap和beanDefinitionMap。

### 4. **Bean的创建与依赖注入**

- `createBean()`: 创建非懒加载和单例作用域的bean并存入beanMap。
- `doDi()`: 遍历beanMap中的bean，查找字段上的依赖注入注解（如@YcResource、@YcAutowired），并进行反射注入。

### 5. **依赖注入逻辑**

- **@YcResource**: 指定依赖的bean ID，通过`getToDiObject()`获取并注入。
- **@YcAutowired**: 需要实现基于类型的自动装配逻辑（此部分尚未实现）。

### 6. **类扫描**

- `recursiveLoadBeanDefinition(String[] basePackages)`: 根据给定的包路径，扫描所有类文件，并根据注解信息生成`YcBeanDefinition`对象。

### 7. **Bean的获取**

- `getBean(String beanid)`: 根据bean ID返回bean实例，支持原型模式和懒加载。

### 8. **异常处理**

- 多处使用try-catch捕获异常，确保容器的健壮性。



## 四.spring中的邮件发送、jms消息代理、Quartz定时器



## 五.AOP的学习:CGLIB动态代理、JDK动态代理并自定义实现jdk动态代理

**代理方式**：

- **JDK动态代理**：只支持接口代理，通过实现接口创建代理对象。
- **CGLIB**：通过字节码生成库动态创建子类代理，支持类的代理。

**性能**：

- **JDK动态代理**：性能稍低，因为每次调用都需要通过反射。
- **CGLIB**：通常性能更高，因为它直接生成字节码，但对类的继承有要求。

**使用场景**：

- **JDK动态代理**：适用于有接口的场景。
- **CGLIB**：适用于没有接口的类，或者需要对类的方法进行拦截时。



## 六.springMVC的学习:模型（Model）、视图（View）和控制器（Controller）,拦截器



## 七.银行小项目:

### 技术:邮箱、jms(activemq)、模板(Velocity)

**邮箱**：在用户进行关键操作（如存款、取款和转账）后，通过电子邮件通知用户账户变动的情况，以确保用户随时了解账户动态。

**JMS（Java Message Service）**：在操作成功后，系统通过后置增强（使用切面编程）将账户变动信息发送到JMS消息中间件。这种设计允许系统与其他服务或应用进行异步通信，实现高内聚和低耦合，防止邮箱承受不住压力导致崩溃。

**模板引擎**：使用模板引擎生成邮件内容和消息体，以便于维护和修改通知格式。

具体实现中，通过定义切面，拦截存款、取款和转账操作的成功执行，并在操作完成后触发相应的通知逻辑。这样，用户能够及时收到与其账户相关的重要信息，同时系统也能通过消息中间件与其他服务进行进一步的交互和处理。

