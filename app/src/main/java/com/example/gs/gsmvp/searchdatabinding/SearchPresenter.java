package com.example.gs.gsmvp.searchdatabinding;

import android.view.View;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SearchPresenter {

    public ResultBean mResultBean;
    private SearchModel mModel = new SearchModel();

    public SearchPresenter(){
        mResultBean = new ResultBean();
    }

    public void sendEmail() {
        mModel.sendEmail()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mResultBean.result.set("Databinding修改数据");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
