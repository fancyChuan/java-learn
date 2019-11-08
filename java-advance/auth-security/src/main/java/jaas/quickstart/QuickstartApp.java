package jaas.quickstart;

public class QuickstartApp {
    public static void main(String[] args) {
        String classPath = QuickstartApp.class.getResource("/").getPath(); // // 不带包路径的QuickstartApp类所在的绝对路径
        String classPathWithPackage = QuickstartApp.class.getResource("").getPath(); // 带包路径的QuickstartApp类所在的绝对路径
        System.out.println(classPath);
        System.out.println(classPathWithPackage);
        System.out.println(System.getProperty("java.home")); // 可以获取到属性
        System.out.println(System.getProperty("user.home"));
//        System.setProperty("java.security.policy", "auth-security/src/main/java/jaas/quickstart/demo.policy");
        System.setProperty("java.security.policy", classPathWithPackage + "demo.policy");
        System.setSecurityManager(new SecurityManager());  // 使用了安全管理器之后下面的代码就报错了，如果没有设置policy权限策略文件的话
        System.out.println(System.getProperty("java.home"));
    }
}
