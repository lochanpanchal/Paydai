package com.example.Project_paydai.Model;

import java.util.List;

public class NewsUrlResponse {
    private List<String> urls;

    public NewsUrlResponse(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}

