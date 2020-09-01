package com.leo.view.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.*

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/15
 * @function
 * @describe
 */
open class BaseViewModel(open var app: Application) : AndroidViewModel(app), LifecycleObserver {

    private val TAG = javaClass.simpleName

//    protected var subscriptions: CompositeDisposable = CompositeDisposable()

//    open fun handleHttpException(e: HttpException, tag: String): ErrorHttpModel {
//        val status = e.code()
//        val message = e.message()
//        val data = e.response()?.errorBody()?.source()?.readByteString()?.utf8()
//
//        return ErrorHttpModel(status, message, tag)
//    }

//    open fun <T> singleObserver(
//        p: (t: T) -> Unit,
//        ex: (e: Throwable) -> Unit
//    ): SingleObserver<T> {
//        return object : SingleObserver<T> {
//            override fun onSubscribe(d: Disposable) {
//                subscriptions.add(d)
//            }
//
//            override fun onSuccess(t: T) {
//                p.invoke(t)
//            }
//
//            override fun onError(e: Throwable) {
//                ex.invoke(e)
//            }
//        }
//    }

//    override fun onCleared() {
//        super.onCleared()
//        if (!subscriptions.isDisposed) {
//            subscriptions.dispose()
//        }
//    }

    // ****************** lifeCycle ******************
    open fun bindLifecycle(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    open fun unbindLifecycle(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {
        Log.i(TAG, "======onCreate======")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() {
        Log.i(TAG, "======onStart======")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onResume() {
        Log.i(TAG, "======onResume======")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected open fun onPause() {
        Log.i(TAG, "======onPause======")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun onStop() {
        Log.i(TAG, "======onStop======")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestroy() {
        Log.i(TAG, "======onDestroy======")
    }
}