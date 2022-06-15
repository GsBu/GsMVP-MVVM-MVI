package com.example.gs.gsmvvm.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.gs.R;
import com.example.gs.databinding.ActivityDataBindingMvvmBinding;

public class DataBindingMvvmActivity extends AppCompatActivity {

    private ActivityDataBindingMvvmBinding mBinding;
    private DataBindingMvvmViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_mvvm);

        mViewModel = ViewModelProviders.of(this).get(DataBindingMvvmViewModel.class);

        mBinding.setViewMode(mViewModel);
        mBinding.setPresenterMode(mViewModel.mPresenterModel);
    }
}