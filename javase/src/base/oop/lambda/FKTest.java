package base.oop.lambda;

/**
 * 使用java8为函数式接口提供的@FunctionalInterface注解
 * 该注解告诉编译器执行更严格的检查，确保该接口是函数式接口（只能有一个抽象方法）
 */
@FunctionalInterface
public interface FKTest {
    void run();
}
