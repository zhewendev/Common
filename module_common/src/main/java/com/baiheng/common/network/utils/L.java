package com.baiheng.common.network.utils;

import android.util.Log;

/**
 * author：zhewen description： date： 4/18/2020 version：
 */
public class L {
    private static boolean debug = false;
    
    public static void e(String msg) {
        
        if (debug) {
            Log.e("OkHttp", msg);
        }
    }
    
}
