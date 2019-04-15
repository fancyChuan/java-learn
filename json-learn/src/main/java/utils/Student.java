package utils;

public class Student {
    Integer id;
    String name;
    Double score;

    public Student(Integer id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String toString() {
        return "Student [id=" + id + " name=" + name + " score=" + score + "]";
    }
}
