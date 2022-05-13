package com.hugocut.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataForm {
    public long getUploadId() {
        return uploadId;
    }

    public void setUploadId(long uploadId) {
        this.uploadId = uploadId;
    }

    long uploadId;


    public static long toObject(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(s);
        return  mapper.readValue(s, DataForm.class).uploadId;
    }
}



