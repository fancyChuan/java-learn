package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import utils.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FastJSON {
    // 1.把Javabean转换成JSON
    public static String javaBeanToJSON() {
        Student stu = new Student(11, "虞姬", 96.8);
        return JSON.toJSONString(stu);

    }

    // 2.把JSON转换成Javabean
    public static Student jsonToJavaBean() {
        String jsonStr = "{'id':2,'name':'杨玉环','score':95.6}";
        return JSON.parseObject(jsonStr, Student.class); // 这个就比官方的JSON方便很多了，只需要传入一个类就可以
    }

    // 3.把集合对象转换成JSON
    public static String listToJSON() {
        List<Student> list = new ArrayList<Student>();
        Collections.addAll(list, new Student(3, "西施", 93.6), new Student(4,
                "貂蝉", 95.3), new Student(5, "平昭阳公主", 94.6));
        return JSON.toJSONString(list);

    }

    // 4.把数组形式的JSON转换成集合对象
    public static List<Student> jsonToList() {
        String JSONStr = "[{\"id\":20,\"name\":\"郭靖\",\"score\":60.5},{\"id\":21,\"name\":\"黄蓉\",\"score\":65.5}]";
        return JSON.parseObject(JSONStr, new TypeReference<List<Student>>() {
        });
    }


    public static void main(String[] args) {
        // 1.把Javabean转换成JSON
        String jsonStr = javaBeanToJSON();
        System.out.println("1.把Javabean转换成JSON:"+jsonStr);

        // 2.把JSON转换成Javabean
        Student stu = jsonToJavaBean();
        System.out.println("2.把JSON转换成Javabean:"+stu);

        // 3.把集合对象转换成JSON
        String jsonArrStr = listToJSON();
        System.out.println("3.把集合对象转换成JSON:"+jsonArrStr);

        // 4.把数组形式的JSON转换成集合对象
        System.out.println("4.把数组形式的JSON转换成集合对象:");
        List<Student> list = jsonToList();
        for(Student stu1:list){
            System.out.println(stu1);
        }
    }
}
