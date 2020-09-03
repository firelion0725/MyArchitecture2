package com.leo.view.base

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
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

    open fun adapterScreen(activity: Activity, targetDp: Int, isVertical: Boolean) {
        val systemDm = Resources.getSystem().displayMetrics
        val appDm = activity.application.resources.displayMetrics
        val activityDm = activity.resources.displayMetrics

        //通过目标DP 算出 逻辑密度
        if (isVertical) {
            activityDm.density = activityDm.heightPixels / targetDp.toFloat()
        } else {
            activityDm.density = activityDm.widthPixels / targetDp.toFloat()
        }

        //通过逻辑密度 算出比例密度
        activityDm.scaledDensity = activityDm.density * (systemDm.scaledDensity / systemDm.density)
        //算出 密度DPI 即修改没英寸的像素点数量
        activityDm.densityDpi = (DisplayMetrics.DENSITY_DEFAULT * activityDm.density).toInt()
    }



}