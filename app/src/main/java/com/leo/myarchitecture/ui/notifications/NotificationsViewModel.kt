package com.leo.myarchitecture.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leo.myarchitecture.cpp.TestNdk.getStringFromC

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = getStringFromC()
    }
    val text: LiveData<String> = _text
}