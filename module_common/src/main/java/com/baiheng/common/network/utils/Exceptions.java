package com.baiheng.common.network.utils;

/**
 * author：zhewen description： date： 4/18/2020 version：
 */
public class Exceptions {
    public static void illegalArgument(String msg, Object... params) {
        
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
