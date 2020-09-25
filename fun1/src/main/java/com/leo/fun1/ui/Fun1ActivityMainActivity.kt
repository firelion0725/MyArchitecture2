package com.leo.fun1.ui

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.leo.fun1.R
import com.leo.view.comm.FUN1_ACTIVITY
import kotlinx.android.synthetic.main.fun1_activity_main.*


@Route(path = FUN1_ACTIVITY)
class Fun1ActivityMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fun1_activity_main)

        button.setOnClickListener {
            Toast.makeText(this.baseContext, "test", Toast.LENGTH_SHORT).show()
        }

//        anonymousInnerClass()
    }


    //匿名内部类持有MemoryTestActivity实例引用
    fun anonymousInnerClass() {
        object : Thread() {
            override fun run() {
                //执行异步处理
                SystemClock.sleep(240000)
            }
        }.start()
    }

}