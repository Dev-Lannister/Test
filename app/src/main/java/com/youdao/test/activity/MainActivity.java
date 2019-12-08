package com.youdao.test.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;

import com.youdao.baselibrary.ui.dataBinding.mvp.MvpActivity;
import com.youdao.test.R;
import com.youdao.test.databinding.ActivityMainBinding;
import com.youdao.test.presenter.MainPresenter;
import com.youdao.test.view.MainView;

public class MainActivity extends MvpActivity<MainPresenter, ActivityMainBinding> implements MainView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMvpPresenter().startClient();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showResult() {

    }
}
