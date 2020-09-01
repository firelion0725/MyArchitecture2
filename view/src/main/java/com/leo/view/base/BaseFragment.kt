package com.leo.view.base

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/20
 * @function
 * @describe
 */
open class BaseFragment : Fragment() {

    private var sInstance: ViewModelProvider.NewInstanceFactory? = null
    private fun getViewModelInstance(): ViewModelProvider.NewInstanceFactory {
        if (sInstance == null) {
            sInstance = ViewModelProvider.NewInstanceFactory();
        }
        return sInstance as ViewModelProvider.NewInstanceFactory;
    }

    fun <T : ViewModel> getViewModel(@NonNull modelClass: Class<T>): T {
        return getViewModelInstance().create(modelClass)
    }

}