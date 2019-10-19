package com.youdao.baselibrary.ui.dataBinding.mvp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.baselibrary.ui.dataBinding.DataBindingBaseFragment;

public abstract class MvpFragment<P extends Presenter, V extends ViewDataBinding> extends DataBindingBaseFragment {

    protected P mvpPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected P getMvpPresenter() {
        return mvpPresenter;
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) mvpPresenter.detachView();
    }

    private Bundle mData;

    public void transforBundle(Bundle bundle) {
        mData = bundle;
    }

    public Bundle getTransforBundle() {
        return mData;
    }
}
