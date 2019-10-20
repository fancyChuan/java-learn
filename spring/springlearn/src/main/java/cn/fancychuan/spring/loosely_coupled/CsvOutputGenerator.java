package cn.fancychuan.spring.loosely_coupled;

public class CsvOutputGenerator implements IOutputGenerator {
    @Override
    public void generateOutput() {
        System.out.println("Creating CsvOutputGenerator  Output......");
    }
}
