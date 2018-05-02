# myJavaWebFrame

###### 以下内容是以书《架构探险》中的内容为例子，可以推得Spring也是类似的实现

#### 1、类加载（ClassUtil）
- 根据“包名.类名”的完全限定符，可以唯一确定一个类，用Class.forName()进行动态加载，获得Class对象。注意，这里的Class对象并不是我们想要的那个类的实例对象。
- Spring中可以设置自动扫描时指定要扫描的包
- 而我们的实现中，为了简便，没有指定包扫描，而是自己硬编码写了所有Controller类和Service类。所有的类会提供一个共有方法，用一个Set返回。

#### 2、根据注解对加载的类进行分类（ClassHelper）
- 取得ClassUtil中返回的含有全部Class对象的Set，一个个遍历，判断类上是否有Service注解或者Controller注解，或者更多，提供不同的方法（getServiceClassSet、getControllerClassSet），可以返回各自的集合，最后再提供一个getBeanClassSet，返回所有的Bean。

#### 3、为所有的Bean创建实例（BeanHelper）
- BeanHelper会调用ClassHelper的个getBeanClassSet取得所有的Bean，在一个静态块里遍历，并用反射的方法调用cls.newInstance()创建各自类的实例对象，以cls为Key，实例对象为Value,存储在一个Map中。
- 并提供getBean()方法，可以传入cls获取对应的实例对象。
- 这里只会创建一个实例对象，所以所有类的对象都是单例。

#### 4、依赖注入（IocHelper）
- 用一个静态块，取得BeanHelper中建立的Map对象，遍历，取出map中的每一个Class类以及相应的实例对象，遍历Class类的Field数组，检查每一个Field上是否有Autowired注解，如果有，再用field.getType()取得这个被注解变量的类型，用map.get()查看其是否是由bean容器管理的对象，即：是否在map中有实例对象,如果是，get返回不为null，此时，map.get()取出的对象，就是被注解的变量field所需要的对象，通过field.set()完成依赖注入。

#### 5、实现@RequestMapping注解（ControllerHelper）
- 首先要另外定义两个类：Request(String requestMethod, String requestPath)和Handler(Class<?> controllerClass, Method actionMethod)。
- 从构造函数可以看出，Request类是请求方法和请求URL的包装类。Handler是Controller类和添加的@RequestMapping注解的包装类。两者之间需要建立一个映射关系，放入一个Request Map中。并提供一个可根据Request对象获取处理方法的方法。
- 实现如下：首先遍历带Controller的Class类，遍历类上的Methods，判断哪些带有@RequestMapping注解，用```method.getAnnotation(MyRequestMapping.class);```取得注解对象，再取得注解上的value，这是一个URL，构造Request对象，再根据当前的controllerClass和method，构造Handler对象，以Request对象为Key，Handler对象为value，添加到Request Map 中去保存。

#### 6、用一个HelperLoader类来统一进行框架类的加载

#### 7、实现DispatcherServlet
- 首先会用URL“/”拦截所有请求，从HttpServletRequest对象中获取请求方法与请求路径，通过ControllerHelper.getHandler来构造Request对象，从而从Map里获取Handler对象。
- Handler对象里面有能处理这个URL的Controller类的Class和对应的Method对象。
- 通过cls = handler.getControllerClass()可以获取Controller的Class对象
- 通过BeanHelper.getBean(cls)可以获取这个Controller类的实例对象，
- 通过handler.getActionMethod()可以获取对象上与URL对应的方法，用上一步取得的具体实例对象可以调用这个方法，执行被注解的方法中的代码。
- 取得被注解方法的返回值，写入response对象，结束。