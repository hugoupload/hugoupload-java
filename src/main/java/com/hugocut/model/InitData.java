package com.hugocut.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InitData {
    private String fileName;
    private int totalParts;
    private long totalSize;
    private String user;
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getTotalParts() {
        return totalParts;
    }

    public void setTotalParts(int totalParts) {
        this.totalParts = totalParts;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getUser() {
        if (user == "") {
            user = "";
        }
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public InitData() {

    }


    public static void main(String[] args) throws JsonProcessingException {
        InitData id = new InitData();
        id.setFileName("aaaa");
        ObjectMapper mapper = new ObjectMapper();

        String out = mapper.writeValueAsString(id);
        System.out.println(out);
        InitData id2 = new InitData();
        String p = "{\"user\": \"cander\"}";
        id2 = mapper.readValue(p,InitData.class);
        System.out.println(id2.user);
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        InitData id2 = new InitData();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return  "";
        }
    }
}
