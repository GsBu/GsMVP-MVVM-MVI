package com.example.gs.gsmvvm.databinding;

import androidx.databinding.ObservableField;

/**
 * 作者    你的名字
 * 时间    2022/6/15 14:55
 * 文件    GsMVP+MVVM+MVI
 * 描述
 */
public class DataBindingMvvmPresenterModel {
    public ObservableField<String> content1 = new ObservableField<>();

    {
        content1.set("初始数据");
    }
}
