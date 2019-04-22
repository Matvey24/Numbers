package com.perelman.numbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public Button btn_calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_calc = findViewById(R.id.btn_calc);
        btn_calc.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, CalcActivity.class);
            startActivity(intent);
        });
    }
}
