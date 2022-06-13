package com.example.gs.gsmvp.searchdatabinding;

import android.os.Bundle;

import com.example.gs.databinding.ActivitySearchDatabindingBinding;

import androidx.databinding.DataBindingUtil;

import com.example.gs.R;
import com.example.gs.gsmvp.base.BaseMVPActivity;
import com.example.gs.gsmvp.base.BasePresenter;

public class SearchDataBindingActivity extends BaseMVPActivity {

    private ActivitySearchDatabindingBinding mDataBinding;

    @Override
    protected void onInitParams(Bundle bundle) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_databinding);

        SearchPresenter searchPresenter = new SearchPresenter();

        mDataBinding.setPresenter(searchPresenter);
        mDataBinding.setResultBean(searchPresenter.mResultBean);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
