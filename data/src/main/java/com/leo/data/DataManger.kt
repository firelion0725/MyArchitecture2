package com.leo.data

import android.app.Application

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */

object DataManger {

    private lateinit var application: Application
    var debug: Boolean = true

    fun initData(application: Application, debug: Boolean) {
        this.application = application
        this.debug = debug
    }

    fun getApplication(): Application {
        return application;
    }


}