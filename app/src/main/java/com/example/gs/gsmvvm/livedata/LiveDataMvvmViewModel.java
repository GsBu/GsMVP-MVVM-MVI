package com.example.gs.gsmvvm.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.functions.Consumer;

/**
 * 作者    你的名字
 * 时间    2022/6/15 13:34
 * 文件    GsMVP+MVVM+MVI
 * 描述
 */
public class LiveDataMvvmViewModel extends ViewModel {

    private LiveDataMvvmModel mModel;

    public MutableLiveData<String> mGetDataLiveData = new MutableLiveData();

    //为保证对外暴露的LiveData不可变（LiveData中的setValue方法是protected权限，外部不能使用），增加一个状态就要添加两个LiveData变量
    private MutableLiveData<String> _mGetDataLiveData2 = new MutableLiveData();
    public LiveData<String> mGetDataLiveData2 = _mGetDataLiveData2;

    public void getData(){
        if(mModel == null){
            mModel = new LiveDataMvvmModel();
        }
        mModel.getData()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mGetDataLiveData.postValue(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getData2(){
        if(mModel == null){
            mModel = new LiveDataMvvmModel();
        }
        mModel.getData()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        _mGetDataLiveData2.postValue(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
