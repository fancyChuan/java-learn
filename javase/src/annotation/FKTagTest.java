package annotation;

@FKTag(age=5)
@FKTag(name="疯狂啊", age=666)
public class FKTagTest {
}

/* 依然可以采用java8以前的写法
@FKTags({@FKTag(age=5)})
@FKTag(name="疯狂啊", age=666)
public class FKTagTest {
}
 */
