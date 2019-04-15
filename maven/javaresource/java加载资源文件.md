## java 加载资源文件

查找资源的规则是由类的ClassLoader实现的，如果这个类是由根类加载器也就是bootstrap类加载器的话，就会由Classloader.getSystemResourceAsStream()去查找


java.lang.ClassLoader.getResource(String name)


#### 不打包成jar
可以通过getResource() 然后对路径做拼接，可以加载到各个地方的资源文件

#### 打包成jar
这个时候，jar是一个文件而不是文件目录，这个时候无法对路径做拼接了，只能加载jar包内的资源

以上两种情况参见 src/main/java/cn.fancychuan.Main

> Class.getResource() 与 ClassLoader.getResource() 的区别 见 src/test/java/cn.fancychuan.PathTest