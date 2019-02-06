package base.oop;

/**
 * 方法重写，使用的是动态绑定
 *
 * 动态绑定：调用对象的成员方法时，将方法与对象的实际内存进行绑定，进行调用。调用属性的时候，并没有动态绑定的操作
 *      也就是说，对于成员属性来说，在哪声明在哪使用
 *
 * 比如：
 *      对于aaa来说，子类中没有getResult()方法，会执行父类的getResult方法，这个时候使用的是父类的内存空间，
 *      因此，使用的i=10。于是结果是20
 *
 *      对于xx来说，虽然向上转型为XX类型，也调用了父类的getResult()，但是执行到getI()的时候，因为子类YY中有getI()这个方法，
 *      这个方法是在xx的内存中的，所以会执行子类的getI()，而子类中的getI()里面的i是在YY定义的，所以i=20.
 */
public class Test3 {
    public static void main(String[] args) {
        AA aa = new BB();
        System.out.println(aa.getResult()); // 结果是40

        AA aaa = new CC();
        System.out.println(aaa.getResult()); // 结果是20

        XX xx = new YY();
        System.out.println(xx.getResult()); // 结果是30
    }


}

class AA {
    public int i = 10; // 在哪声明在哪使用。这个i是在AA中声明的，只在AA内使用
    public int getResult() {
        return i + 10;
    }
}

class BB extends AA {
    public int i = 20;
    public int getResult() {
        return i + 20;
    }
}

class CC extends AA {
    public int i = 20;
}


class XX {
    public int i = 10;
    public int getResult() {
        return getI() + 10;
    }
    public int getI() {
        return i;
    }
}

class YY extends XX {
    public int i = 20;
    public int getI() {
        return i;
    }
}