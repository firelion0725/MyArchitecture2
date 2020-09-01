package com.leo.myarchitecture.ui.notifications

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.leo.manger.thread.ThreadManger
import com.leo.myarchitecture.R
import com.leo.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_notifications.*
import java.lang.Thread.sleep
import java.lang.ref.WeakReference

class NotificationsFragment : BaseFragment() {

    lateinit var notificationsViewModel: NotificationsViewModel
    private val handler = MyHandler(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notificationsViewModel = getViewModel(NotificationsViewModel::class.java)

        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            text_notifications.text = it
        })

        test_button.setOnClickListener {
            ThreadManger.getIoExecutor().execute {
                sleep(2000)
                Log.i("aaaaaaaa", "aaaaaaaaa")
                val msg = Message.obtain()
                msg.what = 0
                handler.sendMessage(msg)
            }
        }
    }

    class MyHandler() : Handler() {

        private lateinit var view: WeakReference<NotificationsFragment>

        constructor(view: NotificationsFragment) : this() {
            this.view = WeakReference(view)
        }

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    Log.i("aaaaaaaa", "msg is ${msg.what}")
                    view.get()?.text_notifications?.text = "msg is 0"
                }
                1 -> {
                    Log.i("aaaaaaaa", "msg is a  ${msg.what}")
                }
            }
        }
    }

}