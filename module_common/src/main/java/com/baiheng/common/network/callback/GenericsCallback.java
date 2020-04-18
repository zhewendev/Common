package com.baiheng.common.network.callback;

import java.lang.reflect.ParameterizedType;

import com.google.gson.Gson;

import okhttp3.Response;

/**
 * author: zhewen description: date: 4/18/2020 version:
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
        T bean = new Gson().fromJson(string, entityClass);
        return bean;
    }
}
