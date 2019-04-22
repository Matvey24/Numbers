package com.perelman.numbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.perelman.numbers.colculator.CalculatorAPI;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalcActivity extends AppCompatActivity {
    @BindView(R.id.calc_et)
    public TextInputEditText et;
    @BindView(R.id.calc_tv)
    public TextView tv;
    private CalculatorAPI calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        ButterKnife.bind(this);
        calc = new CalculatorAPI((d)-> tv.setText(String.format("%s", d)));
        et.setOnClickListener((v)-> calc.count(et.getText().toString()));
    }
    @Override
    protected void onDestroy() {
        calc.dispose();
        super.onDestroy();
    }
}
