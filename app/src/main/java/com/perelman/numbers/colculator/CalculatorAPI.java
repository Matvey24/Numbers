package com.perelman.numbers.colculator;

import com.perelman.numbers.threads.Tasks;

import java.util.Stack;

public class CalculatorAPI {
    private Tasks tasks;
    private Calculator calculator;
    private Parser parser;
    private Counter counter;
    private FuncString func;
    private String s;
    public CalculatorAPI(FuncString funcString){
        tasks = new Tasks();
        calculator = new Calculator();
        parser = new Parser();
        this.func = funcString;
        counter = new Counter() {
            @Override
            public void count() {
                try {
                    stack = parser.parse(s);
                    calculator.clear();
                    while (!stack.empty()) {
                        calculator.next(stack.pop());
                    }
                    calculator.next(null);
                    func.set(calculator.getValue());
                }catch (RuntimeException e){
                }
            }
        };
    }
    public void count(String s){
        counter.s = s;
        counter.count();
    }
    public void dispose(){
        tasks.disposeOnFinish();
    }
    private abstract class Counter{
        String s;
        Stack<Element> stack;
        public abstract void count();
    }
    public interface FuncString {
        void set(double d);
    }
}
