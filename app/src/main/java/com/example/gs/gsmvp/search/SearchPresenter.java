package com.example.gs.gsmvp.search;

import com.example.gs.gsmvp.home.HomeContract;
import com.example.gs.gsmvp.home.HomeModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SearchPresenter extends SearchContract.Presenter {

    private Disposable disposable;

    @Override
    public void sendEmail() {
        disposable = mModel.sendEmail()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mWeakReference.get().sendSuccess(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected SearchContract.Model createModel() {
        return new SearchModel();
    }
}
