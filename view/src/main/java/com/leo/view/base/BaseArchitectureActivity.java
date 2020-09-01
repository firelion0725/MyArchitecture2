package com.leo.view.base;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Leo.ZhangTJ
 * @time 2020/5/11
 * @function
 * @describe
 */
public abstract class BaseArchitectureActivity<VBD extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {

    private VBD viewDataBinding;
    private VM viewModel;

    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }

    private Class<VM> viewModelClzz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createViewModel();
//        createDataBinding(view);
        getLifecycle().addObserver(viewModel);
        viewDataBinding.setLifecycleOwner(this);
        setContentView(view);
    }

//    private void createDataBinding(View view) {
//        try {
//            ParameterizedType superClassType = (ParameterizedType) this.getClass().getGenericSuperclass();
//            if (superClassType != null) {
//                Type type = superClassType.getActualTypeArguments()[0];
//                Class<VBD> clzz = null;
//                if (type instanceof ParameterizedType) {
//                    clzz = (Class<VBD>) ((ParameterizedType) type).getRawType();
//                } else {
//                    clzz = (Class<VBD>) type;
//                }
//
//                Class<?> implClzz = null;
//                String clzzName = clzz.getName();
//                if (!clzzName.endsWith("Impl")) {
//                    clzzName += "Impl";
//                }
//
//                implClzz = Class.forName(clzzName);
//
//                DataBindingComponent component = DataBindingUtil.getDefaultComponent();
//                Constructor<?>[] constructors = implClzz.getConstructors();
//                viewDataBinding = (VBD) constructors[0].newInstance(component, view);
//
//                Method[] methods = implClzz.getMethods();
//                for (Method m : methods) {
//                    if (m.getName().startsWith("set")) {
//                        Class<?> paramType = m.getParameterTypes()[0];
//                        if (paramType.getName().equals(viewModelClzz.getName())) {
//                            m.invoke(viewDataBinding, viewModel);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void createViewModel() {
        if (viewModel != null) {
            return;
        }
        ParameterizedType superClassType = (ParameterizedType) this.getClass().getGenericSuperclass();
        if (superClassType != null) {
            Type type = superClassType.getActualTypeArguments()[1];
            Class<VM> clzz = null;
            if (type instanceof ParameterizedType) {
                clzz = (Class<VM>) ((ParameterizedType) type).getRawType();
            } else {
                clzz = (Class<VM>) type;
            }
            viewModelClzz = clzz;
        }
        try {
            Constructor<?>[] constructors = viewModelClzz.getConstructors();
            viewModel = (VM) constructors[0].newInstance(this.getApplication());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
