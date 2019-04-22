package com.perelman.numbers.colculator.letters;

import static com.perelman.numbers.colculator.letters.Function.Execute1;
import static com.perelman.numbers.colculator.letters.Function.Execute2;
import java.util.HashMap;

public class Functions {
    private static final HashMap<String, Function> functions;
    private static final Execute1 fact = new Execute1(){
        @Override
        public double execute(double a) {
            a = (long)a;
            if(a == 0)
                return 1;
            return a * execute(a - 1);
        }
    };
    static {
        functions = new HashMap<>();

        functions.put("!", new Function(4, true,  fact));
        functions.put("fact", new Function(4, false, fact));
        functions.put("sqrt", new Function(4, false, (Execute1)Math::sqrt));
        functions.put("cbrt", new Function(4, false, (Execute1)Math::cbrt));

        functions.put("sin", new Function(4, false, (Execute1)Math::sin));
        functions.put("sind", new Function(4, false, (Execute1)a-> Math.sin(Math.toRadians(a))));
        functions.put("cos", new Function(4, false, (Execute1)Math::cos));
        functions.put("cosd", new Function(4, false, (Execute1)(a)-> Math.cos(Math.toRadians(a))));
        functions.put("tg", new Function(4, false, (Execute1)Math::tan));
        functions.put("tgd", new Function(4, false, (Execute1)a -> Math.tan(Math.toRadians(a))));
        functions.put("ctg", new Function(4, false, (Execute1)a -> 1 / Math.tan(a)));
        functions.put("ctgd", new Function(4, false, (Execute1)a -> 1 / Math.tan(Math.toRadians(a))));

        functions.put("arcsin", new Function(4, false,(Execute1)Math::asin));
        functions.put("arccos", new Function(4, false,(Execute1)Math::acos));
        functions.put("arctg", new Function(4, false,(Execute1)Math::atan));
        functions.put("arcctg", new Function(4, false,(Execute1)a -> Math.atan(1 / a)));

        functions.put("arctgD", new Function(4, false,(Execute2)Math::atan2));

        functions.put("pow", new Function(4, false, (Execute2)Math::pow));
        functions.put("ln", new Function(4, false, (Execute1)Math::log));
        functions.put("lb", new Function(4, false, (Execute1)a->Math.log(a) / Math.log(2)));
        functions.put("lg", new Function(4, false, (Execute1)Math::log10));
        functions.put("log", new Function(4, false, (Execute2)(a,b)-> Math.log(a) / Math.log(b)));
    }
    public static Function getFunc(String name){
        return functions.get(name);
    }
}
