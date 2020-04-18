package com.baiheng.common.network.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import com.baiheng.common.network.request.RequestCall;

/**
 * author：zhewen description：Base class of all Request.Builder date： 4/18/2020 version：
 */
public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> {
    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected int id;
    
    public T id(int id) {
        
        this.id = id;
        return (T) this;
    }
    
    public T url(String url) {
        
        this.url = url;
        return (T) this;
    }
    
    public T tag(Object tag) {
        
        this.tag = tag;
        return (T) this;
    }
    
    public T headers(Map<String, String> headers) {
        
        this.headers = headers;
        return (T) this;
    }
    
    public T addHeaders(String key, String value) {
        
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, value);
        return (T) this;
    }
    
    public abstract RequestCall build();
    
}
