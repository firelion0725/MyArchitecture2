package com.leo.myarchitecture

import android.app.Application
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.leo.data.DataManger
import com.smartzheng.launcherstarter.utils.DispatcherLog.isDebug


/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DataManger.initData(this, BuildConfig.DEBUG)

        if (!isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

//        BlockCanary.install(this, AppBlockCanary()).start()
//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            LeakCanary.install(this)
//        }
    }

//    class AppBlockCanary : BlockCanaryContext() {
//        override fun provideBlockThreshold(): Int {
//            return 1000
//        }
//    }

}