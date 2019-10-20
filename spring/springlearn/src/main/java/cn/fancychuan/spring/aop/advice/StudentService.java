package cn.fancychuan.spring.aop.advice;

public class StudentService {
    private String name;
    private String url;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void printName() {
        System.out.println("[x]Student name : " + name);
    }

    public void printUrl() {
        System.out.println("[x]Student url : " + url);
    }

    public void printThrowException() {
        throw new IllegalArgumentException();
    }
}
