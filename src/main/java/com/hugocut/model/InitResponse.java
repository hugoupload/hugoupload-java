package com.hugocut.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InitResponse extends  Response {
    DataForm data;

    public DataForm getData() {
        return data;
    }
    public void setData(DataForm data) {
        this.data = data;
    }

    public static InitResponse toObject(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return   mapper.readValue(s, InitResponse.class);
    }
}
