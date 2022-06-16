package com.example.gs.mvi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 作者    你的名字
 * 时间    2022/6/16 9:47
 * 文件    GsMVP+MVVM+MVI
 * 描述
 */
public class MviViewModel extends ViewModel {
    private MutableLiveData<MviViewState> _viewState = new MutableLiveData<>();
    public LiveData<MviViewState> viewState = _viewState;

    private MutableLiveData<MviViewEvent> _viewEvent = new MutableLiveData<>();
    public LiveData<MviViewEvent> viewEvent = _viewEvent;

    private int mCount;

    {
        emit(new MviViewState(0, "初始状态"));
    }

    private void btClick(){
        mCount++;
        emit(new MviViewEvent.ShowToast("按钮点击" + mCount));
    }

    private void emit(MviViewState mviViewState){
        _viewState.setValue(mviViewState);
    }

    private void emit(MviViewEvent mviViewEvent){
        _viewEvent.setValue(mviViewEvent);
    }

    public void dispatch(MviViewAction action){
        reduce(viewState.getValue(), action);
    }

    private void reduce(MviViewState state, MviViewAction action){
        if(action instanceof MviViewAction.Bt1Click){
            btClick();
        }else if(action instanceof MviViewAction.Bt2Click){
            emit(new MviViewEvent.UpdateText("ViewModel中的数据"));
        }
    }
}
