package com.example.gs.gsmvp.test;

public class TestPresenter extends TestContract.Presenter {

    @Override
    protected TestContract.Model createModel() {
        return new TestModel();
    }
}
