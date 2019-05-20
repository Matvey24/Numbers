package com.perelman.numbers.calculator2.calculator.helpers;

import com.perelman.numbers.calculator2.values.util.actions.Func;

import java.util.HashMap;

public class Funcs<T> {
    private final HashMap<String, Func<T>> functions;
    Funcs(){
        functions = new HashMap<>();
    }
    public void add(Func<T> func){
        functions.put(func.name, func);
    }
    public Func<T> getFunc(String name){
        return functions.get(name);
    }
    void clear(){
        functions.clear();
    }
}
