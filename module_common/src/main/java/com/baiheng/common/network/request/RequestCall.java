package com.baiheng.common.network.request;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by zhewen
 * date:2019/8/28
 * Encapsulating OkHttp, provide more interface to the outside
 */
public class RequestCall {

    private OkHttpRequest okHttpRequest;
    private Request request;
    private Call call;
    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;

    private OkHttpClient clone;

    public RequestCall(OkHttpRequest okHttpRequest) {
        this.okHttpRequest = okHttpRequest;
    }

    public RequestCall readTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public RequestCall writeTimeOut(long writeTimeOut)
    {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public RequestCall connTimeOut(long connTimeOut)
    {
        this.connTimeOut = connTimeOut;
        return this;
    }

    public Call buildCall(Callback callback) {

    }

    public Request generateRequest(Callback callback) {
        return okHttpRequest.
    }
}
