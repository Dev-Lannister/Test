package com.youdao.test.presenter;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.test.adapter.BondListAdapter;
import com.youdao.test.model.source.TestDataSource;
import com.youdao.test.view.MainView;

public class MainPresenter extends Presenter {

    private BondListAdapter mBondListAdapter;
    private TestDataSource mDataSource;

    private MainPresenter(MainView mvpView) {
        super(mvpView);
    }

    public MainPresenter(MainView mvpView, TestDataSource dataSource) {
        this(mvpView);
        mDataSource = dataSource;
    }

    public void initBondListAdapter() {
        mBondListAdapter = new BondListAdapter();
        mBondListAdapter.addAllData(mDataSource.getBondList());
    }

    public BondListAdapter getBondListAdapter() {
        return mBondListAdapter;
    }


}
