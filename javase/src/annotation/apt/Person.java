package annotation.apt;

@Persistent(table="person_inf")
public class Person {
    @Id(column = "person_id", type = "integer", generator = "identity")
    private int id;
    @Property(column = "person_name", type = "string")
    private String name;
    @Property(column = "person_age", type = "integer")
    private int age;

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
