package com.perelman.numbers.calculator2.values;

import com.perelman.numbers.calculator2.values.util.AbstractType;

public class Bool extends AbstractType<Boolean> {
    public Bool(){
        addConst("true",true);
        addConst("false", false);

        addFunction("!", a -> !a, 5);

        addSign('|', (a,b)-> a||b, 3);
        addSign('&', (a,b)-> a&&b, 4);
        addSign('^', (a,b)-> a^b, 2);
        addSign('=', (a,b)-> a==b, 1);
    }
    @Override
    public Boolean toValue(String text) {
        int a = Integer.parseInt(text);
        return a != 0;
    }
}
