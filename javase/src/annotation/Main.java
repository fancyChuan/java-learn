package annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testSafeVarargs(Arrays.asList("Hello!"), Arrays.asList("world"));
    }

    /**
     * 1. 产生“堆污染”
     * (1)不带泛型对象赋值给对泛型对象时往往会发生堆污染
     * (2)尤其是在形参个数可变的方法中，而形参的类型又是泛型时，更容易导致堆污染
     *
     * 抑制这种警告的方法：
     *  - 使用@SafeVarargs修饰
     *  - 使用@SuppressWarnings("unchecked")
     *  - 编译时使用 -Xlint:varargs选项
     *
     *  下面的方法在 javac 编译的时候会报错
     */
    @SafeVarargs
    public static void testSafeVarargs(List<String>... listStrArray) {
        // 下面演示第(1)种情况
        List list = new ArrayList<Integer>();
        list.add(20);
        List<String> listStr = list; // 整个地方产生堆污染
        System.out.println(listStr.get(0)); // 这里在运行时报错ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        // 下面演示第(2)种情况
        List[] listArray = listStrArray; //java语音不允许创建泛型数组，因此listStrArray只能被当成List[]处理，这个时候已经发生了堆污染
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(new Random().nextInt(100));
        listArray[0] = myList;
        String s = listStrArray[0].get(0);
    }
}

// 关闭整个类里的编译器警告。针对没有使用泛型的警告
@SuppressWarnings(value="unchecked")
class SuppressWarningsTest {
}
