## JAAS

### 授权
授权/鉴权：确定了当前执行代码的用户有什么权限，资源是否可以进行访问

授权的方式是为安全管理器绑定一个授权策略文件，并在java应用中设置安全管理器

为安全管理器绑定policy文件的方式：
- 1.在运行程序时加入 -Djava.security.policy=demo.policy
- 2.执行System.setProperty("java.security.policy", "demo.policy")



### 认证
认证：可靠安全地确定当前是谁在执行代码(也就是登录了之后知道是谁在执行代码，具体执行了啥不知道)

一般主要涉及：LoginContext，LoginModule，CallbackHandler，Principal这几个类，后三个类需要开发者自己实现
- LoginContext：认证核心类，也是入口类，用于触发登录认证，具体的登录模块由构造方法name参数指定
- LoginModule：登录模块，封装具体的登录认证逻辑，认证失败抛异常，认证成功则向Subject添加一个Principal
- CallbackHandler:回调处理器，用于搜集认证信息
- Principal:代表程序用户的某一身份，与其密切相关的为Subject，用于代表程序用户，而一个用户可以多种身份，授权时可以针对某用户的多个身份分别授权

绑定认证策略的方式：
- 运行程序时加上： -Djava.security.auth.login.config=demo.config
- 代码中配置： System.setProperty("java.security.auth.login.config", "demo.config");


### 核心JAAS类
LoginModule
- JndiLoginModule 用于验证在JNDI中配置的目录服务
- Krb5LoginModule 使用Kerberos协议进行验证
- NTLoginModul 使用当前用户在NT中的用户信息进行验证
- UnixLoginModule 使用当前用户在Unix中的用户信息进行验证


配置文件
```
结构：
Application {
ModuleClass Flag ModuleOptions;
ModuleClass Flag ModuleOptions;
...
};
比如
Sample {
com.sun.security.auth.module.NTLoginModule Rquired debug=true;
}
```
- ModuleClass表示要使用的验证类
- Flag控制当申请中包含了多个LoginModule时进行登录时的行为：Required、Sufficient、Requisite和Optional
    - 最常用的是Required，使用它意味着对应的LoginModule对象必须被调用，并且必须需要通过所有的验证
- ModuleOption允许有多个参数。例如可以设定调试参数为True（debug=true），这样诊断输出将被送到System.out中 



#### 参考资料
1. [类和接口说明](http://itmyhome.com/java-api/javax/security/auth/Subject.html)
