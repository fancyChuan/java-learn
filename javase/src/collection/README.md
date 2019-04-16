## java集合
数组与集合的区别：
- 数组长度固定，集合可以变化
- 数组无法映射，集合的Map类型可以映射
- 数组既可以保存基本类型，也可以保存对象（实际是对象的引用），而集合只能保存对象
### 2. Collection和Iterator类
> 示例参见 [CollectionTest.java](https://github.com/fancyChuan/java-learn/blob/master/javase/src/collection/CollectionTest.java)

Collection是List、Set、Queue的父接口。需要关注的方法：
- boolean retainAll(Collection c) 从集合中删除集合c里不包含的元素（交集）
- Object[] toArray() 把集合转为数组
- removeIf(Predicate filter) 批量删除符合条件的元素，也可以使用Lambda表达式

> 所有Collection实现类都重写了toString()， 所以通过System.out.println()的时候，会打印出集合的所有元素内容。

Iterator接口是Collection的父接口，java8为Iterator接口新增了一个forEach(Consumer action)方法，而Consumer是一个**函数式接口**，可以使用Lambda表达式

Iterator需要注意的方法：
- void remove() 删除集合中上一次next()方法返回的元素。
- void forEachRemaining(Consumer action) java8新增的方法，可以对集合中剩余的元素做操作，支持Lambda表达式

java8新增了Stream操作集合，支持串行、并行聚集操作的元素。Stream有两种方法类型：中间方法、末端方法（对流的最终操作，之后流关闭）

Collection对象可以调用stream()方法来获得流对象，并进一步执行聚合操作。

### 3. Set集合
>示例参见 [HashSetTest.java](https://github.com/fancyChuan/java-learn/blob/master/javase/src/collection/HashSetTest.java)

Set就是Collection，只是Set不允许包含重复元素

- HashSet类：按Hash算法来存储集合元素，有很好的存取和查找性能
    - 集合元素可以为null
    - HashSet不是同步的，多个线程同时修改元素时，需要通过代码保证同步
    - 判断两元素是否相同的标准是 两对象的equals()方法相等，并且两对象的hashCode()方法返回值也相等
    - hashCode()重写规则：
        - 程序运行时，同一对象多次调用hashCode()得到的值应该一样
        - 当两个对象通过equals()方法比较返回true时，两对象的hashCode()值也应该相同
        - 对象中用作equals()比较的实例变量，也应该用于hashCode()
> 注意：如果把某个类的对象保存到HashSet中，但又需要重写hashCode() equals() 方法时，应该尽量让两对象的这两个方法返回值保持相等

- LinkedHashSet类：使用hashCode来决定元素存储位置的同时，使用链表来维持元素的顺序
    - 性能略差与HashSet，但是在遍历元素的时候，性能较好

- TreeSet类：SortedSet接口的实现类，可以排序元素
    - TreeSet提供额外的方法：
        - Comparator comparator() 对象所采取的的排序方法，默认的自然排序则返回null
        - first() last() 
        - lower() higher()
        - subSet() headSet() tailSet()
    - 采用**红黑树**的数据结构来存储集合元素
    - 要让TreeSet能正常工作，只能添加同一种类型的对象
    - 放进TreeSet的类，需要实现Comparable接口，并实现compareTo()方法
    - 判断两元素相等的唯一标准是，调用compareTo()方法，得到0
    