package com.leo.myarchitecture.cpp;

import android.content.Context;

/**
 * @author Leo.ZhangTJ
 * @time 2020/8/12
 * @function
 * @describe
 */
public class TestNdk {

    static {
        System.loadLibrary("leo-lib");
    }

    public static native String getStringFromC();

    public static native int getSum(int a, int b);

    public static native void showToast(Context context, String string);
}
