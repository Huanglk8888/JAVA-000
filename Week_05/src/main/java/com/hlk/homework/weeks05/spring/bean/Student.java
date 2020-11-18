package com.hlk.homework.weeks05.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private String name = "huanglk";
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
