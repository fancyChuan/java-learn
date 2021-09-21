## Springboot整合的组件及其功能

- DispatcherServlet
```xml
<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
```
> 负责接收所有请求，也负责springMVC的核心的调度管理

- CharacterEncodingFilter
```
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
> 解决中文乱码
```
首先，页面中字符集统一
JSP : <%@page pageEncoding="utf-8" %>
HTML : <meta charset="UTF-8">
其次，tomcat中字符集设置，对get请求中，中文参数乱码有效
Tomcat配置：URIEncoding=utf-8
最后，设置此filter，对post请求中，中文参数乱码有效
```
    