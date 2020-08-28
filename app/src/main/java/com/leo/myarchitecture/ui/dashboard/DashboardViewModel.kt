package com.leo.myarchitecture.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leo.data.api.GankApiRepository
import com.leo.data.model.BannerTitleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val text: LiveData<String> = _text

    fun test() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data: BannerTitleModel = GankApiRepository.getBanners().blockingGet()
                withContext(Dispatchers.Main) {
                    _text.value = "${data.status}"
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}