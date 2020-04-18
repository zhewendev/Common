package com.baiheng.common.network.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * author: zhewen description: date: 4/18/2020 version:
 */
public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        
        return response.body().string();
    }
}
