package collection;

import java.util.HashSet;

/**
 * 20190222 HashSet存元素的机智
 *
 * 运行结果： [collection.B@1, collection.B@1, collection.C@2, collection.A@74a14482, collection.A@4554617c]
 *
 * 说明两个 new C() 被认为是相同的元素，因此只保留一个，而AB尽管要么hashCode相同要么equals相等，但也认为是两个对象
 *
 * 也就是说：只有两对象的equals()方法相等，同时hashCode()方法返回值也相等，HashSet才认为是同个元素
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new A());
        hashSet.add(new A());
        hashSet.add(new B());
        hashSet.add(new B());
        hashSet.add(new C());
        hashSet.add(new C());

        System.out.println(hashSet);
    }
}


class A {
    public boolean equals(Object obj) {
        return true;
    }
}

class B {
    public int hashCode() {
        return 1;
    }
}

class C {
    public int hashCode() {
        return 2;
    }
    public boolean equals(Object obj) {
        return true;
    }
}
