package com.sww.myapplication.utils;

import com.sww.myapplication.callback.OkHttpCallBack;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {

    private static OkHttpClient okHttpClient;

    public static void init(){
        if (okHttpClient==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5000, TimeUnit.MILLISECONDS);
            okHttpClient = builder.build();

        }
    }

    public static void get(String url, OkHttpCallBack okHttpCallBack) {
        HashMap<String,String> hashMap = null;
        // TODO: 从apiCommonParams获取 2019/5/22
        url = getFinalStringUrl(url,hashMap);
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(okHttpCallBack);
    }

    public static void post(String url, HashMap<String,String> map, OkHttpCallBack okHttpCallBack){
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            formBodyBuilder.add(entry.getKey(),entry.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(okHttpCallBack);
    }

    /**
     * 得到追加公共参数的url;
     */
    public static String getFinalStringUrl(String url,HashMap<String,String> hashMap){

        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (HashMap.Entry<String, String> entry : hashMap.entrySet()) {
                if (stringBuilder.length()>0) stringBuilder.append("&");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(entry.getValue(),"utf-8"));
            }
            String paraString = stringBuilder.toString();
            if (url.contains("?")){
                url+=("&"+paraString);
            }else {
                url+=("?"+paraString);
            }
            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
