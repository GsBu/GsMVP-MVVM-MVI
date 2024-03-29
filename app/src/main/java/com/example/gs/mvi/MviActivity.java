package com.example.gs.mvi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.R;

public class MviActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btGet, btGet2, btGet3;
    private TextView tvData;

    private MviViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvi);

        btGet = findViewById(R.id.bt_get);
        btGet2 = findViewById(R.id.bt_get2);
        btGet3 = findViewById(R.id.bt_get3);
        tvData = findViewById(R.id.tv_data);
        btGet.setOnClickListener(this);
        btGet2.setOnClickListener(this);
        btGet3.setOnClickListener(this);

        mViewModel = ViewModelProviders.of(this).get(MviViewModel.class);
        mViewModel.viewState.observe(this, new Observer<MviViewState>() {
            @Override
            public void onChanged(MviViewState mviViewState) {
                renderViewState(mviViewState);
            }
        });

        mViewModel.viewEvent.observe(this, new Observer<MviViewEvent>() {
            @Override
            public void onChanged(MviViewEvent mviViewEvent) {
                renderViewEvent(mviViewEvent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_get:
                mViewModel.dispatch(new MviViewAction.Bt1Click());
                break;
            case R.id.bt_get2:
                mViewModel.dispatch(new MviViewAction.Bt2Click());
                break;
            case R.id.bt_get3:
                mViewModel.dispatch(new MviViewAction.Bt3Click());
                break;
            default:
                break;
        }
    }

    private void renderViewState(MviViewState state){
        if(state.getState() == MviViewState.INITIAL){
            tvData.setText(state.getContent());
        }else if(state.getState() == MviViewState.SUCCESS){
            tvData.setText(state.getContent());
        }
    }

    private void renderViewEvent(MviViewEvent event){
        if(event instanceof MviViewEvent.ShowToast){
            MviViewEvent.ShowToast showToast = (MviViewEvent.ShowToast)event;
            Toast.makeText(this, showToast.getMessage(), Toast.LENGTH_SHORT).show();
        }else if(event instanceof MviViewEvent.ShowDialog){
            MviViewEvent.ShowDialog showDialog = (MviViewEvent.ShowDialog)event;
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_layout);
            TextView textView = dialog.findViewById(R.id.tv_content);
            textView.setText(showDialog.getMessage());
            Button button = dialog.findViewById(R.id.bt_ok);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}