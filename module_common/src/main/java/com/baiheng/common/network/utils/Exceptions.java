package com.baiheng.common.network.utils;

/**
* author：zhewen
* description：
* date： 8/29/2019
* version：
*/
public class Exceptions {
    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
