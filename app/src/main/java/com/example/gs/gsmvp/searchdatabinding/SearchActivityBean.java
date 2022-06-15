package com.example.gs.gsmvp.searchdatabinding;

import androidx.databinding.ObservableField;

/**
 * 作者    你的名字
 * 时间    2022/6/13 13:44
 * 文件    GsMVP+MVVM+MVI
 * 描述
 */
public class SearchActivityBean {
    public final ObservableField<String> result = new ObservableField();
    {
        result.set("初始数据");
    }
}
