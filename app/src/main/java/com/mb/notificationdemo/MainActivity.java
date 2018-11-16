package com.mb.notificationdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationHelper = new NotificationHelper(this);

        AppCompatButton btnOpen = findViewById(R.id.btn_open);
        AppCompatButton btnClose = findViewById(R.id.btn_close);
        AppCompatButton btnOpenOther = findViewById(R.id.btn_open_second);
        AppCompatButton btnCloseOther = findViewById(R.id.btn_close_second);

        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnOpenOther.setOnClickListener(this);
        btnCloseOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_open:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    notificationHelper.notify(1, notificationHelper
                            .getDefaultNotification("通知栏1", "这是一个默认的通知").build());
                }
                break;
            case R.id.btn_close:
                notificationHelper.cancle(1);
                break;
            case R.id.btn_open_second:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    notificationHelper.notify(2, notificationHelper
                            .getSecondNotification("通知栏2", "这是一个的通知").build());
                }
                break;
            case R.id.btn_close_second:
                notificationHelper.cancle(2);
                break;
        }
    }
}
