package base.oop.enumPackage;

public enum Gender {
    MALE, FEMALE, OTHER;
    public String name; // 定义一个成员变量
}

/**
 * 改进的具有更好良好封装效果的类，避免对name的不规范赋值
 */
enum Gender2 {
    MALE, FEMALE;
    private String name;

    public void setName(String name) {
        switch (this) {
            case MALE:
                if (name.equals("男")) {
                    this.name = name;
                }
                else {
                    System.out.println("参数错误");
                    return;
                }
                break;
            case FEMALE:
                if (name.equals("女")) {
                    this.name = name;
                }
                else {
                    System.out.println("参数错误");
                    return;
                }
                break;
        }
    }

    public String getName() {
        return name;
    }
}

/**
 * 最好的枚举类，设计成不可变类型
 */
enum Gender3 {
    // 此处的枚举值必须调用对应的构造器来创建，等价于
    // public static final Gender MALE = new Gender("男")
    // public static final Gender FEMALE = new Gender("女")
    MALE("男"), FEMALE("女");
    private final String name;

    // 构造器必须是私有的
    private Gender3(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/**
 * 实现了接口的枚举类
 */
enum GenderInterface1 implements GenderDesc {
    MALE, FEMALE;

    @Override
    public void info() {
        System.out.println("实现了接口的枚举类，用于定义性别");
    }
}

/**
 * 实现接口，不同的枚举值调用接口的方法时呈现不同的行为
 */
enum GenderInterface2 implements GenderDesc {
    // 此处的枚举值必须调用对应的构造器来创建
    MALE("男") {
        // 这里的花括号实际上就是一个类体部分，相当于匿名内部类的雷替部分。
        // 也就是说，创建MALE、FEMALE枚举值时，实际上是Gender匿名子类的实例，而不是GenderInterface2的实例
        @Override
        public void info() {
            System.out.println("代表男性");
        }
    },
    FEMALE("女") {
        @Override
        public void info() {
            System.out.println("代表女性");
        }
    };
    private final String name;

    private GenderInterface2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}