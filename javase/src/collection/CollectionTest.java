package collection;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CollectionTest {
    public static void main(String[] args) {
        // testCollection();
        // testLambdaEach();
        // testIteratorError();
        // testRemoveIf();
        // testPredicate();
        // testStream();
        testCollStream();
    }

    /**
     * 1. ArrayList、HashSet示例
     *
     *  实例化的时候，可以指定集合元素的类型 通过 Collection<xxx>来指定，或者ArrayList<xxx>，其中xxx就限定了元素的类型。
     *  比如Collection<String> test = new ArrayList()，这样test.add(666)就会报错，虽然可以自动装箱
     */
    public static void testCollection() {
        Collection arrayList = new ArrayList();
        arrayList.add("fancy");
        arrayList.add(66); // 虽然集合不能放基本类型，但是java支持自动装箱，也就是把int转为Integer
        arrayList.remove(66);
        arrayList.add(666);
        arrayList.add("大闹天天啦~");
        System.out.println(arrayList); // [fancy, 666, 大闹天天啦~] 说明实现了toString()方法

        Collection hashSet = new HashSet();
        hashSet.add("9年义务教育");
        hashSet.add("ooo");
        hashSet.add(666);
        System.out.println(arrayList.containsAll(hashSet)); // 是否全部包含，false
        System.out.println(arrayList.contains(666)); // true

        arrayList.removeAll(hashSet);
        System.out.println(arrayList); // [fancy, 大闹天天啦~] 666被干掉了

        arrayList.add(666);
        hashSet.retainAll(arrayList);
        System.out.println(hashSet); // [666] 只有一个元素相同
    }

    /**
     * 2. 使用Lambda表达式遍历集合
     */
    public static void testLambdaEach() {
        Collection hashSet = new HashSet();
        hashSet.add("9年义务教育");
        hashSet.add("ooo");
        hashSet.add(666);

        hashSet.forEach(obj -> System.out.println("迭代集合元素： " + obj));

        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (item.equals(666)) {
                iterator.remove(); // 删除上一次next()得到的元素，注意这里是要通过迭代器去修改
                iterator.forEachRemaining(obj -> System.out.println("[x]这是额外增加的: "+ obj + "xxx"));
            }
            System.out.println("使用传统方式遍历: " + item);

            item = "修改，但是不影响集合的元素"; // 迭代时，是把元素的值赋给迭代变量，而不是元素本身，所以对迭代变量的修改不影响集合
        }
        System.out.println("最终的集合内容为：" + hashSet.toString());

    }

    /**
     * 3. 测试Iterator的快速失败(fail-fast)机制:
     *      一旦在迭代的过程中发现集合被修改（通常被其他线程修改），会立即报java.util.ConcurrentModificationException的错误
     *      但是要注意，如果是最后一个元素，不一定会报错
     */
    public static void testIteratorError() {
        Collection hashSet = new HashSet();
        hashSet.add("9年义务教育");
        hashSet.add("ooo");
        hashSet.add("什么鬼");
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            Object item = (String) iterator.next();
            System.out.println(item);
            if (item.equals("什么鬼")) { // 这里如果改成 item.equals("ooo")就不会报错，因为ooo是最后一个元素
                hashSet.remove(item);
            }
        }
        System.out.println(hashSet);
    }

    /**
     * 4. removeIf() 的使用
     */
    public static void testRemoveIf() {
        Collection hashSet = new HashSet();
        hashSet.add("9年义务教育");
        hashSet.add("ooo");
        hashSet.add("什么鬼啊");

        hashSet.removeIf(obj -> ((String) obj).length() < 4); // "什么鬼啊".length()的值为4
        System.out.println(hashSet);
    }

    /**
     * 5. java8新增的Predicate操作，可以充分简化集合的运算
     */
    public static void testPredicate() {
        Collection hashSet = new HashSet();
        hashSet.add("9年义务教育");
        hashSet.add("ooo");
        hashSet.add("什么鬼啊");

        System.out.println(calCall(hashSet, item->((String) item).length()>4));
        System.out.println(calCall(hashSet, item->((String) item).contains("o"))); // 统计包含“o” 的元素个数
    }
    public static int calCall(Collection coll, Predicate p) {
        int total = 0;
        for (Object obj: coll) {
            if (p.test(obj)) {
                total ++;
            }
        }
        return total;
    }

    /**
     * 6. java8新增的Stream操作集合
     */
    public static void testStream() {
        IntStream intStream = IntStream.builder().add(20).add(10).add(50).add(-2).add(18).build();

        // 下面调用聚集函数的代码每次只能执行一行，之后流就会关闭
//        System.out.println("所有元素的最大值: " + intStream.max().getAsInt());
//        System.out.println("所有元素的最小值: " + intStream.min().getAsInt());
//        System.out.println("所有元素的综合： " + intStream.sum());
//        System.out.println("所有元素的平方是否都大于20： " + intStream.allMatch(ele -> ele*ele>20));
//        System.out.println("是否包含元素的平方大于20： " + intStream.anyMatch(ele -> ele*ele>20));

        IntStream newIntStream = intStream.map(ele -> ele * 2 + 1);
        newIntStream.forEach(System.out::println);
    }

    /**
     * 7. stream()获取流
     */
    public static void testCollStream() {
        Collection hashSet = new HashSet();
        hashSet.add("9年义务教育");
        hashSet.add("ooo");
        hashSet.add("什么鬼啊");

        System.out.println(hashSet.stream().filter(ele->((String) ele).length()>3));
        hashSet.stream().mapToInt(ele->((String) ele).length()).forEach(System.out::println);
    }
}
