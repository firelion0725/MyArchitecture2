package com.leo.myarchitecture.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leo.manger.aspectj.annotation.RecordClick
import com.leo.view.base.BaseViewModel


class HomeViewModel(application: Application) : BaseViewModel(application) {

    val liveData: MutableLiveData<String> = MutableLiveData<String>()

    @RecordClick("CLICK_HOME_BUTTON")
    fun test() {
//        Log.i("aaaaaaaaa", "aaaaaaaaaa")
//        ARouter.getInstance().build(FUN1_ACTIVITY)
//            .navigation()

        liveData.value = "aa"
    }
}