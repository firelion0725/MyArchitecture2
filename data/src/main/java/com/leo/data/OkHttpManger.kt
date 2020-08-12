package com.leo.data

import android.util.Log
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */

object OkHttpManger {


    private const val TIME_OUT: Long = 120

    val instance: OkHttpClient = getHttpClient()

    private fun getHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder =
            OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        if (DataManger.debug) {
            builder.addNetworkInterceptor(getHttpLogInterceptor())
            builder.addInterceptor(ChuckInterceptor(DataManger.getApplication()))
        }

        return builder.build()
    }


    private class HttpLogger : HttpLoggingInterceptor.Logger {

        override fun log(message: String) {
            Log.d("OK http ===", message) //okHttp的详细日志会打印出来
        }
    }

    private fun getHttpLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLogger())
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


}