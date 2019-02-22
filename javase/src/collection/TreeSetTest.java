package collection;

import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
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
}
