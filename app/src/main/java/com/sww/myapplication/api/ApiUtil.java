package com.sww.myapplication.api;

import com.sww.myapplication.callback.OkHttpCallBack;
import com.sww.myapplication.utils.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public abstract class ApiUtil {

    ApiListener apiListener;
    HashMap<String,String> hashMap;

    OkHttpCallBack okHttpCallBack = new OkHttpCallBack() {
        @Override
        public void onSuccess(Call call, String string) {
            //定义抽象方法，将string传递出去；
            getSuccessData(string);
            apiListener.success(ApiUtil.this);
        }

        @Override
        public void onFailure(Call call, IOException e) {
            super.onFailure(call, e);
            apiListener.failure(ApiUtil.this);
        }
    };

    public abstract void getSuccessData(String string);

    public abstract String getUrl();

    public void get(ApiListener apiListener){
        this.apiListener = apiListener;
        OkHttpUtil.get(getUrl(),okHttpCallBack);
    }

    public void post(ApiListener apiListener){
        this.apiListener = apiListener;
        OkHttpUtil.post(getUrl(),hashMap,okHttpCallBack);
    }

    public void addParams(HashMap<String,String> hashMap){
        this.hashMap = hashMap;
    }

}
