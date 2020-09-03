package com.leo.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding


/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */

abstract class BaseArchitectureFragment<VM:BaseViewModel,VDB:ViewDataBinding> : BaseFragment() {

    protected lateinit var binding: VDB
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = setViewModel()
        binding = setBinding()
        viewModel.apply {
            lifecycle.addObserver(this)
        }
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroy() {
        viewModel.let { lifecycle.removeObserver(it) }
        super.onDestroy()
    }

    abstract fun setViewModel(): VM
    abstract fun setBinding(): VDB
}