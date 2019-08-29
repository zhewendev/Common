package com.baiheng.common.network.builder;

import java.util.Map;

/**
* author：zhewen
* description：Extend the ability of url to add custom parameters
* date： 8/29/2019
* version：
*/
public interface HasParamsable {
    OkHttpRequestBuilder params(Map<String, String> params);    //Custom parameter set added at the tail of the url
    OkHttpRequestBuilder addParams(String key, String value);   //Add a custom parameter key pair at the end of url
}
