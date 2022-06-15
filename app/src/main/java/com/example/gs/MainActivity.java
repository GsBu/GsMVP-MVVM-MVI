package com.example.gs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gs.gsmvp.home.HomeActivity;
import com.example.gs.gsmvvm.databinding.DataBindingMvvmActivity;
import com.example.gs.gsmvvm.livedata.LiveDataMvvmActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btMVP, btDataBindingMVVM, btLiveDataMVVM, btMVI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMVP = findViewById(R.id.bt_mvp);
        btDataBindingMVVM = findViewById(R.id.bt_databinding_mvvm);
        btLiveDataMVVM = findViewById(R.id.bt_live_data_mvvm);
        btMVI = findViewById(R.id.bt_mvi);

        btMVP.setOnClickListener(this);
        btDataBindingMVVM.setOnClickListener(this);
        btLiveDataMVVM.setOnClickListener(this);
        btMVI.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.bt_mvp:
                intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_databinding_mvvm:
                intent = new Intent(MainActivity.this, DataBindingMvvmActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_live_data_mvvm:
                intent = new Intent(MainActivity.this, LiveDataMvvmActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}