package com.example.gs.gsmvp.searchdatabinding;

import io.reactivex.functions.Consumer;

public class SearchPresenter {

    public SearchActivityBean mSearchActivityBean;
    private SearchModel mModel = new SearchModel();

    public SearchPresenter(){
        mSearchActivityBean = new SearchActivityBean();
    }

    public void sendEmail() {
        mModel.sendEmail()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mSearchActivityBean.result.set("Databinding修改数据");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
