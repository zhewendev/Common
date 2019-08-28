package com.baiheng.common.network.utils;

/**
 * Created by zhewen
 * date:2019/8/28
 */
public class Exceptions {
    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
