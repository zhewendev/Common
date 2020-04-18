package com.baiheng.common.network.builder;

import com.baiheng.common.network.OkHttpUtils;
import com.baiheng.common.network.request.OtherRequest;
import com.baiheng.common.network.request.RequestCall;

/**
 * author: zhewen description: date: 4/18/2020 version:
 */
public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
