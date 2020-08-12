package com.leo.myarchitecture

import android.app.Application
import com.leo.data.DataManger
import com.leo.manger.aspectj.annotation.Record


/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */

class MyApplication : Application() {

    @Record
    override fun onCreate() {
        super.onCreate()
        DataManger.initData(this, BuildConfig.DEBUG)
//        BlockCanary.install(this, AppBlockCanary()).start()
//        LeakCanary.install(this)
    }

//    class AppBlockCanary : BlockCanaryContext() {
//        override fun provideBlockThreshold(): Int {
//            return 1000
//        }
//    }

}