package base.io;

import java.io.*;

/**
 * 对象序列化读写
 *
 * 把对象序列化到文件中，会有乱码的情况，但是用同样的方式读取是没问题的
 *
 * 比如下面的案例拿到的就是：
 * �� sr base.io.Person�Dc����> I ageL namet Ljava/lang/String;xp   t 这是我的
 *
 */
public class ObjectSerializable {

    public static void main(String[] args) {
        testWriteObject();
        testReadObject();
        testChangeObject();
    }


    public static void testWriteObject() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/base/io/object.txt"))) {
            Person person = new Person("这是我的", 23);
            output.writeObject(person);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // 改变一个对象的属性，重新序列化的时候，只返回序列化编号，并不会再次序列化
    public static void testReadObject() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/base/io/object.txt"))) {
            Person person = (Person) input.readObject();
            System.out.println(person.getName() + " and age:" + person.getAge());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void testChangeObject() {
        try (
                ObjectOutputStream output = new ObjectOutputStream(
                        new FileOutputStream("src/base/io/object-mutable.txt"));
                ObjectInputStream input = new ObjectInputStream(
                        new FileInputStream("src/base/io/object-mutable.txt"))
        ) {
            Person person = new Person("孙悟空", 500);
            output.writeObject(person);

            person.setName("猪八戒");
            output.writeObject(person);

            Person p1 = (Person) input.readObject();
            Person p2 = (Person) input.readObject();

            System.out.println(p1==p2);
            System.out.println(p1.equals(p2));
            System.out.println(p2.getName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
