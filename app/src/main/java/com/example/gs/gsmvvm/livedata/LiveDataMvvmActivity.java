package com.example.gs.gsmvvm.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gs.R;

public class LiveDataMvvmActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btGet, btGet2;
    private TextView tvData, tvData2;

    private LiveDataMvvmViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_mvvm);

        btGet = findViewById(R.id.bt_get);
        btGet2 = findViewById(R.id.bt_get2);
        tvData = findViewById(R.id.tv_data);
        tvData2 = findViewById(R.id.tv_data2);

        btGet.setOnClickListener(this);
        btGet2.setOnClickListener(this);

        mViewModel = ViewModelProviders.of(this).get(LiveDataMvvmViewModel.class);

        mViewModel.mGetDataLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvData.setText("1：" + s);
            }
        });
        mViewModel.mGetDataLiveData2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvData2.setText("2：" + s);
            }
        });
        //LiveData中的setValue方法是protected权限，外部不能使用，确保了数据在外部不能被改变
        //mViewModel.mGetDataLiveData2.setValue("在View层改变数据");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_get:
                mViewModel.getData();
                break;
            case R.id.bt_get2:
                mViewModel.getData2();
                break;
            default:
                break;
        }
    }
}