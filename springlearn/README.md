## Spring

IOC：控制反转
- Bean模块
    - 将类和类之间的依赖从代码中脱离，采用配置的方式进行依赖关系描述
    - IOC容器负责依赖类之间的创建、拼接、管理、获取等工作
    - BeanFactory接口是Spring的核心接口，实现了容器的许多功能
- Context模块
- 表达式语言模块

AOP
> 面向横切面编程。在Spring中实现AOP有多重选择
- Spring AOP
- AspectJ（AOP语言级框架）
- java.lang.instrument

数据访问与集成
> 程序的核心问题都是对数据的访问和操作，数据有多种表现形式，需要有不同的技术
- JDBC
- ORM 
- OXM 
- JMS
- 事务管理


Web及远程操作
- 建立在Spring Context模块之上


持久化层
- 负责数据的访问和操作，DAO类被上层的业务类调用
- 主要工作是从数据库中加载数据并实例化为领域对象，或者将领域对象持久化到数据库中


【参考资料】
1. [Spring3.0就这么简单 - 陈雄华](https://github.com/djsecret/sprProjects)
