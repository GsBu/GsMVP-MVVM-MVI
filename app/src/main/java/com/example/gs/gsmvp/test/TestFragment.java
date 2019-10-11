package com.example.gs.gsmvp.test;

import android.view.View;

import com.example.gs.gsmvp.R;
import com.example.gs.gsmvp.base.BaseFragment;

public class TestFragment extends BaseFragment<TestContract.View, TestContract.Presenter>
        implements TestContract.View {

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initViewOrEvent(View view) {

    }

    @Override
    protected TestContract.Presenter createPresenter() {
        return new TestPresenter();
    }

}
