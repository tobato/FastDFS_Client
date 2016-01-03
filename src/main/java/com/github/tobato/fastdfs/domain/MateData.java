package com.github.tobato.fastdfs.domain;

/**
 * 文件元数据(MateData)
 * 
 * @author tobato
 *
 */
public class MateData {

    private String name;

    private String value;

    public MateData() {
    }

    public MateData(String name) {
        this.name = name;
    }

    public MateData(String name, String value) {
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

    @Override
    public String toString() {
        return "NameValuePair [name=" + name + ", value=" + value + "]";
    }

}
