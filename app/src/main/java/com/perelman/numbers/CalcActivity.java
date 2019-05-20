package com.perelman.numbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.perelman.numbers.calculator2.ArrayCalculator;
import com.perelman.numbers.calculator2.calculator.executors.Variable;
import com.perelman.numbers.calculator2.values.Bool;
import com.perelman.numbers.calculator2.values.Number;
import com.perelman.numbers.calculator2.values.complex.CVal;
import com.perelman.numbers.calculator2.values.complex.ComplexNumber;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalcActivity extends AppCompatActivity {
    @BindView(R.id.calc_et)
    public TextInputEditText et;
    @BindView(R.id.calc_tv)
    public TextView tv;
    private int calc_type;
    private static final int NUMBER = 1;
    private static final int BOOL = 2;
    private static final int COMPLEX = 3;
    private ArrayCalculator<CVal> calc_num;
    private ArrayCalculator<Double> calc_double;
    private ArrayCalculator<Boolean> calc_bool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        ButterKnife.bind(this);
        calc_num = new ArrayCalculator<>();
        calc_bool = new ArrayCalculator<>();
        calc_double = new ArrayCalculator<>();
        calc_type = NUMBER;
    }
    @OnClick(R.id.calc_count)
    public void onClick(){
        Editable e = et.getText();
        if(e != null){
            String s = e.toString();
            try {
                switch (calc_type) {
                    case NUMBER:
                        double d = calc_double.calculate(s, new Number());
                        tv.setText(String.valueOf(d));
                        break;
                    case BOOL:
                        boolean b = calc_bool.calculate(s, new Bool());
                        tv.setText(String.valueOf(b));
                        break;
                    case COMPLEX:
                        CVal c = calc_num.calculate(s, new ComplexNumber());
                        tv.setText(c.toString());
                        break;
                }
            }catch (RuntimeException exception){
                tv.setText(exception.getMessage());
            }
        }

    }
    private MenuItem plot;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        plot = menu.findItem(R.id.calc_plot);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.calc_set_bool:
                calc_type = BOOL;
                plot.setEnabled(true);
                item.setChecked(true);
                break;
            case R.id.calc_set_numbers:
                calc_type = NUMBER;
                plot.setEnabled(false);
                item.setChecked(true);
                break;
            case R.id.calc_set_complex:
                calc_type = COMPLEX;
                plot.setEnabled(false);
                item.setChecked(true);
            case R.id.calc_plot:
                switch (calc_type){
                    case NUMBER:
                        break;
                    case BOOL:
                        break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
