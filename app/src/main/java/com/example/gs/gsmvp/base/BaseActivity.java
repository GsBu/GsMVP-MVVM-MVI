package com.example.gs.gsmvp.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者    Gs
 * 时间    2019/4/23 15:44
 * 描述    Activity封装
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化接收到的的参数
        if (null != getIntent() && null != getIntent().getExtras()) {
            onInitParams(getIntent().getExtras());
        }

        // 初始化页面视图
        setupViews(savedInstanceState);
    }

    /**
     * 初始化接收到的的参数
     */
    protected abstract void onInitParams(Bundle bundle);

    /**
     * 初始化页面视图
     * 此函数在onCreate后被调用； 主要目的是在子类中实现视图的初始化工作
     */
    protected abstract void setupViews(Bundle savedInstanceState);
}
