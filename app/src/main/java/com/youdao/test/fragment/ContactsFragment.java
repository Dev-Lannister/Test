package com.youdao.test.fragment;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.baselibrary.ui.dataBinding.mvp.MvpFragment;
import com.youdao.test.R;

public class ContactsFragment extends MvpFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }
}
