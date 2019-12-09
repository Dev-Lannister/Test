package com.youdao.test.presenter;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.test.network.netty.NettyClient;
import com.youdao.test.view.MainView;

public class MainPresenter extends Presenter<MainView> {

    private static final String HOST = "192.168.43.81";
    private static final int PORT = 8888;

    public MainPresenter(MainView mvpView) {
        super(mvpView);
    }

    public void startClient() {
        new NettyClient(PORT, HOST).start();
    }
}
