package com.baiheng.common.network.builder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.baiheng.common.network.request.GetRequest;
import com.baiheng.common.network.request.RequestCall;

import android.net.Uri;

/**
 * author：zhewen description： date： 4/18/2020 version：
 */
public class GetBuilder extends OkHttpRequestBuilder implements HasParamsable {
    
    @Override
    public RequestCall build() {
        
        if (params != null) {
            url = appendParams(url, params);
        }
        return new GetRequest(url, tag, params, headers, id).build();
    }
    
    /**
     * add custom parameters at the end of url
     * 
     * @param url
     *            request address
     * @param params
     *            custom parameter set
     * @return
     */
    protected String appendParams(String url, Map<String, String> params) {
        
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }
    
    @Override
    public GetBuilder params(Map<String, String> params) {
        
        this.params = params;
        return this;
    }
    
    @Override
    public GetBuilder addParams(String key, String value) {
        
        if (this.params == null) {
            params = new LinkedHashMap<String, String>();
        }
        params.put(key, value);
        return this;
    }
}
