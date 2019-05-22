package com.sww.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sww.myapplication.api.ApiListener;
import com.sww.myapplication.api.ApiUtil;
import com.sww.myapplication.test.TestBookApi;
import com.sww.myapplication.test.TestBookPostApi;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testPost();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                testGet();
//                testGetNew();
                testPostNew();
            }
        }).start();
    }

    public void testGetNew(){
        new TestBookApi().get(new ApiListener() {
            @Override
            public void success(ApiUtil apiUtil) {
                String string = ((TestBookApi)apiUtil).getResponse();
                Log.e(TAG, "success: "+string );
            }

            @Override
            public void failure(ApiUtil apiUtil) {
                String string = ((TestBookApi)apiUtil).getResponse();
                Log.e(TAG, "failure: "+string );
            }
        });
    }

    public void testPostNew(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("sww_hello1","sww_sleep");
        new TestBookPostApi(hashMap).post(new ApiListener() {
            @Override
            public void success(ApiUtil apiUtil) {
                String string = ((TestBookPostApi)apiUtil).getResponse();
                Log.e(TAG, "success: "+string );
            }

            @Override
            public void failure(ApiUtil apiUtil) {
                String string = ((TestBookApi)apiUtil).getResponse();
                Log.e(TAG, "failure: "+string );
            }
        });
    }

    public void testGet() {
        //1.创建HttpClient;
        OkHttpClient client = new OkHttpClient();
        //2.创建HttpRequest;
        Request request = new Request.Builder()
                .url("http://httpbin.org/get?id=sww&sww=false")
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.e(TAG, "testGet: "+response.body().string() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testPost() {
        //1.创建HttpClient;
        OkHttpClient client = new OkHttpClient();
        //2.创建HttpRequest;
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, "{\"sww\":\"haha\"}");
        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.e(TAG, "testGet: " + response.body().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
