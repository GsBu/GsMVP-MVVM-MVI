package com.example.gs.gsmvvm.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.gs.R;
import com.example.gs.databinding.ActivityDataBindingMvvmBinding;

public class DataBindingMvvmActivity extends AppCompatActivity {

    private ActivityDataBindingMvvmBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_mvvm);
    }
}