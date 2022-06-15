package com.example.gs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gs.gsmvp.home.HomeActivity;
import com.example.gs.gsmvvm.DataBindingMvvmActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btMVP, btMVVM, btMVI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMVP = findViewById(R.id.bt_mvp);
        btMVVM = findViewById(R.id.bt_mvvm);
        btMVI = findViewById(R.id.bt_mvi);

        btMVP.setOnClickListener(this);
        btMVVM.setOnClickListener(this);
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
            case R.id.bt_mvvm:
                intent = new Intent(MainActivity.this, DataBindingMvvmActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}