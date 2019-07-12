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

#### 2.主程序类，主入口类

```java
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
```

@**SpringBootApplication**: 标注某个类是程序主入口，SpringBoot通过这个类的main方法来启动应用

```
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@java.lang.annotation.Inherited
@org.springframework.boot.SpringBootConfiguration
@org.springframework.boot.autoconfigure.EnableAutoConfiguration
@org.springframework.context.annotation.ComponentScan(excludeFilters = {@org.springframework.context.annotation.ComponentScan.Filter(type = org.springframework.context.annotation.FilterType.CUSTOM, classes = {org.springframework.boot.context.TypeExcludeFilter.class}), @org.springframework.context.annotation.ComponentScan.Filter(type = org.springframework.context.annotation.FilterType.CUSTOM, classes = {org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter.class})})
public @interface SpringBootApplication {
```

@**SpringBootConfiguration**:SpringBoot的配置类

@**EnableAutoConfiguration**：开启自动注解

​	@**AutoConfigurationPackage**：自动配置包

​	@**Import**：给容器导入组件



## 二、配置文件



### 1、配置文件

SpringBoot支持两种配置文件（是全局的配置文件）：

- application.properties
- application.yml



### 6、配置文件加载位置

SpringBoot会扫描一下位置的application.properties或者application.yml文件作为默认配置文件：

- file: ./config/
- file: ./
- classpath:/config/
- classpath:/

优先级由高到低。搞优先级的配置会覆盖低优先级的配置。

**互补配置**：高优先级没有配置的属性会使用低优先级的

也可以通过spring.config.location来改变默认的配置文件位置，注意是打包后运行时使用：

```
java -jar xxx.jar --spring.config.location
```





