package base.oop;

public class Test1 {

    public static void main(String[] args) {
        A a = new B();
        // 方法的重载：方法名一样，参数类型不一样（个数、类型、顺序）【TODO：这里不应该是多态么】
        test(a);
    }

    /**
     * A a = new B(); test(a);
     * 这个案例中，如果把下面这个函数注释掉，test(a) 就会报错。
     * 这是因为 a 是A类型的，而test(B b) 这个方法不满足
     * 这个时候，把test(B b) 改为 test(Object b) 那么test(a) 就可以执行了
     */
    public static void test(A a) {
        System.out.println("aaaa");
    }

    public static void test(B b) {
        System.out.println("bbbb");
    }
}


class A {
}


class B extends A {
}