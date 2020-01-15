package com.example.gs.gsmvp.search;

import com.example.gs.gsmvp.base.BaseModel;
import com.example.gs.gsmvp.base.BasePresenter;
import com.example.gs.gsmvp.base.BaseView;

import io.reactivex.Observable;

public interface SearchContract {
    interface Model extends BaseModel {
        Observable<String> sendEmail();
    }

    interface View extends BaseView {
        void sendSuccess(String str);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void sendEmail();
    }
}
