package com.leo.myarchitecture.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.leo.fun1.ui.Fun1ActivityMainActivity
import com.leo.myarchitecture.R
import com.leo.myarchitecture.cpp.CppHttp
import com.leo.myarchitecture.databinding.FragmentHomeBindingImpl
import com.leo.view.base.BaseArchitectureFragment
import com.leo.view.base.BaseViewModel
import com.zhangyue.we.x2c.X2C
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

@Xml(layouts = ["fragment_home"])
class HomeFragment : BaseArchitectureFragment() {

    override fun setViewModel(): BaseViewModel {
        return HomeViewModel(this.requireActivity().application)
    }

    override fun setBinding(): ViewDataBinding {
        val view = X2C.inflate(context, R.layout.fragment_home, container, false)
        val dataBinding: FragmentHomeBindingImpl? = DataBindingUtil.bind(view)
        dataBinding?.viewModel = viewModel as HomeViewModel?
        return dataBinding!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val c = CppHttp()
        text.text = c.testString()

        (viewModel as HomeViewModel).liveData.observe(this.viewLifecycleOwner, Observer {
            val intent = Intent(this.activity, Fun1ActivityMainActivity::class.java)
            startActivity(intent)
        })
    }


}