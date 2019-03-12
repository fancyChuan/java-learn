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


enum Gender3 {
    MALE("男"), FEMALE("女");
    private final String name;

    private Gender3(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}