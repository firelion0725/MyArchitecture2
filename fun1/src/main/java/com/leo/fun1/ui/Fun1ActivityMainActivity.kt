package com.leo.fun1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.leo.fun1.R
import com.leo.view.comm.FUN1_ACTIVITY

@Route(path = FUN1_ACTIVITY)
public class Fun1ActivityMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fun1_activity_main)
    }
}