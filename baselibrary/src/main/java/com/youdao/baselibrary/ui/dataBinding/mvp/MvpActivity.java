package com.youdao.baselibrary.ui.dataBinding.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.baselibrary.ui.dataBinding.DataBindingBaseActivity;

public abstract class MvpActivity<P extends Presenter, V extends ViewDataBinding> extends DataBindingBaseActivity {

    private P mvpPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    public V getViewDataBinding() {
        return (V) mBinding;
    }

    public P getMvpPresenter() {
        return mvpPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) mvpPresenter.detachView();
    }
}
