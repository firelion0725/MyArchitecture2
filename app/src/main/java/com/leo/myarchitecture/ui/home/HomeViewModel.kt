package com.leo.myarchitecture.ui.home

import android.app.Application
import android.util.Log
import com.leo.manger.aspectj.annotation.RecordClick
import com.leo.myarchitecture.ui.base.BaseViewModel

class HomeViewModel(application: Application) : BaseViewModel(application) {


    @RecordClick("CLICK_HOME_BUTTON")
    fun test() {
        Log.i("aaaaaaaaa", "aaaaaaaaaa")
    }
}