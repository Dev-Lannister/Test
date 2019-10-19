package com.youdao.baselibrary.ui.dataBinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.youdao.baselibrary.ui.BaseFragment;

public abstract class DataBindingBaseFragment<T extends ViewDataBinding> extends BaseFragment {

    protected T mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mDataBinding.getRoot();
    }
}
