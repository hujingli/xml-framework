package com.hujingli.xmler.bean;

public class TransRspContext<T> {

    private T context;

    public TransRspContext(T context) {
        this.context = context;
    }

    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }
}
