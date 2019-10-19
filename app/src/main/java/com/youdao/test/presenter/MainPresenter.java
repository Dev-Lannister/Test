package com.youdao.test.presenter;

import androidx.fragment.app.Fragment;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.test.fragment.ContactsFragment;
import com.youdao.test.fragment.DiscoveryFragment;
import com.youdao.test.fragment.SettingFragment;
import com.youdao.test.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends Presenter {

    public MainPresenter(MainView mvpView) {
        super(mvpView);
    }

    public List<Fragment> getDatas() {
        List<Fragment> datas = new ArrayList<>();
        datas.add(new ContactsFragment());
        datas.add(new DiscoveryFragment());
        datas.add(new SettingFragment());
        return datas;
    }
}
