package com.youdao.test.presenter;

import android.app.Activity;
import android.content.Context;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.test.adapter.TestRecyclerViewAdapter;
import com.youdao.test.model.bean.LexiconEntity;
import com.youdao.test.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends Presenter {

    private TestRecyclerViewAdapter mAdapter;
    private Context mContext;

    public MainPresenter(MainView mvpView) {
        super(mvpView);
        mContext = (Activity) mvpView;
    }

    public TestRecyclerViewAdapter getRecyclerViewAdapter() {
        mAdapter = new TestRecyclerViewAdapter(mContext);
        return mAdapter;
    }

    public void initRecyclerViewData() {

        List<LexiconEntity> list = new ArrayList<>();

        LexiconEntity entity = new LexiconEntity();
        entity.setName("离线词库");
        entity.setContent("883324个词条");
        entity.setSize("54.1M");
        entity.setStatus("下载");
        list.add(entity);

        LexiconEntity entity1 = new LexiconEntity();
        entity1.setName("离线词库");
        entity1.setContent("883324个词条");
        entity1.setSize("54.1M");
        entity1.setStatus("下载");
        list.add(entity1);

        LexiconEntity entity2 = new LexiconEntity();
        entity2.setName("离线词库");
        entity2.setContent("883324个词条");
        entity2.setSize("54.1M");
        entity2.setStatus("下载");
        list.add(entity2);

        LexiconEntity entity3 = new LexiconEntity();
        entity3.setName("离线词库");
        entity3.setContent("883324个词条");
        entity3.setSize("54.1M");
        entity3.setStatus("更新");
        list.add(entity3);

        mAdapter.addAll(list);
    }

}
