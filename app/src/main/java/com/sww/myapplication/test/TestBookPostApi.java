package com.sww.myapplication.test;

import com.sww.myapplication.api.ApiUtil;

import java.util.HashMap;

public class TestBookPostApi extends ApiUtil {

    String response;

    public TestBookPostApi(HashMap<String,String> hashMap){
        addParams(hashMap);
    }

    @Override
    public void getSuccessData(String string) {
        response = string;
    }

    @Override
    public String getUrl() {
        return "http://httpbin.org/post";
    }

    public String getResponse() {
        return response;
    }
}
