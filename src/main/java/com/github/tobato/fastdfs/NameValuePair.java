package com.github.tobato.fastdfs;

/**
 * 键值对模型
 * 
 * @author yuqih
 *
 */
public class NameValuePair {
    protected String name;
    protected String value;

    public NameValuePair() {
    }

    public NameValuePair(String name) {
        this.name = name;
    }

    public NameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
