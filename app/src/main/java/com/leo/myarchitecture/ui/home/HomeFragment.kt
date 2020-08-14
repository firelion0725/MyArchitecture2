package com.leo.myarchitecture.ui.home

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.leo.myarchitecture.R
import com.leo.myarchitecture.databinding.FragmentHomeBindingImpl
import com.leo.myarchitecture.ui.base.BaseArchitectureFragment
import com.leo.myarchitecture.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : BaseArchitectureFragment() {

    override fun setViewModel(): BaseViewModel {
        return HomeViewModel(this.requireActivity().application)
    }

    override fun setBinding(): ViewDataBinding {
        val dataBinding: FragmentHomeBindingImpl? =
            DataBindingUtil.bind(layoutInflater.inflate(R.layout.fragment_home, container, false))
        dataBinding?.viewModel = viewModel as HomeViewModel?
        return dataBinding!!
    }


}