package com.example.gs.gsmvp.home;

import android.os.Bundle;

import com.example.gs.gsmvp.R;
import com.example.gs.gsmvp.base.BaseActivity;
import com.example.gs.gsmvp.util.ActivityUtils;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onInitParams(Bundle bundle) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);

        HomeFragment homeDetailFragment = (HomeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fl_content);

        if (homeDetailFragment == null) {
            homeDetailFragment = HomeFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    homeDetailFragment, R.id.fl_content);
        }
    }
}
