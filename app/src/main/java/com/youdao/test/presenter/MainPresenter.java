package com.youdao.test.presenter;

import com.youdao.baselibrary.ui.contract.Presenter;
import com.youdao.test.network.listener.ConnectListener;
import com.youdao.test.network.netty.NettyServer;
import com.youdao.test.view.MainView;

public class MainPresenter extends Presenter<MainView> {

    private static final int PORT = 8888;

    public MainPresenter(MainView mvpView) {
        super(mvpView);
    }

    public void startServer() {
        new NettyServer(PORT, new ConnectListener() {
            @Override
            public void onConnect() {
                mvpView.startCapture();
            }
        }).start();
    }

}
