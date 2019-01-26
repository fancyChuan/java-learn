

#### 拦截器
对于SpringMVC来说，拦截器就是拦截特定的请求并进行相应的处理，比如权限验证。

通过实现HandlerInterceptor接口来完成。主要有三个方法：
1. preHandle() 请求被处理前调用该方法。返回值为true/false。false表示请求结束，不需要后续处理
2. postHandle() 请求被处理后，即Controller层处理后，视图渲染前执行
3. afterCompletion() 视图渲染后执行，主要进行资源清理

> 注： 一个请求可以被多个拦截器拦截

== 实例 ==

运行程序后： 浏览器访问 /test 路径 会被拦截，重定向到登录界面。

> 注意: springmvc-config.xml 配置了拦截所有请求。在LoginInterceptor.java中又把/login请求排除在拦截之外。