package com.leo.data.api

import com.leo.data.model.BannerTitleModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */
interface GankApi {

    @GET("v2/banners")
    public fun getBanners(): Single<BannerTitleModel>

}