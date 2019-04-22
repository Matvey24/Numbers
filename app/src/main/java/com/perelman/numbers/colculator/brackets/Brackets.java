package com.perelman.numbers.colculator.brackets;

import java.util.HashMap;

public class Brackets {
    private static HashMap<Character, Bracket> names;
    public static Bracket opening;
    public static Bracket closes;
    static {
        names = new HashMap<>();
        opening = new Bracket(false);
        closes = new Bracket(true);
        add('<', '>');
        add('(', ')');
        add('[', ']');
        add('{', '}');
    }
    private static void add(char c1, char c2){
        names.put(c1, opening);
        names.put(c2, closes);
    }

    public static Bracket getBr(String bracket){
        return getBr(bracket.charAt(0));
    }
    public static Bracket getBr(char bracket){
        return names.get(bracket);
    }
    public static Bracket getAnti(String bracket){
        return getAnti(getBr(bracket));
    }
    public static Bracket getAnti(Bracket bracket){
        return (bracket.closes)?opening:closes;
    }
    public static boolean isAnti(String br1, String br2){
        return getBr(br1).closes != getBr(br2).closes;
    }
    public static boolean isAnti(Bracket br1, Bracket br2){
        return br1.closes != br2.closes;
    }
}
