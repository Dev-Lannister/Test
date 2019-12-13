package com.youdao.test.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.Surface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.protobuf.ByteString;
import com.youdao.test.model.bean.ScreenCastMessage;
import com.youdao.test.network.netty.ServerSession;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ScreenCastService extends Service {

    private static final String VIRTUAL_DISPLAY_NAME = "Recording Display";

    private MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection;
    private MediaCodec mEnCoder;
    private VirtualDisplay mVirtualDisplay;
    private Surface mSurface;

    private ScreenCastBinder mBinder = new ScreenCastBinder();

    public class ScreenCastBinder extends Binder {
        public void startScreenCast(int resultCode, Intent resultIntent) {
            if (mMediaProjection == null) {
                mMediaProjection = mMediaProjectionManager.getMediaProjection(resultCode, resultIntent);
            }
            startRealScreenCast();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    }

    private void startRealScreenCast() {
        try {
            MediaFormat mediaFormat = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, 360, 640);
            mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, 1024000);
            mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, 30);
            mediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1);//关键帧间隔
            mediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
            mEnCoder = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
            mEnCoder.setCallback(new MediaCodec.Callback() {
                @Override
                public void onInputBufferAvailable(@NonNull MediaCodec codec, int index) {

                }

                @Override
                public void onOutputBufferAvailable(@NonNull MediaCodec codec, int index, @NonNull MediaCodec.BufferInfo info) {
                    ByteBuffer outputBuffer = codec.getOutputBuffer(index);
                    if (info.size > 0 && outputBuffer != null) {
                        outputBuffer.position(info.offset);
                        outputBuffer.limit(info.offset + info.size);
                        byte[] bytes = new byte[outputBuffer.remaining()];
                        outputBuffer.get(bytes);
                        Log.d("lijiwei", "onOutputBufferAvailable: bytes.len = " + bytes.length);
                        sendData(bytes);
                    }
                    if (mEnCoder != null) {
                        mEnCoder.releaseOutputBuffer(index, false);
                    }
                }

                @Override
                public void onError(@NonNull MediaCodec codec, @NonNull MediaCodec.CodecException e) {

                }

                @Override
                public void onOutputFormatChanged(@NonNull MediaCodec codec, @NonNull MediaFormat format) {

                }
            });
            mEnCoder.configure(mediaFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            mSurface = mEnCoder.createInputSurface();
            mEnCoder.start();
            mVirtualDisplay = mMediaProjection.createVirtualDisplay(VIRTUAL_DISPLAY_NAME, 360, 720, 240, DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC, mSurface, null, null);
        } catch (IOException e) {
            release();
            e.printStackTrace();
        }
    }

    private void sendData(byte[] bytes) {
        ScreenCastMessage.Data.Builder builder = ScreenCastMessage.Data.newBuilder();
        builder.setData(ByteString.copyFrom(bytes));
        ServerSession.getInstance().sendMessage(builder.build());
    }

    private void stopScreenCapture() {
        release();
        if (mVirtualDisplay == null) {
            return;
        }
        mVirtualDisplay.release();
        mVirtualDisplay = null;
    }

    private void release() {

        if (mEnCoder != null) {
            mEnCoder.stop();
            mEnCoder.release();
            mEnCoder = null;
        }
        if (mSurface != null) {
            mSurface.release();
            mSurface = null;
        }
        if (mMediaProjection != null) {
            mMediaProjection.stop();
            mMediaProjection = null;
        }
    }
}
