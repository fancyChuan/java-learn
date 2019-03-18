package annotation;

import java.lang.annotation.*;

@Repeatable(FKTags.class) // 加上这一行以后就支持重复注解了，其中TKTags类的value是一个注解容器，能够把注解元素放在容器中
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FKTag {
    String name() default "fancyChuan";
    int age();
}
