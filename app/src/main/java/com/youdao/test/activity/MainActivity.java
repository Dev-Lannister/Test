package com.youdao.test.activity;

import androidx.annotation.Nullable;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.IBinder;

import com.youdao.baselibrary.ui.dataBinding.mvp.MvpActivity;
import com.youdao.test.R;
import com.youdao.test.databinding.ActivityMainBinding;
import com.youdao.test.presenter.MainPresenter;
import com.youdao.test.service.ScreenCastService;
import com.youdao.test.view.MainView;

public class MainActivity extends MvpActivity<MainPresenter, ActivityMainBinding> implements MainView {

    private static final int REQUEST_CODE = 1;
    private ScreenCastService.ScreenCastBinder screenCastBinder;
    private Intent resultIntent;
    private int resultCode;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            screenCastBinder = (ScreenCastService.ScreenCastBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMvpPresenter().startServer();
        startScreenCaptureIntent();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void startCapture() {
        screenCastBinder.startScreenCast(resultCode, resultIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            resultIntent = data;
            this.resultCode = resultCode;
            Intent intent = new Intent(this, ScreenCastService.class);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    private void startScreenCaptureIntent() {
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), REQUEST_CODE);
    }
}
