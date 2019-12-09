package com.youdao.test.network.netty;

import com.youdao.test.model.bean.ScreenCastMessage;
import com.youdao.test.network.Session;

import io.netty.channel.Channel;

public class ServerSession implements Session {

    private static ServerSession serverSession;

    private Channel channel;

    public static ServerSession getInstance() {
        if (serverSession == null) {
            serverSession = new ServerSession();
        }
        return serverSession;
    }

    public void addChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void sendMessage(ScreenCastMessage.Data message) {
        channel.writeAndFlush(message);
    }

}
