package offical;

import utils.FileOperation;
import utils.Student;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfficalJSON {
    private static String firstPath = "E:\\JavaWorkshop\\java-learn\\json-learn\\src\\main\\java\\offical\\first.json";
    private static String secondPath = "E:\\JavaWorkshop\\java-learn\\json-learn\\src\\main\\java\\offical\\second.json";

    // 1.将java对象转换成JSON数据格式
    public static String javaToJSON() {
        Student stu = new Student(1, "王昭君", 89.5);
        JSONObject object = new JSONObject(stu);
        return object.toString();
    }

    // 2.将JSON转化为JavaBean对象
    public static Student jsonToJavaBean() throws IOException {
//        String json = "{'id':2,'name':'杨玉环','score':95.6}";
        String json = FileOperation.readFile(firstPath).get(0); // 从文件读取
        JSONObject jsonObject = new JSONObject(json);
        Integer id = jsonObject.optInt("id");
        String name = jsonObject.optString("name");
        Double score = jsonObject.optDouble("score");
        Student student = new Student(id, name, score);
        return student;
    }

    // 3. 把集合转化为JSON字符串
    public static String listToJSON() {
        ArrayList<Student> students = new ArrayList<>();
        Collections.addAll(students, new Student(3, "西施", 93.6), new Student(4,
                "貂蝉", 95.3), new Student(5, "平昭阳公主", 94.6));
        JSONArray jsonArray = new JSONArray(students);
        return jsonArray.toString();
    }

    // 4.把数组形式的JSON串转化为集合对象
    public static List<Student> jsonArrayToList() throws IOException {
        List<Student> list = new ArrayList<Student>();
        // String jsonStr ="[{\"score\":93.6,\"name\":\"西施\",\"id\":3},{\"score\":95.3,\"name\":\"貂蝉\",\"id\":4},{\"score\":94.6,\"name\":\"平昭阳公主\",\"id\":5}]";
        String jsonStr = FileOperation.readFile(secondPath).get(0);

        JSONArray jsonArr=new JSONArray(jsonStr);
        for(int i=0; i<jsonArr.length(); i++){
            JSONObject jsonObj=jsonArr.optJSONObject(i); //用于获取每一个对象
            int id=jsonObj.optInt("id");
            String name=jsonObj.optString("name");
            double score=jsonObj.optDouble("score");
            Student stu=new Student(id,name,score);
            list.add(stu);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        //1.JavaBean转化为JSON数据格式
        String first = javaToJSON();
        System.out.println("1.JavaBean转化为JSON数据格式:" + first);
        FileOperation.write2file(firstPath, first);

        // 2.将JSON转化为JavaBean对象
        Student student = jsonToJavaBean();
        System.out.println("2.将JSON转化为JavaBean对象:" + student);

        // 3. 把集合转化为JSON字符串
        String jsonStr1 = listToJSON();
        System.out.println("3. 把集合转化为JSON字符串:"+jsonStr1);
        FileOperation.write2file(secondPath, jsonStr1);

        //4.把数组形式的JSON串转化为集合对象
        List<Student> list=jsonArrayToList();
        System.out.println("4.把数组形式的JSON串转化为集合对象：");
        for(Student stu1:list){
            System.out.println(stu1);
        }

    }
}
