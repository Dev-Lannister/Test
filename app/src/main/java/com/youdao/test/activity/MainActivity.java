package com.youdao.test.activity;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.youdao.baselibrary.ui.dataBinding.mvp.MvpActivity;
import com.youdao.test.R;
import com.youdao.test.adapter.TestViewPagerAdapter;
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
        TestViewPagerAdapter viewPagerAdapter = new TestViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addDatas(getMvpPresenter().getDatas());
        getViewDataBinding().vpContent.setAdapter(viewPagerAdapter);
        getViewDataBinding().vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    getViewDataBinding().tvContacts.setTextColor(getColor(R.color.colorWhite));
                    getViewDataBinding().tvDiscovery.setTextColor(getColor(R.color.colorBlack));
                    getViewDataBinding().tvSetting.setTextColor(getColor(R.color.colorBlack));
                } else if (position == 1) {
                    getViewDataBinding().tvContacts.setTextColor(getColor(R.color.colorBlack));
                    getViewDataBinding().tvDiscovery.setTextColor(getColor(R.color.colorWhite));
                    getViewDataBinding().tvSetting.setTextColor(getColor(R.color.colorBlack));
                } else {
                    getViewDataBinding().tvContacts.setTextColor(getColor(R.color.colorBlack));
                    getViewDataBinding().tvDiscovery.setTextColor(getColor(R.color.colorBlack));
                    getViewDataBinding().tvSetting.setTextColor(getColor(R.color.colorWhite));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showResult() {

    }
}
