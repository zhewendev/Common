package com.baiheng.common.network.request;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * author：zhewen description：Method is the Request of GET date： 4/18/2020 version：
 */
public class GetRequest extends OkHttpRequest {
    
    public GetRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        
        super(url, tag, params, headers, id);
    }
    
    @Override
    protected RequestBody buildRequestBody() {
        
        return null;
    }
    
    @Override
    protected Request buildRequest(RequestBody requestBody) {
        
        return builder.get().build();
    }
}
