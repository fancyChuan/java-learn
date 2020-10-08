## 一步一步学Spring Boot 2：微服务项目实战

集成MySQL：cn.fancychuan.springboot.SpringbootApplicationTests#mysqlTest
- 1.在pom文件中添加集成依赖
```
<dependency><!-- 用于集成mysql -->
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```
- 2.在配置文件中添加配置项
```
spring.datasource.url=jdbc:mysql://hphost:3306/test?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
