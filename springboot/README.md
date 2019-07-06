## Spring Boot

### HelloWorld探究
#### 1.POM文件
- 父项目
```
项目依赖的父项目
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.6.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>
  
父项目的父项目
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.1.6.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
  </parent>
这是真正管理springboot应用里面的所有依赖版本，是SpringBoot的版本仲裁中心
没有在dependencies里面定义才需要制定版本
```
- 启动器
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
spring-boot-starter: SpringBoot场景启动器，帮我们导入web模块正常运行所以来的组建

springboot把启动器抽象了出来，做成了一个个启动器，只需要引入所需要的starter就会把所有依赖都导入进来。需要什么功能就导入什么场景启动器

[官网starters地址](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/using-boot-build-systems.html#using-boot-starter)