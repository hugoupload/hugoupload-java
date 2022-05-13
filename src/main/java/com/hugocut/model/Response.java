package com.hugocut.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    int code;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return   mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}


