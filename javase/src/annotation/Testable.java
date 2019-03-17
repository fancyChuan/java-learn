package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个指明程序是可测试的注解
 *
 * 这个注解可以作为JUnit框架的补充，框架要求方法以test开头，而这个注解可以让任何方法都是可测试的
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Testable {
}
