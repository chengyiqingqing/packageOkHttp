package com.sww.myapplication.test;

import com.sww.myapplication.api.ApiUtil;

import java.util.HashMap;

public class TestBookApi extends ApiUtil {

    String response;

    @Override
    public void getSuccessData(String string) {
        response = string;
    }

    @Override
    public String getUrl() {
        return "http://httpbin.org/get?id=sww&sww=false";
    }

    public String getResponse() {
        return response;
    }
}
