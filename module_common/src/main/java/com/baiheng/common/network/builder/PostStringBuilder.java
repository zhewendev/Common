package com.baiheng.common.network.builder;

import com.baiheng.common.network.request.PostStringRequest;
import com.baiheng.common.network.request.RequestCall;

import okhttp3.MediaType;

/**
* author: zhewen
* description:
* date: 8/30/2019
* version:
*/
public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder> {

    private String content;
    private MediaType mediaType;

    public PostStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, params, headers, content, mediaType, id).build();
    }
}
