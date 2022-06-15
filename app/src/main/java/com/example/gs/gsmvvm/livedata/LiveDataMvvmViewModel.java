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
}
