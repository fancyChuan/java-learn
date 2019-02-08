## javac编译命令

命令选项 | 含义
--- | ---
-d | 指定编译生成的字节码文件的存放路径，ieda中使用的一般是target目录


> -d 示例
```
E:\javaProject\java-learn\javase>javac -d target src/base/javac/HelloWorld.java
```
上述代码会在当前的target目录下生成 base/javac/HelloWorld.class 其中base/javac也可以理解为是目录，但实际上base.javac其实是java包

执行的时候，需要进入-d所指定的目录下，并且需要把生成的字节码文件按照：包名+类名的格式，如下
```
E:\javaProject\java-learn\javase\target>java base/javac/HelloWorld
hello world

直接执行 java HelloWorld 会找不到类的错误
```

## java执行命令
命令选项 | 含义
--- | ---
-classpath | 选项的值可以是一系列路径，选项与值中间用空格隔开



## javadoc命令
命令选项 | 含义
--- | ---
-d | 指定存放生成API文档的目录
-windowtitle | 设置API文档的浏览器窗口标题
-doctitle | HTML格式，指定概述页面的标题（只有对多个包下源文件生成API文档时才有概述界面）
-header | HTML格式，指定页眉


### javadoc常用标识
标识 | 含义 | 出现的位置
--- | ---| ---
@author | 作者 | 
@version | 版本
@deprecated | 不推荐使用的方法
@param | 方法的参数说明
@return | 返回信息
@see | “参见”，，用于指定交叉参考的内容
@exception | 抛出异常的类型
@throws | 抛出的异常