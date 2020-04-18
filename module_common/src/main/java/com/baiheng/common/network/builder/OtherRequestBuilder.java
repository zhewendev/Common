package com.baiheng.common.network.builder;

import com.baiheng.common.network.request.OtherRequest;
import com.baiheng.common.network.request.RequestCall;

import okhttp3.RequestBody;

/**
 * author：zhewen description：DELETE、PUT、PATCH Methods date： 4/18/2020 version：
 */
public class OtherRequestBuilder extends OkHttpRequestBuilder<OtherRequestBuilder> {
    private RequestBody requestBody;
    private String method;
    private String content;
    
    public OtherRequestBuilder(String method) {
        
        this.method = method;
    }
    
    @Override
    public RequestCall build() {
        
        return new OtherRequest(requestBody, content, method, url, tag, params, headers, id).build();
    }
    
    public OtherRequestBuilder requestBody(RequestBody requestBody) {
        
        this.requestBody = requestBody;
        return this;
    }
    
    public OtherRequestBuilder requestBody(String content) {
        
        this.content = content;
        return this;
    }
    
}
