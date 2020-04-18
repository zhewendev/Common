package com.baiheng.common.network.callback;

/**
 * author: zhewen description: date: 4/18/2020 version:
 */
public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
