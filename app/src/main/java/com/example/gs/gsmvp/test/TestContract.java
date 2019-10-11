package com.example.gs.gsmvp.test;

import com.example.gs.gsmvp.base.BaseModel;
import com.example.gs.gsmvp.base.BasePresenter;
import com.example.gs.gsmvp.base.BaseView;

public interface TestContract {
    interface Model extends BaseModel {

    }

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {

    }
}
