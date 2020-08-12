package com.leo.architecture.test.data.comm

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */

fun <T> compile(observable: Flowable<T>): Flowable<T> {
    return observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> observableCompile(): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream: Observable<T> ->
        upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> singleCompile(): SingleTransformer<T, T> {
    return SingleTransformer { upstream: Single<T> ->
        upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}