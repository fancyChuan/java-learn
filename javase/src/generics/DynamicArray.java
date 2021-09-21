package generics;

/**
 * 动态容器类：数组长度可变（底层数组的长度是不变的）
 *  Java容器中对应的类是ArrayList
 */
public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

}
