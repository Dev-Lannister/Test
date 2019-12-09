package com.youdao.test.network;

import com.youdao.test.model.bean.ScreenCastMessage;

public interface Session {
    void sendMessage(ScreenCastMessage.Data message);
}
