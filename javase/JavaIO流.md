## Java IO流

InputStream() 一个一个字节的读


加一个缓存区，可以多个字节一块读了之后再返回给Java客户端

![image](image/IO流之BufferedInputStream.png)


想要读取一行怎么办？使用BufferReader

而要使用Reader来读取字符，那需要知道使用了编码格式（utf8还是gbk等），因为不同的编码方式一个字符对应的字节数不一样

![image](image/IO流之BufferedReader.png)