package com.youdao.test.network;

import com.youdao.test.model.bean.TestProtobuf;

public interface Session {
    void sendMessage(TestProtobuf.Word message);
}
