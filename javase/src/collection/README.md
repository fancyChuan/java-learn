## java集合
数组与集合的区别：
- 数组长度固定，集合可以变化
- 数组无法映射，集合的Map类型可以映射
- 数组既可以保存基本类型，也可以保存对象（实际是对象的引用），而集合只能保存对象
### 2. Collection和Iterator类
Collection是List、Set、Queue的父接口。需要关注的方法：
- boolean retainAll(Collection c) 从集合中删除集合c里不包含的元素（交集）
- Object[] toArray() 把集合转为数组
- removeIf(Predicate filter) 批量删除符合条件的元素，也可以使用Lambda表达式

> 所有Collection实现类都重写了toString()， 所以通过System.out.println()的时候，会打印出集合的所有元素内容。

Iterator接口是Collection的父接口，java8为Iterator接口新增了一个forEach(Consumer action)方法，而Consumer是一个==函数式接口==，可以使用Lambda表达式

Iterator需要注意的方法：
- void remove() 删除集合中上一次next()方法返回的元素。
- void forEachRemaining(Consumer action) java8新增的方法，可以对集合中剩余的元素做操作，支持Lambda表达式

java8新增了Stream操作集合，Stream有两种方法类型：中间方法、末端方法（对流的最终操作，之后流关闭）

Collection对象可以调用stream()方法来获得流对象，并进一步执行聚合操作。