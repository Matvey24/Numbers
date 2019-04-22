package com.perelman.numbers.colculator.letters;

import java.util.HashMap;

public class Constants {
    private static HashMap<String, Double> map;
    static {
        map = new HashMap<>();
        map.put("pi", Math.PI);
        map.put("e", Math.E);

        map.put("g", 9.8);
        map.put("G", 6.674083e-11);
        map.put("h", 6.6260700408e-34);
        map.put("c", 299792458.0);

        map.put("eps", 8.85418781762039e-12);

        map.put("NA", 6.022140857e23);
        map.put("R", 8.31445984);
        map.put("k", 1.380648528e-23);


        map.put("ME", 5.97e24);
        map.put("au", 1.495978707e11);
        map.put("pc", 3.09e16);
    }
    public static Double getValue(String letters){
        return map.get(letters);
    }
}
