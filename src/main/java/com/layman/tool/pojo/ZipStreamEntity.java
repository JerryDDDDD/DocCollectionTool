package com.layman.tool.pojo;

/**
 * zip相关  流文件实体类
 */

import java.io.InputStream;


public class ZipStreamEntity {

    public String name;
    public InputStream inputstream;

    public ZipStreamEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ZipStreamEntity(String name, InputStream inputstream) {
        super();
        this.name = name;
        this.inputstream = inputstream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getInputstream() {
        return inputstream;
    }

    public void setInputstream(InputStream inputstream) {
        this.inputstream = inputstream;
    }

}
