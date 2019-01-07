package com.fancychuan.spring.loosely_coupled;

public class OutputHelper {
    IOutputGenerator outputGenerator;

    public void generateOutput() {
        this.outputGenerator.generateOutput(); // TODO: 为什么这里要用this
    }

    public void setOutputGenerator(IOutputGenerator outputGenerator) {
        this.outputGenerator = outputGenerator;
    }
}
