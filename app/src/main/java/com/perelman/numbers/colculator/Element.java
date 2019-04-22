package com.perelman.numbers.colculator;

public class Element {
    public static final int LETTER = 1;
    public static final int NUMBER = 2;
    public static final int BRACKET = 3;
    public static final int SIGN = 4;
    public static final int DIVIDER = 5;
    public String symbol;
    public int type;

    public Element(String symbol, int type) {
        this.symbol = symbol;
        this.type = type;
    }

    public double toNumber(){
        return Double.parseDouble(symbol);
    }

    @Override
    public String toString() {

        return symbol;
    }
}
