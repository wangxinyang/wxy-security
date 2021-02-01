package com.security.support;

import org.springframework.http.HttpStatus;

public class SimpleResponse {

    private HttpStatus code;
    private String content;

    public SimpleResponse(HttpStatus code, String content) {
        this.code = code;
        this.content = content;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
