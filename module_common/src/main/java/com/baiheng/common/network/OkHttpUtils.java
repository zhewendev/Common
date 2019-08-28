package com.baiheng.common.network;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

/**
 * created by zhewen
 * date: 2019/8/28
 */
public class OkHttpUtils {

    public static final long DEFAULT_MILLISECONDS = 30000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient(); //todo 待定制化
        } else {
            mOkHttpClient = okHttpClient;
        }
        mPlatform = Platform.get(); // todo
    }

    /**
     * Initialize OkHttpUtils Object
     * @param okHttpClient
     * @return
     */
    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance() {
        return initClient(null);
    }

}
