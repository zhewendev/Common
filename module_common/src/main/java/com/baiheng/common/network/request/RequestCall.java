package com.baiheng.common.network.request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.baiheng.common.network.OkHttpUtils;
import com.baiheng.common.network.callback.Callback;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author：zhewen description：Encapsulating OkHttp, provide more interface to the outside date：
 * 8/29/2019 version：
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
    
    public RequestCall writeTimeOut(long writeTimeOut) {
        
        this.writeTimeOut = writeTimeOut;
        return this;
    }
    
    public RequestCall connTimeOut(long connTimeOut) {
        
        this.connTimeOut = connTimeOut;
        return this;
    }
    
    /**
     * turn Request into a Call
     * 
     * @param callback
     * @return
     */
    public Call buildCall(Callback callback) {
        
        request = generateRequest(callback); // Submit RequestBody data in Request
        if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0) {
            readTimeOut = readTimeOut > 0 ? readTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            connTimeOut = connTimeOut > 0 ? connTimeOut : OkHttpUtils.DEFAULT_MILLISECONDS;
            // Clone a OkHttpClient
            clone = OkHttpUtils.getInstance().getOkHttpClient().newBuilder()
                    .readTimeout(readTimeOut, TimeUnit.MILLISECONDS).writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                    .connectTimeout(connTimeOut, TimeUnit.MILLISECONDS).build();
            call = clone.newCall(request);
        } else {
            call = OkHttpUtils.getInstance().getOkHttpClient().newCall(request);
        }
        return call;
    }
    
    /**
     * Generate the final Request
     * 
     * @param callback
     * @return
     */
    private Request generateRequest(Callback callback) {
        
        return okHttpRequest.generateRequest(callback);
    }
    
    /**
     * asynchronous request
     * 
     * @param callback
     */
    public void execute(Callback callback) {
        
        buildCall(callback);
        if (callback != null) {
            callback.onBefore(request, getOkHttpRequest().getId());
        }
        OkHttpUtils.getInstance().execute(this, callback);
    }
    
    public Call getCall() {
        
        return call;
    }
    
    public OkHttpRequest getOkHttpRequest() {
        
        return okHttpRequest;
    }
    
    /**
     * synchronous request
     * 
     * @return Request Results
     * @throws IOException
     */
    public Response execute() throws IOException {
        
        buildCall(null);
        return call.execute();
    }
    
    public void cancel() {
        
        if (call != null) {
            call.cancel();
        }
    }
    
}
