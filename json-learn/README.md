## Java操作JSON数据

java中没有内置JSON的解析，需要借助第三方类库

常用的有：

- [Gson: 谷歌开发的 JSON 库，功能十分全面。](https://github.com/google/gson)
- [FastJson: 阿里巴巴开发的 JSON 库，性能十分优秀。](https://github.com/alibaba/fastjson)
- [Jackson: 社区十分活跃且更新速度很快。](https://github.com/FasterXML/jackson)

#### JSON官方解析
- 序列化成json，类似于python的json.dumps() 
```
// 注意被序列化的对象需要是类似于JavaBean规范的
public static String javaToJSON() {
        Student stu = new Student(1, "王昭君", 89.5);
        JSONObject object = new JSONObject(stu);
        return object.toString();
    }
```
- 反序列化，类似于python的json.loads()
```
// 取出数据后再还原为对象，这点和python不一样
JSONObject jsonObject = new JSONObject(json);
Integer id = jsonObject.optInt("id");
String name = jsonObject.optString("name");
Double score = jsonObject.optDouble("score");
Student student = new Student(id, name, score);
```
- 列表，使用 JSONARRAY() 操作类类似于 JSONObject() 参见 [OfficalJSON](https://github.com/fancyChuan/java-learn/blob/master/json-learn/src/main/java/offical/OfficalJSON.java)