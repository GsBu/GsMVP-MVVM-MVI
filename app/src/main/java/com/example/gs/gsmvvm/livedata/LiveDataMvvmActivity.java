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

    private Button btGet;
    private TextView tvData;

    private LiveDataMvvmViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_mvvm);

        btGet = findViewById(R.id.bt_get);
        tvData = findViewById(R.id.tv_data);

        btGet.setOnClickListener(this);

        mViewModel = ViewModelProviders.of(this).get(LiveDataMvvmViewModel.class);

        mViewModel.mGetDataLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvData.setText(s);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_get:
                mViewModel.getData();
                break;
            default:
                break;
        }
    }
}