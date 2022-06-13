package com.example.gs.gsmvp.user;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gs.R;
import com.example.gs.gsmvp.base.BaseFragment;

public class UserFragment extends BaseFragment<UserContract.View, UserContract.Presenter>
        implements UserContract.View, View.OnClickListener {

    private Button btGetData;
    private TextView tvTitle;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initViewOrEvent(View view) {
        btGetData = view.findViewById(R.id.bt_get_data);
        tvTitle = view.findViewById(R.id.tv_title);

        btGetData.setOnClickListener(this);
    }

    @Override
    protected UserContract.Presenter createPresenter() {
        return new UserPresenter();
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
