package com.sww.myapplication.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class OkHttpCallBack implements Callback {

    public abstract void onSuccess(Call call, String string);

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response != null) {
            String string = response.body().string().toString();
            onSuccess(call, string);
        } else {
            onFailure(call, null);
        }
    }

}
