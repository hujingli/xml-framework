package com.hujingli.xmler.bean;

public class TransDefine {

    private Class service;
    private Class inputType;
    private Class outputType;

    public TransDefine(Class service, Class inputType, Class outputType) {
        this.service = service;
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public Class getService() {
        return service;
    }

    public void setService(Class service) {
        this.service = service;
    }

    public Class getInputType() {
        return inputType;
    }

    public void setInputType(Class inputType) {
        this.inputType = inputType;
    }

    public Class getOutputType() {
        return outputType;
    }

    public void setOutputType(Class outputType) {
        this.outputType = outputType;
    }

    @Override
    public String toString() {
        return "TransDefine{" +
                "service=" + service +
                ", inputType=" + inputType +
                ", outputType=" + outputType +
                '}';
    }
}
