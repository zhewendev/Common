package com.baiheng.common.network.request;


import com.baiheng.common.network.callback.Callback;
import com.baiheng.common.network.utils.Exceptions;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
* author：zhewen
* description：base class of all Request
* date： 8/29/2019
* version：
*/
public abstract class OkHttpRequest {

    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;

    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers, int id) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        this.id = id;
        if (url == null) {
            Exceptions.illegalArgument("url can not be null");
        }

        initBuilder();
    }

    /**
     * Initialize some basic params,such as url,tag,headers
     */
    private void initBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    protected abstract RequestBody buildRequestBody();  //

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        return requestBody;
    }

    protected abstract Request buildRequest(RequestBody requestBody);   // Structural RequestBody(the GET method is empty)

    public RequestCall build() {
        return new RequestCall(this);
    }

    public Request generateRequest(Callback callback)
    {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody, callback);
        Request request = buildRequest(wrappedRequestBody);
        return request;
    }


    /**
     * Convert headers of type Map to type Headers, then Initialize it
     */
    protected void appendHeaders() {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headers == null) {
            return;
        }
        for (String key : headers.keySet()) {
            headersBuilder.add(key, headers.get(key));
        }
        builder.headers(headersBuilder.build());
    }

    public int getId() {
        return id;
    }

}
