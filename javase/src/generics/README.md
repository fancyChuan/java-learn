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

类型通配符
- List<String> 不是List<Object>的子类，参见 Main.testISNotSubClass()
- java早期设计中，允许吧Integer[] 赋值给Number[] 变量，其实是不合理的，因为两者并不是互为父子类
- 数组和泛型不同。Sub是Fat的子类型，那么 Sub[] 仍然是 Fat[] 的子类型，但 G<Sub>不是G<Fat>的子类型
- 使用类型通配符
    - List<?> 可以表示各种泛型List的父类，? 就是通配符
    - 带这种通配符的List仅表示是各种泛型List的父类，并不能把元素加到其中 List<?> c = new ArrayList<String>(); c.add("tt"); 是错误的，唯一例外的是null，可以放进去
    - 设定类型通配符的上限： List<? extends Shape>表示所有Shape泛型List的父类 参见 Main.testLimitMatch()
    - 设定类型形参的上限： 在定义类型形参时设定
        - public class Apple<T extends Number> {} 这是一个Apple泛型类，要求是Number的子类型
        - 极端情况下，需要设定多个上限时：最多一个父类上限，可以有多个接口上限，接口上限在类上限之后
        > public class Apple<T extends Number & java.io.Serializable>
    - 设定通配符的下限： <? super Type> ? 表示它必须是Type的本身或者Type的父类。 参见：testSuperMatch()
- 泛型方法，在类、接口中没有使用类型形参，在方法中定义类型形参
    - 语法： 修饰符 <T, S> 返回值类型 方法名(形参列表) 参见：testGenericMothed()
    - 在调用的时候，不用显式指出String Object等类型，因为系统会根据传入的变量自动判断类型参数的类型 参见 testGenericMothed()
- 泛型方法和类型通配符的区别
    - 如果一个类型形参只使用一次，唯一效果就是传入不同的实际类型，那么就应该用通配符
    - 如果方法中的一个形参的类型或者返回值依赖另一个形参的类型，那么就应该用泛型方法
    - 另一个区别是：类型通配符既可以在方法签名中定义形参的类型，也可以用于定义变量的类型；但泛型方法中的类型形参必须在对于方法中显式声明
- 泛型构造器
    - 跟泛型方法一样，也可以在构造器签名中声明类型形参
