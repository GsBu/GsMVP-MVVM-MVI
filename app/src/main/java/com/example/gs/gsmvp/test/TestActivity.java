package com.example.gs.gsmvp.test;

import android.os.Bundle;

import com.example.gs.R;
import com.example.gs.gsmvp.base.BaseActivity;
import com.example.gs.gsmvp.util.ActivityUtils;

public class TestActivity extends BaseActivity {

    @Override
    protected void onInitParams(Bundle bundle) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);

        TestFragment fragment = (TestFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = TestFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.container);
        }
    }
}
