package com.example.gs.gsmvvm.databinding;

import androidx.lifecycle.ViewModel;

import io.reactivex.functions.Consumer;

/**
 * 作者    你的名字
 * 时间    2022/6/15 14:53
 * 文件    GsMVP+MVVM+MVI
 * 描述
 */
public class DataBindingMvvmViewModel extends ViewModel {
    private DataBindingMvvmModel mModel;
    public DataBindingMvvmPresenterModel mPresenterModel = new DataBindingMvvmPresenterModel();

    public void getData(){
        if(mModel == null){
            mModel = new DataBindingMvvmModel();
        }
        mModel.getData()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mPresenterModel.content1.set(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
