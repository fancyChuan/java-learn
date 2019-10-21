## IoC容器

IOC：控制反转
> 通过容器来控制业务对象之间的依赖关系，控制权由应用代码转移到了外部容器。控制权的转移就是反转。好处是降低了业务对象之间的依赖程度

#### BeanFactory
概述：
- BeanFactory接口是Spring的核心接口，一般称其为IoC容器，通过java反射功能实例化bean并建立bean之间的依赖关系
- IOC容器负责依赖类之间的创建、拼接、管理、获取等工作
- 还提供了多种高级服务，比如bean实例缓存、生命周期管理、bean实例代理、事件发布、资源装载等
- 面向Spring本身，是基础设施

> javaBean有一定的规范，要求提供一个无参构造器、不依赖某个特定的容器等，而Spring中的bean更宽泛，所有可以被spring容器实例化并鼓励的java类都可以成为bean

初始化BeanFactory


#### ApplicationContext
- ApplicationContext应用上下文，有时候为了方便，也将ApplicationContext称为Spring容器
- 建立在BeanFactory基础之上，提供更多面向应用的功能
- 面向Spring用户，也就是业务开发者