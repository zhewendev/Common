package com.baiheng.common.network.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

import okhttp3.Response;

/**
* author: zhewen
* description:
* date: 8/30/2019
* version:
*/
public abstract class GenericsCallback<T> extends Callback<T> {
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {

        String string = response.body().string();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }
        T bean = new Gson().fromJson(string,entityClass);
        return bean;
    }
}
