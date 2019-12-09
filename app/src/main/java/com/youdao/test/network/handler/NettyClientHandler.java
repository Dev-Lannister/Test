package com.youdao.test.network.handler;

import android.media.MediaCodec;
import android.util.Log;

import com.youdao.test.model.bean.ScreenCastMessage;
import com.youdao.test.model.bean.TestProtobuf;
import com.youdao.test.ui.ScreenSurfaceView;

import java.nio.ByteBuffer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<ScreenCastMessage.Data> {

    private long count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ScreenCastMessage.Data data) {
        while (ScreenSurfaceView.mediaCodec == null && !ScreenSurfaceView.codecIsCreated) {
            Log.d("lijiwei", "MediaCodec未初始化完成");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int offset = 0;
        int length = data.getData().size();
        byte[] bytes = data.getData().toByteArray();

        MediaCodec mediaCodec = ScreenSurfaceView.mediaCodec;
        int inputBufferIndex;
        try {
            inputBufferIndex = mediaCodec.dequeueInputBuffer(-1);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (inputBufferIndex >= 0) {
            ByteBuffer inputBuffer = mediaCodec.getInputBuffer(inputBufferIndex);
            inputBuffer.clear();
            inputBuffer.put(bytes, offset, length);
            int value = bytes[offset + 4] & 0x0f;

            if (value == 7 || value == 8) {
                Log.d("lijiwei", "h264PackageReceived: config frame");
                mediaCodec.queueInputBuffer(inputBufferIndex, 0, length, count * 1024000 / 30, MediaCodec.BUFFER_FLAG_CODEC_CONFIG);
            } else if (value == 5) {
                mediaCodec.queueInputBuffer(inputBufferIndex, 0, length, count * 1024000 / 30, MediaCodec.BUFFER_FLAG_KEY_FRAME);
            } else {
                mediaCodec.queueInputBuffer(inputBufferIndex, 0, length, count * 1024000 / 30, 0);
            }
            count++;
        }

        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int outputBufferIndex = mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
        while (outputBufferIndex >= 0) {
            mediaCodec.releaseOutputBuffer(outputBufferIndex, true);
            outputBufferIndex = mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
        }
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
