package com.security.core.properties;

public enum LoginType {

    HTML(1, "浏览器模式"),

    JSON(2, "json模式");

    int code;

    String desc;

    LoginType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
