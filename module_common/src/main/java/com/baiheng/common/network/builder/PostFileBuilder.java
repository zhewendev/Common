package com.baiheng.common.network.builder;

import java.io.File;

import com.baiheng.common.network.request.PostFileRequest;
import com.baiheng.common.network.request.RequestCall;

import okhttp3.MediaType;

/**
 * author: zhewen description: date: 4/18/2020 version:
 */
public class PostFileBuilder extends OkHttpRequestBuilder<PostFileBuilder> {
    private File file;
    private MediaType mediaType;
    
    public OkHttpRequestBuilder file(File file) {
        
        this.file = file;
        return this;
    }
    
    public OkHttpRequestBuilder mediaType(MediaType mediaType) {
        
        this.mediaType = mediaType;
        return this;
    }
    
    @Override
    public RequestCall build() {
        
        return new PostFileRequest(url, tag, params, headers, file, mediaType, id).build();
    }
}
