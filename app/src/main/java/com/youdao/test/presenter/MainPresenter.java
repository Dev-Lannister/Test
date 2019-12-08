package com.youdao.test.presenter;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.test.network.netty.NettyServer;
import com.youdao.test.view.MainView;

public class MainPresenter extends Presenter {

    private static final int PORT = 8888;

    public MainPresenter(MainView mvpView) {
        super(mvpView);
    }

    public void startServer() {
        new NettyServer(PORT).start();
    }

}
