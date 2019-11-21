package com.youdao.test.activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.youdao.baselibrary.ui.dataBinding.mvp.MvpActivity;
import com.youdao.test.Injection;
import com.youdao.test.R;
import com.youdao.test.databinding.ActivityMainBinding;
import com.youdao.test.presenter.MainPresenter;
import com.youdao.test.view.MainView;

public class MainActivity extends MvpActivity<MainPresenter, ActivityMainBinding> implements MainView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();
        getViewDataBinding().rvBond.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getMvpPresenter().initBondListAdapter();
        getViewDataBinding().rvBond.setAdapter(getMvpPresenter().getBondListAdapter());

        getViewDataBinding().rvScan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getMvpPresenter().initBondListAdapter();
        getViewDataBinding().rvScan.setAdapter(getMvpPresenter().getBondListAdapter());
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this, Injection.provideTestRepository());
    }

    @Override
    public void showResult() {

    }
}
