package com.baiheng.common.network.utils;

import android.os.Build;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
* author：zhewen
* description：
* date： 8/29/2019
* version：
*/
//todo ????
public class Platform {
    private static final Platform PLATFORM = findPlatform();

    public static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Bundle");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        }catch (ClassNotFoundException ignored) {

        }
        return new Platform();
    }

    public Executor defaultCallbackExecutor() {
        return Executors.newCachedThreadPool();
    }

    public void execute(Runnable runnable) {
        defaultCallbackExecutor().execute(runnable);
    }

    static class Android extends Platform {

    }
}
