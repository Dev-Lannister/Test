package com.youdao.test.network.handler;

import android.util.Log;

import com.youdao.test.model.bean.TestProtobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<TestProtobuf.Word> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TestProtobuf.Word word) {
        Log.d("lijiwei", "客户端收到： " + word.getText());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        TestProtobuf.Word word = TestProtobuf.Word.newBuilder().setText("test")
                .setSentence("test is a test")
                .setInterpretation("测试")
                .build();
        ctx.channel().writeAndFlush(word);
    }
}
