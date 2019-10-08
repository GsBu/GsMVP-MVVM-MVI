package com.example.gs.gsmvp.user;

import com.example.gs.gsmvp.base.BaseModel;
import com.example.gs.gsmvp.base.BasePresenter;
import com.example.gs.gsmvp.base.BaseView;

import io.reactivex.Observable;

public interface UserContract {
    interface Model extends BaseModel {
        Observable<String> requestData();
    }

    interface View extends BaseView {
        void updateTitle(String title);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void getData();
    }
}
