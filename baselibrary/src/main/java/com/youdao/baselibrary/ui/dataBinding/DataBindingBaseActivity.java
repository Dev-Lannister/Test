package com.youdao.baselibrary.ui.dataBinding;

import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.youdao.baselibrary.ui.BaseActivity;

public abstract class DataBindingBaseActivity<T extends ViewDataBinding> extends BaseActivity {

    protected T mBinding;

    @Override
    protected void initView() {
        super.initView();
        initDataBinding();
    }

    private void initDataBinding() {
        View decorView = getWindow().getDecorView();
        View bindingView = ((ViewGroup) decorView.findViewById(android.R.id.content)).getChildAt(0);
        mBinding = DataBindingUtil.bind(bindingView);
    }
}
