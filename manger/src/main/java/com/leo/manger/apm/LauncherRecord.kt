package com.leo.manger.apm

import android.util.Log
import kotlin.properties.Delegates

/**
 * @author Leo.ZhangTJ
 * @time 2020/8/11
 * @function
 * @describe
 */
class LauncherRecord {

    companion object {
        private const val TAG = "LauncherRecord"

        //BuildConfig.DEBUG
        var debug: Boolean = false
        private var startTime by Delegates.notNull<Long>()

        fun startLauncherRecord() {
            startTime = System.currentTimeMillis()
        }

        fun endLauncherRecord() {
            val cost = System.currentTimeMillis() - startTime
            Log.i(TAG, "===========cost: $cost")
        }

        fun startTracing() {
            if (debug) {
//                TraceCompat.beginSection("apm")
//            Debug.startMethodTracing("apm")
            }

        }

        fun stopTracing() {
            if (debug) {
//                TraceCompat.endSection()
//                Debug.stopMethodTracing()
            }
        }
    }

}