package com.perelman.numbers.colculator.letters;


public class Function {
    public int priority;
    public boolean afterNumber;
    public int argCount;
    private Execute1 execute1;
    private Execute2 execute2;
    private Execute3 execute3;
    public double execute(double a){
        return execute1.execute(a);
    }
    public double execute(double a, double b){
        return execute2.execute(a, b);
    }
    public double execute(double a, double b, double c){
        return execute3.execute(a, b, c);
    }
    public Function(int priority, boolean afterNumber, Object execute) {
        this.priority = priority;
        this.afterNumber = afterNumber;
        if(execute instanceof Execute1){
            execute1 = (Execute1) execute;
            argCount = 1;
        }else if(execute instanceof Execute2){
            execute2 = (Execute2) execute;
            argCount = 2;
        }else if(execute instanceof Execute3){
            execute3 = (Execute3)execute;
            argCount = 3;
        }
    }
    public interface Execute1{
        double execute(double a);
    }
    public interface Execute2{
        double execute(double a, double b);
    }
    public interface Execute3{
        double execute(double a, double b, double c);
    }
}
