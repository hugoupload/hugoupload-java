package com.hugocut.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UploadResponse extends Response {
    UploadData data;

    public UploadData getData() {
        return data;
    }

    public void setData(UploadData data) {
        this.data = data;
    }

    public static UploadResponse toObject(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return   mapper.readValue(s, UploadResponse.class);
    }
}
