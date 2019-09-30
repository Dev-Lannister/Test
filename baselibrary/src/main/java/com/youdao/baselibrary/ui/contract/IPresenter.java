package com.youdao.baselibrary.ui.contract;

public interface IPresenter<V extends IMvpView> {

    void attachView(V view);

    void detachView();

}
