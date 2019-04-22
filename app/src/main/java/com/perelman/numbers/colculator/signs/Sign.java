package com.perelman.numbers.colculator.signs;

public class Sign {
    public final int priority;
    private Execute execute;
    Sign(Execute execute, int priority){
        this.execute = execute;
        this.priority = priority;
    }
    public double execute(double a, double b){
        return execute.execute(a,b);
    }
    public interface Execute{
        double execute(double a, double b);
    }
    //11 раз слово execute - профит
}
