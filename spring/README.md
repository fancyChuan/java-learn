## Spring 

结构目录说明：
- spring 整个项目，pom文件只是用来管理有多少模块，便于IDEA识别的而已，没有多大用
- simple-spring 《spring3.0就这么简单》书籍的学习实践，这是一个完整的工程，其pom文件是它下面所有模块子pom的父pom。用的版本是3.1
- springlearn “实验楼”上关于spring的实践，用的版本是5.1.1


学习/复习顺序：
- IoC控制反转
    - 装配bean
        - 通过扫描的方式
        - 自定义第三方Bean
        - 有条件的装配bean：某些条件下不需要装配bean，比如在数据源配置出错的时候
- DI依赖注入
    - 使用Autowired：[参见BussinessPerson.java](spring/springlearn/src/main/java/cn/fancychuan/spring/di/pojo/BussinessPerson.java)
    - 消除歧义Primary和Qualifier
    - 带有参数的构造方法类的装配：[BussinessPersonWithPara.java](spring/springlearn/src/main/java/cn/fancychuan/spring/di/pojo/BussinessPersonWithPara.java)
- AOP面向横切面编程




【参考资料】
1. [Spring3.0就这么简单 随书源码 - 陈雄华](https://github.com/djsecret/sprProjects)
