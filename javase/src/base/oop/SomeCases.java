package base.oop;

public class SomeCases {
    public static void main(String[] args) {
        testReferenceInstance();
    }

    /**
     * 20190225 演示java值传递机制
     *
     * 这个示例容易造成误解，认为传到swap()方法中的是dx对象本身，而不是复制品
     */
    public static void testReferenceInstance() {
        DataX dx = new DataX(); // dx实际上存的是对象的引用，也就是对象的地址，对象的数据其实放在堆内存中
        dx.a = 1;
        dx.b = 2;

        swap(dx); // 传进来的照样是复制品，只不过是 dx对象的引用地址，所以，在方法中对对象的操作通过引用，实际影响了对象本身。
        System.out.println("a: " + dx.a + "\tb: " + dx.b);
    }
    public static void swap(DataX dx) {
        int tmp = dx.a;
        dx.a = dx.b;
        dx.b = tmp;
    }
}


class DataX {
    int a;
    int b;
}
