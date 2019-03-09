## JDBC

JDBC API是一组接口，没有提供实现类，实现类由各个数据库产商提供，也就是数据库驱动程序。使用JDBC时，只需要面向JDBC API编程即可。这也是面向接口编程的应用

JDBC有4中类型：
1. JDBC-ODBC桥，最早实现，为了推广JDBC，java8中已被删除
2. 直接把JDBC API映射成特定的数据库客户端API，对性能有严格要求的时候可以考虑使用
3. 支持三层接口的JDBC访问方式
4. 纯java，直接与数据库实例交互，最智能，也是目前最流行的


Statement执行SQL语句有三种方法（方法一次只能执行一个SQL语句）：
- execute() 执行任何SQL语句，返回一个boolean值。当SQL有返回值时为true，否则为false
- executeQuery() 执行select语句，返回ResultSet，当使用非select语句的时候，执行报错
- executeUpdate() 执行DML、DDL语句，返回一个整数，DML代表影响的记录条数，DDL返回0