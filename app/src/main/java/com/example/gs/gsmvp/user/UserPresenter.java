package com.example.gs.gsmvp.user;

import com.example.gs.gsmvp.util.LogUtil;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class UserPresenter extends UserContract.Presenter {

    private Disposable disposable;

    @Override
    public void getData() {
        disposable = mModel.requestData()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtil.e(TAG, "accept String：" + s);
                        mWeakReference.get().updateTitle(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(TAG, "accept Throwable：" + throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected UserContract.Model createModel() {
        return new UserModel();
    }
}
