## JAVA远程调试
> 解决一切JAVA问题的能力！

两点认知：
- Java世界的一切东西都是在拼Java命令行参数而已
- 我们能够调试一切Java程序
    - 想清楚要调试的代码运行在哪个JVM里面
    - 找到要调试的源代码

给需要调试的程序在启动时加上
```
-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=11729
```

加上这个环境变量，那么当前环境下，所有使用Java启动的程序都会允许远程调试
```
JAVA_TOOL_OPTION=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=11729

suspend=y 表示要阻塞，停下来等待远程调试IDEA来连接
```

之后用IDEA的远程功能去连接上这11729端口