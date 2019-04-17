package collection;

import java.util.TreeSet;

public class TreeSetTest {
    /**
     * 1. TreeSet的一些方法
     */
    public static void testTreeSet() {
        TreeSet nums = new TreeSet();
        nums.add(5);
        nums.add(8);
        nums.add(1);
        nums.add(-1);
        nums.add(10);

        System.out.println(nums.lower(6)); // 小于6的最大元素
        System.out.println(nums.higher(6)); // 大于6的最小元素

        System.out.println(nums.subSet(1, 8)); // [1,8)
        System.out.println(nums.headSet(5)); // 小于5
        System.out.println(nums.tailSet(5)); // 大于等于5
    }

    /**
     * 2. 测试compareTo()是两个元素是否相等的唯一标准
     * 结论：
     *      为了避免下面重复添加同一个对象，重写了某个类的equals()方法时应该确保compareTo()也会得到相对应的结果
     */
    public static void testCompareTo() {
        TreeSet treeSet1 = new TreeSet();
        treeSet1.add(2);
        treeSet1.add(2);
        System.out.println(treeSet1); // 结果为 [2] 说明相同的元素只保留一个

        TreeSet<Z> treeSet2 = new TreeSet<>();
        Z z1 = new Z(6);
        treeSet2.add(z1);
        treeSet2.add(z1);
        System.out.println(treeSet2); // 结果为 [collection.Z@4554617c, collection.Z@4554617c] 说明虽然equals()=true，但是还是能添加成功，因为调用compareTo()不等于0
        treeSet2.first().age = 9; // 修改元素的属性
        System.out.println(treeSet2.last().age); // 结果变为9，说明第一个和第二个元素都是同一个对象的引用
    }

    /**
     * 3. 测试可变对象被修改后对TreeSet的影响
     */
    public static void testChangeEle() {
        TreeSet<R> treeSet = new TreeSet<>();
        treeSet.add(new R(5));
        treeSet.add(new R(-3));
        treeSet.add(new R(9));
        treeSet.add(new R(-2));
        System.out.println(treeSet);

        treeSet.first().count = 20;
        treeSet.last().count = -2;
        System.out.println(treeSet); // 原来的位置不变，现在的顺序是乱的

        System.out.println(treeSet.remove(new R(-2))); // 删除被修改过的对象，失败
        System.out.println(treeSet);
        System.out.println(treeSet.remove(new R(5))); // 删除没被修改过的对象，成功
        System.out.println(treeSet);
    }

    public static void main(String[] args) {
        // testTreeSet();
        // testCompareTo();
        testChangeEle();
    }
}

/**
 * 用于测试compareTo()是两个元素是否相等的唯一标准，哪怕 equals() 返回true
 */
class Z implements Comparable {
    int age;

    public Z(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }
}

/**
 * 用于测试可变对象被修改后对TreeSet的影响
 */
class R implements Comparable {
    int count;

    public R(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return "R[count:" + count + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == R.class) {
            R r = (R) obj;
            return this.count == r.count;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        R r = (R) o;
        // return count > r.count ? 1 : count < r.count ? -1 : 0; // 这种写法可以替换为下面一行
        return Integer.compare(count, r.count);
    }
}