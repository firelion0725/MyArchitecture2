package com.leo.myarchitecture.cpp;

import com.leo.data.model.BannerTitleModel;

import io.reactivex.Single;

/**
 * @author Leo.ZhangTJ
 * @time 2020/8/26
 * @function
 * @describe
 */
public class CppHttp {

    static {
        System.loadLibrary("http-lib");
    }

    public native  String testString();


}
