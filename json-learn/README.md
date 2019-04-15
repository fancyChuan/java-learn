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

#### FastJSON
com.alibaba.fastjson.JSON
- 序列化成json字符串： JSON.toJSONString(obj)
- 反序列化成对象： JSON.parseObject(str, Xxx.Class)，跟官方JSON相比，只需要传入对象类就可以
- 列表对象序列化： JSON.toJSONString(list);
- 列表对象的字符串格式反序列化： JSON.parseObject(JSONStr, new TypeReference<List<Student>>() {})

参见 [OfficalJSON](https://github.com/fancyChuan/java-learn/blob/master/json-learn/src/main/java/fastjson/FastJSON.java)

#### 其他
【maven地址】
- 官方JSON：[https://mvnrepository.com/artifact/org.json/json](https://mvnrepository.com/artifact/org.json/json)
- fastjson:[https://mvnrepository.com/artifact/com.alibaba/fastjson](https://mvnrepository.com/artifact/com.alibaba/fastjson)

【参考文档】
1. [Java学习总结（二十）——JSON解析：官方解析，GSON解析，FastJSON解析，-墨营的博客-51CTO博客](https://blog.51cto.com/13501268/2129213)