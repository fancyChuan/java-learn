

/**
 * 20190116
 *  【一对一】
 *      * 使用association来做一对一的关系映射 见ClassesMapper.xml文件
 *      * 这里没有采用  嵌套结果映射  ，而是让结果映射可复用，采用了resultMap去指示的方法
 *
 *      【注意】
 *          * JDBC配置的时候注意数据库的时区以及&的特殊写法 jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&amp;characterEncoding=utf-8
 * 20190121
 *  【一对多】
 *      * 主要使用collection来做一多的映射关系。见ClassesMapper.xml文件
 *      * 这里使用了 嵌套结果映射 的方式
 * 20190124
 *  【多对多】
 *      * 跟一对多相比，加了个中间的映射类 StudentCourseLink，对于这个类来说，就是一对多。
 *
 * 【理解】
 *      1. mybatis的本质把JDBC返回的每一行数据进行封装
 *
 *  1	Tom	male	18	1	1	Math	5
 *  1	Tom	male	18	1	2	Computer	4
 *
 *      比如上面的就是一对多，后三个变量用collection来封装。见 StudentMapper.xml
 *
 * 20190126
 *  【注解】
 */
public class Main {
}
