package com.leo.fun1.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.leo.fun1.R
import com.leo.fun1.databinding.Fun1FragmentHomeBindingImpl
import com.leo.view.base.BaseArchitectureFragment
import com.leo.view.comm.FUN1_ACTIVITY
import com.zhangyue.we.x2c.X2C
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fun1_fragment_home.*

@Xml(layouts = ["fun1_fragment_home"])
class HomeFragment : BaseArchitectureFragment<HomeViewModel, Fun1FragmentHomeBindingImpl>() {

    override fun setViewModel(): HomeViewModel = HomeViewModel(this.requireActivity().application)

    override fun setBinding(container: ViewGroup?): Fun1FragmentHomeBindingImpl {
        val view = X2C.inflate(context, R.layout.fun1_fragment_home, container, false)
        val dataBinding: Fun1FragmentHomeBindingImpl? = DataBindingUtil.bind(view)
        dataBinding?.viewModel = viewModel
        return dataBinding!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        text.text = "fun1 home page"

        viewModel.liveData.observe(this.viewLifecycleOwner, Observer {
            ARouter.getInstance().build(FUN1_ACTIVITY).navigation()
        })
    }


}