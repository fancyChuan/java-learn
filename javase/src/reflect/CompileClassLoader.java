package reflect;


import java.io.*;
import java.lang.reflect.Method;

/**
 * 20190327 自定义类加载器
 *
 * 终端命令： E:\JavaWorkshop\java-learn\javase\src>java reflect.CompileClassLoader reflect.TestClassLoader hello fancy world
 *
 * 在这个地方，需要十分注意路径的问题。首先类名要用：包名+类名，同时要在 src 目录下，不然总会找不到路径
 * 执行之前，需要先编译 CompileClassLoader，命令为： E:\JavaWorkshop\java-learn\javase\src>javac -encoding utf-8 reflect\CompileClassLoader.java
 */
public class CompileClassLoader extends ClassLoader {

    // 读取一个文件的内容
    private byte[] getBytes(String name) throws IOException {
        File file = new File(name);
        byte[] raw = new byte[(int) file.length()];
        try (
                FileInputStream fin = new FileInputStream(file);
                )
        {
            int r = fin.read(raw); // 一次性读取class文件的全部二进制数据
            if (r != file.length()) {
                throw new IOException("无法读取全部文件");
            }
            return raw;
        }
    }

    // 定义编译指定java文件的方法
    public boolean compile(String javaFile) throws IOException {
        System.out.println("======= 进入编译环节 =======");
        System.out.println("正在编译： " + javaFile + " ...");
        System.out.println("当前路径： " + ClassLoader.getSystemResource(""));
//        System.out.println("当前路径path： " + ClassLoader.getSystemResource("").getPath());
        javaFile = ClassLoader.getSystemResource("").getPath() + File.separator + javaFile; // 这一行是使用了绝对路径，用相对路径的时候，在Main中是无法执行的
        Process p = Runtime.getRuntime().exec("javac -encoding utf-8 " + javaFile); // 这是一个新的进程，要特别注意环境的问题

        try {
            p.waitFor();
            String msg = new BufferedReader(new InputStreamReader(p.getErrorStream(),"GBK")).readLine(); // 编译过程的错误信息
            if (msg != null) {
                System.out.println("[error] " + msg);
            } else {
                System.out.println("编译成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        int ret = p.exitValue();
        System.out.println("======= 编译结束 =======");
        return ret == 0;
    }

    // 重写findClass() 方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String fileStub = name.replace(".", File.separator); // File.separator
        String javaFileName = fileStub + ".java";
        String classFileName = fileStub + ".class";
        File javaFile = new File(javaFileName);
        File classFile = new File(classFileName);

        if (javaFile.exists() && (!classFile.exists() || classFile.lastModified() < javaFile.lastModified())) {
            try {
                if(!compile(javaFileName) || !classFile.exists()) {
                    throw new ClassNotFoundException("ClassNotFoundException: " + classFileName);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (classFile.exists()) {
            try {
                byte[] raw = getBytes(classFileName);
                clazz = defineClass(name, raw, 0, raw.length);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }

        return clazz;
    }


    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("缺少目标类，请按格式执行： java CompileClassLoader className");
        }

        String progClass = args[0];
        System.out.println("将要加载的类是： " + progClass);
        String[] progArgs = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);

        CompileClassLoader ccl = new CompileClassLoader();
        Class<?> clazz = ccl.loadClass(progClass);
        System.out.println("加载得到对象：" + clazz);
        System.out.println(new String[0]);
        System.out.println((new String[0]).getClass());

        Method main = clazz.getMethod("main", (new String[0]).getClass());
        Object argsArray[] = {progArgs};
        main.invoke(null, argsArray);
    }
}
