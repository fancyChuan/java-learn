## 泛型

背景：
- JDK1.5增加泛型支持很大程度上是为了让集合能够记住元素的数据类型。
- 在这之前都是把元素当成Object处理。这样在取出元素处理的时候，很容易出现 ClassCastException异常
- 使用了泛型以后，从集合中取出元素就再也不需要对类型做转换，因为集合“记住”了元素的类型

java7的泛型
- java7之前<>里面的类型名称不能省略
```
List<String> str = new List<>(); // java7以后可以这样写，省略new <String>里的String
```

深入泛型
- 定义泛型接口、类：可以为任何类、接口增加泛型声明（并不只有集合类才使用，虽然集合类是使用泛型的重要场所） 参见 Apply.java
- 从泛型类派生子类
    - 使用带泛型声明的接口、父类的时候，不能再包含类型形参，需要具体给出类型
    - 比如 public class A extends Apply<T>{} 就是错的，应该是public class A extends Apple<String>{}
    - 当然也可以不传入实际类型参数。比如 public class A extends Apple{}
- 不存在泛型类
    - 虽然List<String> 可以视为List的子类，但是系统并没有产生新的class文件，也不会吧List<String>当成新类来处理。参见 Main.testNotNewClass()
    - 在静态方法、静态初始化块、静态变量的声明和初始化中都不能使用类型形参。
    - 由于系统不会真正生成泛型类，所以instanceof后面不能使用泛型类 比如 cs instanceOf List<String> 是错误的
> List<String> 可以视为List的子类 等同于：
```
public interface ListString extends List {
    void add String(String x);// 原来的E形参全部变成String
}
``` 

