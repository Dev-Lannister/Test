package com.youdao.test.network.handler;

import android.util.Log;

import com.youdao.test.model.bean.TestProtobuf;
import com.youdao.test.network.netty.ServerSession;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<TestProtobuf.Word> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TestProtobuf.Word word) throws Exception {
        Log.d("lijiwei", "服务端收到： " + word.getSentence());
        /*TestProtobuf.Word response = TestProtobuf.Word.newBuilder().setText("response")
                .setInterpretation("回复")
                .build();
        channelHandlerContext.channel().writeAndFlush(response);*/
        ServerSession.getInstance().addChannel(channelHandlerContext.channel());
    }

}
