package com.example.gs.mvi;

/**
 * 作者    你的名字
 * 时间    2022/6/16 9:42
 * 文件    GsMVP+MVVM+MVI
 * 描述
 */
public class MviViewState {

    public static final int INITIAL = 0;
    public static final int SUCCESS = 1;

    private int state;
    private String content;

    public MviViewState(int state, String content){
        this.state = state;
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
