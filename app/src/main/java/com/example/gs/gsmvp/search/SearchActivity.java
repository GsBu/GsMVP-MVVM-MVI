package com.example.gs.gsmvp.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gs.gsmvp.R;
import com.example.gs.gsmvp.base.BaseMVPActivity;

public class SearchActivity extends BaseMVPActivity<SearchContract.View, SearchContract.Presenter>
        implements SearchContract.View{

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendEmail();
            }
        });
    }

    @Override
    protected void onInitParams(Bundle bundle) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {

    }

    @Override
    protected SearchContract.Presenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void sendSuccess(String str) {
        Snackbar.make(fab, str, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
