package com.sww.myapplication.api;

public interface ApiListener {

    /**
     * 请求成功；
     * @param apiUtil
     */
    void success(ApiUtil apiUtil);

    void failure(ApiUtil apiUtil);

}
