package com.example.gs.gsmvp.home;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gs.gsmvp.R;
import com.example.gs.gsmvp.base.BaseFragment;

public class HomeFragment extends BaseFragment<HomeContract.View, HomeContract.Presenter>
        implements HomeContract.View, View.OnClickListener {

    private Button btGetData;
    private TextView tvTitle;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewOrEvent(View view) {
        btGetData = view.findViewById(R.id.bt_get_data);
        tvTitle = view.findViewById(R.id.tv_title);

        btGetData.setOnClickListener(this);
    }

    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void updateTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_get_data:
                mPresenter.getData();
                break;
            default:
                break;
        }
    }
}
