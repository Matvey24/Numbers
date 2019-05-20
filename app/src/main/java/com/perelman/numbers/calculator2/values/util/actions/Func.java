package com.perelman.numbers.calculator2.values.util.actions;

import com.perelman.numbers.calculator2.values.util.actions.functions.UnarFunc;
import com.perelman.numbers.calculator2.values.util.actions.functions.BinarFunc;
import com.perelman.numbers.calculator2.values.util.actions.functions.TernarFunc;

public class Func<T> {
    public final UnarFunc<T> unarFunc;
    public final BinarFunc<T> binarFunc;
    public final TernarFunc<T> ternarFunc;
    public final String name;
    public final int priority;
    public Func(String name, UnarFunc<T> function, int priority){
        this.name = name;
        this.unarFunc = function;
        this.priority = priority;
        binarFunc = null;
        ternarFunc = null;
    }
    public Func(String name, BinarFunc<T> function, int priority){
        this.name = name;
        this.binarFunc = function;
        this.priority = priority;
        unarFunc = null;
        ternarFunc = null;
    }
    public Func(String name, TernarFunc<T> function, int priority){
        this.name = name;
        this.ternarFunc = function;
        this.priority = priority;
        unarFunc = null;
        binarFunc = null;
    }
    public int argcount(){
        if(unarFunc != null){
            return 1;
        }else if(binarFunc != null){
            return 2;
        }else if(ternarFunc != null){
            return 3;
        }
        return 0;
    }
}
