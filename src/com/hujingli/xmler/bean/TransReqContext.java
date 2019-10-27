package com.hujingli.xmler.bean;

import java.io.Serializable;

public class TransReqContext<T> implements Serializable {

    private T context;

    public TransReqContext(T context) {
        this.context = context;
    }

    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }
}
