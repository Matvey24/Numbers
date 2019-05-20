package com.perelman.numbers.calculator2.values.util;

import com.perelman.numbers.calculator2.values.util.actions.Constant;
import com.perelman.numbers.calculator2.values.util.actions.Func;
import com.perelman.numbers.calculator2.values.util.actions.Sign;
import com.perelman.numbers.calculator2.values.util.actions.functions.TernarFunc;
import com.perelman.numbers.calculator2.values.util.actions.functions.UnarFunc;
import com.perelman.numbers.calculator2.values.util.actions.functions.BinarFunc;

import java.util.ArrayList;

public abstract class AbstractType<T> {
    public final ArrayList<Sign<T>> signs;
    public final ArrayList<Func<T>> funcs;
    public final ArrayList<Constant<T>> consts;
    private Sign<T> missingSign;
    protected AbstractType() {
        signs = new ArrayList<>();
        funcs = new ArrayList<>();
        consts = new ArrayList<>();
    }

    protected Sign<T> addSign(char name, BinarFunc<T> sign, int priority) {
        Sign<T> s = new Sign<>(name, sign, priority);
        signs.add(s);
        return s;
    }
    protected void unarySign(Sign<T> sign, UnarFunc<T> func, int priority){
        sign.canBeUnary = true;
        addFunction("" + sign.name, func, priority);
    }
    protected void setMissingSign(Sign<T> sign){
        this.missingSign = sign;
    }

    public void addConst(String name, T state) {
        consts.add(new Constant<>(name, state));
    }

    public void addFunction(String name, UnarFunc<T> unarFunc, int priority) {
        funcs.add(new Func<>(name, unarFunc, priority));
    }

    public void addFunction(String name, BinarFunc<T> binarFunc, int priority) {
        funcs.add(new Func<>(name, binarFunc, priority));
    }

    public void addFunction(String name, TernarFunc<T> ternarFunc, int priority) {
        funcs.add(new Func<>(name, ternarFunc, priority));
    }

    public abstract T toValue(String text);

    public Sign<T> getMissingSign() {
        return missingSign;
    }
}
