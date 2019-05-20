package com.perelman.numbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.perelman.numbers.talker.Decl;
import com.perelman.numbers.talker.Genus;
import com.perelman.numbers.talker.NumberTalker;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.perelman.numbers.talker.Decl.*;
import static com.perelman.numbers.talker.Genus.*;

public class TalkerActivity extends AppCompatActivity {
    @BindView(R.id.talk_group_decl)
    public RadioGroup groupDecl;
    @BindView(R.id.talk_group_gen)
    public RadioGroup groupGen;
    @BindView(R.id.talk_et)
    public EditText et;
    @BindView(R.id.talk_tv)
    public TextView tv;
    private NumberTalker nt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talker);
        ButterKnife.bind(this);
        nt = new NumberTalker();
    }
    @OnTextChanged(R.id.talk_et)
    @OnClick({R.id.talk_decl_im, R.id.talk_decl_ro, R.id.talk_decl_da,  R.id.talk_decl_tv, R.id.talk_decl_pr,
                        R.id.talk_gen_he, R.id.talk_gen_she, R.id.talk_gen_it})
    public void generate(){
        Decl d;
        switch (groupDecl.getCheckedRadioButtonId()){
            case R.id.talk_decl_im:
                d = I;
                break;
            case R.id.talk_decl_ro:
                d = RO;
                break;
            case R.id.talk_decl_da:
                d = D;
                break;
            case R.id.talk_decl_tv:
                d = T;
                break;
            case R.id.talk_decl_pr:
                d = P;
                break;
            default:
                d = I;
        }
        Genus g;
        switch (groupGen.getCheckedRadioButtonId()){
            case R.id.talk_gen_he:
                g = HE;
                break;
            case R.id.talk_gen_she:
                g = SHE;
                break;
            case R.id.talk_gen_it:
                g = IT;
                break;
            default:
                g = HE;
        }
        String val = et.getText().toString();
        String text = nt.get(d,g,val);
        tv.setText(text);
    }
}
