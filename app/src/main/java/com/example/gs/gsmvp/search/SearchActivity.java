package com.example.gs.gsmvp.search;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;

import com.example.gs.R;
import com.example.gs.gsmvp.base.BaseMVPActivity;

public class SearchActivity extends BaseMVPActivity<SearchContract.View, SearchContract.Presenter>
        implements SearchContract.View{

    FloatingActionButton fabEmail;

    @Override
    protected void onInitParams(Bundle bundle) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);

        fabEmail = findViewById(R.id.fab_email);
        fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendEmail();
            }
        });
    }

    @Override
    protected SearchContract.Presenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void sendSuccess(String str) {
        Snackbar.make(fabEmail, str, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
