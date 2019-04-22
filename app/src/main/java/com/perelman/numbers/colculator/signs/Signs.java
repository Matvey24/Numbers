package com.perelman.numbers.colculator.signs;

import java.util.HashMap;

public class Signs {
    private static HashMap<Character, Sign> signs;
    static {
        signs = new HashMap<>();
        signs.put('+', new Sign(((a, b) -> a + b), 1));
        signs.put('-', new Sign((a, b)-> a - b, 1));
        signs.put('*', new Sign((a, b)-> a * b, 2));
        signs.put('/', new Sign((a, b)-> a / b, 2));
        signs.put('%', new Sign((a, b)-> a % b, 2));
        signs.put('^', new Sign(Math::pow, 3));
    }
    public static Sign getSign(String sign){
        return getSign(sign.charAt(0));
    }
    public static Sign getSign(char sign){
        return signs.get(sign);
    }
}
