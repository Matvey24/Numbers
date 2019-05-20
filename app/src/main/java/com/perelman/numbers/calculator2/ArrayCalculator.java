package com.perelman.numbers.calculator2;

import com.perelman.numbers.calculator2.calculator.Director;
import com.perelman.numbers.calculator2.calculator.executors.Variable;
import com.perelman.numbers.calculator2.values.util.AbstractType;
import com.perelman.numbers.calculator2.values.util.actions.AbstractFunc;

import java.util.*;

public class ArrayCalculator<T> {
    private Director<T> director;
    private AbstractType<T> type;
    private List<AbstractFunc<T>> funcs;
    private List<String> funcNames;
    public T calculate(String lines, AbstractType<T> type){
        List<String> list = Arrays.asList(lines.split("\n"));
        this.type = type;
        director = new Director<>(type);
        funcs = new ArrayList<>();
        funcNames = new ArrayList<>();
        for(int i = 1; i < list.size(); ++i){
            String s = list.get(i);
            String t = s.replaceAll("[ \t]", "");
            int n = t.indexOf('=');
            if(n == -1)
                continue;
            analise(t.substring(0, n), t.substring(n + 1));
        }
        director.renewType(type);
        for(int i = 0; i < funcs.size(); ++i){
            AbstractFunc<T> func = funcs.get(i);
            String str = funcNames.get(i);
            director.update(str);
            func.setFunc(director.getTree(), director.getVars());
        }
        director.update(list.get(0));
        return director.calculate();
    }
    private void analise(String start, String end){
        int pos = start.indexOf(':');
        if(pos == -1){
            director.update(end);
            type.addConst(start, director.calculate());
        }else {
            AbstractFunc<T> func = new AbstractFunc<>();
            String name = start.substring(0, pos);
            int args = Integer.parseInt(start.substring(pos + 1));
            switch (args){
                case 3:
                    type.addFunction(name, func.getTernary(), 5);
                    break;
                case 2:
                    type.addFunction(name, func.getBinary(), 5);
                    break;
                case 1:
                    type.addFunction(name, func.getUnary(), 5);
                    break;
                default:
                    throw new RuntimeException("args count is bad " + args);
            }
            funcs.add(func);
            funcNames.add(end);
        }
    }

}
