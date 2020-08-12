package com.leo.myarchitecture.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.lang.reflect.Constructor
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * @author Leo.ZhangTJ
 * @time 2020/4/14
 * @function
 * @describe
 */

abstract class BaseArchitectureFragment<VBD : ViewDataBinding, VM : BaseViewModel> :
    BaseFragment() {

    private var viewModelClassType: Class<VM>? = null

    protected lateinit var binding: VBD
    protected var viewModel: VM? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(getLayoutId(), container, false)
        setViewModel()
        binding = createBindingWithVBD(root);
        viewModel?.apply {
            lifecycle.addObserver(this)
        }
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroy() {
        viewModel?.let { lifecycle.removeObserver(it) }
        super.onDestroy()
    }

    private fun createBindingWithVBD(v: View): VBD {
        if (viewModelClassType == null) {
            viewModelClassType = viewModel?.javaClass!!
        }
        //获取泛型的class type
        val classType: Class<VBD>
        val superClass: Type? = javaClass.genericSuperclass
        val type = (superClass as ParameterizedType).actualTypeArguments[0]
        classType = if (type is ParameterizedType) {
            type.rawType as Class<VBD>
        } else {
            type as Class<VBD>
        }
        //通过名称获取 泛型实例的class type
        val cl: Class<*> = if (classType.name.endsWith("Impl")) {
            //证明直接传入的泛型为实现类 可以直接用
            Class.forName(classType.name);
        } else {
            val className = classType.name + "Impl"
            Class.forName(className)
        }

        //反射获得构造方法 构造实例
        val compact = DataBindingUtil.getDefaultComponent()
        val cons: Array<Constructor<VBD>> = cl.constructors as Array<Constructor<VBD>>;
        val instance = cons[0].newInstance(compact, v)
        //获取setViewModel 方法
        val methods = cl.methods
        for (m in methods) {
            if (m.name.startsWith("set")) {
                val paramType = m.parameterTypes[0]
                if (paramType.name == viewModelClassType?.name) {
                    m.invoke(instance, viewModel)
                    break
                }
            }
        }

        return instance
    }

    private fun createBindingWithVm(): VM {
        val superClass: Type? = javaClass.genericSuperclass
        val type = (superClass as ParameterizedType).actualTypeArguments[1]
        viewModelClassType = if (type is ParameterizedType) {
            type.rawType as Class<VM>
        } else {
            type as Class<VM>
        }
        val cons = viewModelClassType?.constructors
        return cons?.get(0)?.newInstance(activity?.application) as VM
    }

    open fun setViewModel() {
        viewModel = createBindingWithVm()
    }

    abstract fun getLayoutId(): Int
}