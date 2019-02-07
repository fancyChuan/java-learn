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