package com.hugocut.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CompleteResponse extends Response {

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    int[] data;

    public static CompleteResponse toObject(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(s);
        CompleteResponse r =  mapper.readValue(s, CompleteResponse.class);
        if (r.code == 0) {
            return  r;
        } else {
            return  r;
        }
    }
}
