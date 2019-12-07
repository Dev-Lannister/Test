package com.youdao.test.network.handler;

import com.youdao.test.model.bean.TestProtobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<TestProtobuf.Word> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TestProtobuf.Word word) throws Exception {
        System.out.println("服务端收到： " + word.getSentence());
        TestProtobuf.Word response = TestProtobuf.Word.newBuilder().setText("response")
                .setInterpretation("回复")
                .build();
        channelHandlerContext.channel().writeAndFlush(response);
    }

}
