package com.youdao.baselibrary.ui.contract;

public class Presenter<V extends IMvpView> implements IPresenter<V> {

    protected V mvpView;

    public Presenter(V mvpView) {
        attachView(mvpView);
    }

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

}
