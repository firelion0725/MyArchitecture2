package com.leo.data

import com.leo.architecture.test.data.comm.baseUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */
object RetrofitManger {

    private val retrofitMap = HashMap<String, Retrofit>()

    fun getInstance(url: String): Retrofit {
        var instance = retrofitMap[url]
        if (instance == null) {
            instance = create()
            retrofitMap[url] = instance
        }
        return instance
    }

    val instance: Retrofit = create()

    private fun create(): Retrofit {
        return create(baseUrl)
    }

    private fun create(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(OkHttpManger.instance)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}