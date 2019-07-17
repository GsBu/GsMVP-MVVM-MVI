package com.example.gs.gsmvp.home;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomePresenter extends HomeContract.Presenter {

    private Disposable disposable;

    @Override
    void getData() {
        disposable = mModel.requestData()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mWeakReference.get().updateTitle(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected HomeContract.Model createModel() {
        return new HomeModel();
    }

/*    @Override
    public void detach() {
        super.detach();
    }*/
}
