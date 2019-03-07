package base.oop.innerClass;

class StaticOut {
    static class StaticIn {
        public StaticIn() {
            System.out.println("静态内部类正在实例啦");
        }
    }
}

public class CreateStaticInnerInstance {
    public static void main(String[] args) {
        StaticOut.StaticIn staticIn = new StaticOut.StaticIn();
        // 上面的代码等价于下面两行
        StaticOut.StaticIn in;
        in = new StaticOut.StaticIn();
    }
}

/**
 * 这里外部类很想是内部类的包空间
 */
class SubClassStatic extends StaticOut.StaticIn {

}
