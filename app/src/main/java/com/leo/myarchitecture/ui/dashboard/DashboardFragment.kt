package com.leo.myarchitecture.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.leo.myarchitecture.R
import com.leo.view.base.BaseFragment
import com.zhangyue.we.x2c.X2C
import com.zhangyue.we.x2c.ano.Xml
import kotlinx.android.synthetic.main.fragment_dashboard.*

@Xml(layouts = ["fragment_dashboard"])
class DashboardFragment : BaseFragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_dashboard, container, false)
        return X2C.inflate(inflater.context,R.layout.fragment_dashboard,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserver()
        dashboardViewModel.test()
    }

    private fun initObserver() {
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            text_dashboard.text = it
        })
    }
}