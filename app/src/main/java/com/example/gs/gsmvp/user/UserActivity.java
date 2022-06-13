package com.example.gs.gsmvp.user;

import android.os.Bundle;

import com.example.gs.R;
import com.example.gs.gsmvp.base.BaseActivity;
import com.example.gs.gsmvp.util.ActivityUtils;

public class UserActivity extends BaseActivity {

    @Override
    protected void onInitParams(Bundle bundle) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user);

        UserFragment homeDetailFragment = (UserFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (homeDetailFragment == null) {
            homeDetailFragment = UserFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    homeDetailFragment, R.id.container);
        }
    }
}
