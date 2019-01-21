package com.fancychuan.onetoone.model;

import com.fancychuan.onetomany.model.Student;

import java.util.List;

public class Classes {
    private Integer id;
    private String name;
    private HeadTeacher teacher;
    private List<Student> students;

    public Classes() {
    }

    public Classes(Integer id, String name, HeadTeacher teacher, List<Student> students) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.students = students;
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

    public HeadTeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(HeadTeacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
