## JDBC

JDBC API是一组接口，没有提供实现类，实现类由各个数据库产商提供，也就是数据库驱动程序。使用JDBC时，只需要面向JDBC API编程即可。这也是面向接口编程的应用

JDBC有4种类型：
1. JDBC-ODBC桥，最早实现，为了推广JDBC，java8中已被删除
2. 直接把JDBC API映射成特定的数据库客户端API，对性能有严格要求的时候可以考虑使用
3. 支持三层接口的JDBC访问方式
4. 纯java，直接与数据库实例交互，最智能，也是目前最流行的


Statement执行SQL语句有三种方法（方法一次只能执行一个SQL语句）：
- execute() 执行任何SQL语句，返回一个boolean值。当SQL有返回值时为true，否则为false
> 可以通过getResultSet() 获取该SQL执行后的返回数据；通过getUpdateCount() 获取该SQL执行后影响的记录数
- executeQuery() 执行select语句，返回ResultSet，当使用非select语句的时候，执行报错
- executeUpdate() 执行DML、DDL语句，返回一个整数，DML代表影响的记录条数，DDL返回0

PreparedStatement
- 用于经常使用某个SQL时，对该SQL预编译，比如：
```
insert into tmp1 values (44, 'xxx')
insert into tmp1 values (55, 'yyy')
```
- PreparedStatement也有三种执行SQL的方法，只不过都不需要参数，因为已经预编译了SQL
- 比Statement效率高，并且不需要拼接SQL，通过占位符?以及后面后面使用setXxx()方法完成SQL的替换
- 可用于防止SQL注入
```
select * from jdbc_test where username='' and  pass='';
# 上面的语句如果使用字符串拼接后用Statement执行，就有可能被SQL注入成：
select * from jdbc_test where username='' or true or '' and  pass=''
```
> 注意：PreparedStatement执行带有占位符的SQL时，占位符只能代表普通值，不能代替表名、列名等数据库对象，也不用代替insert、select等关键词

使用CallableStatement调用存储过程
```
dilimiter //
CREATE PROCEDURE add_pro(a int, b int, out sum int)
begin set sum = a + b;
end;
//
```
>参见 jdbc.Main.testCallableStatement()

管理结果集
- 可滚动、可更新的结果集
    - 默认打开的ResultSet是不可更新的，需要传入额外的参数
    - resultSetType:控制ResultSet的类型，取值有：
        - ResultSet.TYPE_FORWARD_ONLY，控制记录指针只能向前移动
        - ResultSet.TYPE_SCROLL_INSENSITIVE，控制记录指针可以自由移动，底层数据的改变不会影响ResultSet的内容
        - ResultSet.TYPE_SCROLL_SENSITIVE，控制记录指针可以自由移动，底层数据的改变会影响ResultSet的内容
    - resultSetConcurrency：控制ResultSet的并发类型，取值有：
        - ResultSet.CONCUR_READ_ONLY: 只读并发模式（默认）
        - ResultSet.CONCUR_UPDATABLE: 可更新的并发模式
> 可更新的结果集要满足两个条件：1. 所有数据来源一个表 2. 数据集必须包含主键
- 管理Blob类型数据
    - Blob 二进制长对象，用于存储大文件，比如图片、视频等
    - 使用PreparedStatement的setBinaryStream() 方法传入二进制流
    - MySQL中：blob只能存储64KB，而mediumblob可以存储16MB
    
    