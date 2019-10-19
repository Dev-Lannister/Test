package com.youdao.test.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mDatas = new ArrayList<>();

    public TestViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addDatas(List<Fragment> datas) {
        mDatas.addAll(datas);
    }

    @Override
    public Fragment getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
