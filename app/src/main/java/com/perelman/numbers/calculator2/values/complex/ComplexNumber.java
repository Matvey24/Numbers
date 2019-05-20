package com.perelman.numbers.calculator2.values.complex;

import com.perelman.numbers.calculator2.values.util.AbstractType;
import com.perelman.numbers.calculator2.values.util.actions.Sign;

public class ComplexNumber extends AbstractType<CVal> {
    public ComplexNumber(){
        Sign<CVal> minus = addSign('-', CVal::diff, 1);
        addSign('+', CVal::sum,1);
        Sign<CVal> mul = addSign('*', CVal::mul, 2);
        addSign('/', CVal::divide, 2);
        addSign('^', CVal::pow, 3);

        addFunction("pow", CVal::pow, 5);
        addFunction("exp", CVal::exp, 5);
        addFunction("ln", CVal::ln, 5);
        addFunction("mod", CVal::mod, 5);
        addFunction("lg", CVal::lg, 5);
        addFunction("ld", CVal::ld, 5);
        addFunction("log", CVal::log, 5);
        addFunction("sqrt", CMath::sqrt, 5);
        addFunction("cbrt", CMath::cbrt, 5);

        addFunction("cos",CMath::cos, 5);
        addFunction("sin",CMath::sin, 5);


        addConst("i", CVal.I);
        addConst("e", CVal.E);
        addConst("pi", CVal.PI);

        unarySign(minus, CMath::negate, 4);
        setMissingSign(mul);
    }

    @Override
    public CVal toValue(String text) {
        return new CVal(Double.parseDouble(text));
    }
}
