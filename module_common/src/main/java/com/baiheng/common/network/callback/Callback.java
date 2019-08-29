package com.baiheng.common.network.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
* author：zhewen
* description：
* date： 8/29/2019
* version：
*/
public abstract class Callback<T> {

    public void onBefore(Request request, int id) {

    }

    public void onAfter(int id) {

    }

    public void inProgress(float progress, long total, int id) {

    }

    public boolean validateReponse(Response response, int id)
    {
        return response.isSuccessful();
    }

    public abstract T parseNetworkResponse(Response response, int id) throws Exception;

    public abstract void onError(Call call, Exception e, int id);

    public abstract void onResponse(T response, int id);

    // todo
    public static Callback CALLBACK_DEFAULT = new Callback()
    {

        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {

        }

        @Override
        public void onResponse(Object response, int id)
        {

        }
    };
}
