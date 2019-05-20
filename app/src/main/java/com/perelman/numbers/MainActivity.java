package com.perelman.numbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_calc, R.id.btn_talk})
    public void onClick(Button b){
        Intent intent = new Intent();
        switch (b.getId()){
            case R.id.btn_calc:
                intent.setClass(this, CalcActivity.class);
                break;
            case R.id.btn_talk:
                intent.setClass(this, TalkerActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
