package com.youdao.test.ui;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ScreenSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Surface surface;
    public static MediaCodec mediaCodec;
    public static boolean codecIsCreated = false;

    private boolean isRunning = false;
    private RenderThread renderThread;


    public ScreenSurfaceView(Context context) {
        this(context, null);
    }

    public ScreenSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScreenSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ScreenSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        renderThread = new RenderThread();
        renderThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    public class RenderThread extends Thread {
        @Override
        public void run() {
            try {
                Log.d("lijiwei", "run: 初始化解码器");
                surface = holder.getSurface();
                mediaCodec = MediaCodec.createDecoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
                MediaFormat mediaFormat = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, getWidth(), getHeight());
                Log.d("lijiwei", "run: 解码器宽高：" + getWidth() + "*" + getHeight());
                mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, 1024000);
                mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, 30);
                mediaFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, 0);
                mediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1);
                mediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
                if (surface.isValid()) {
                    codecIsCreated = true;
                    mediaCodec.configure(mediaFormat, surface, null, 0);
                    mediaCodec.start();
                }
            } catch (Exception e) {
                Log.d("lijiwei", "初始化解码器失败", e);
                e.printStackTrace();
            }
            super.run();
        }
    }
}
