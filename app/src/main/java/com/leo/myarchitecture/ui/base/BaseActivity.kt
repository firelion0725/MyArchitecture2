package com.leo.myarchitecture.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Leo.ZhangTJ
 * @time 2020/4/27
 * @function
 * @describe
 */
abstract class BaseActivity : AppCompatActivity() {

    private val targetDp = 360
    open lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ScreenUtils.adapterScreen(this, targetDp, false)
        view = LayoutInflater.from(this).inflate(getLayoutId(), null, false)
        setContentView(view)
    }

    abstract fun getLayoutId(): Int



}