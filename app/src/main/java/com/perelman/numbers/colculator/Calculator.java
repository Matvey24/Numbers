package com.perelman.numbers.colculator;

import com.perelman.numbers.colculator.brackets.Bracket;
import com.perelman.numbers.colculator.letters.Constants;
import com.perelman.numbers.colculator.letters.Function;
import com.perelman.numbers.colculator.signs.Sign;

import java.util.Stack;

import static com.perelman.numbers.colculator.Element.*;
import static com.perelman.numbers.colculator.brackets.Brackets.getBr;
import static com.perelman.numbers.colculator.letters.Functions.getFunc;
import static com.perelman.numbers.colculator.signs.Signs.getSign;

public class Calculator {
    private Stack<Double> values;
    private Stack<Element> other;
    public Calculator(){
        values = new Stack<>();
        other = new Stack<>();
    }
    public void next(Element e){
        if(e == null) {
            count(0);
            return;
        }
        switch (e.type){
            case LETTER:
                nextLetter(e);
                break;
            case SIGN:
                nextSign(getSign(e.symbol), e);
                break;
            case NUMBER:
                values.push(e.toNumber());
                break;
            case BRACKET:
                other.push(e);
                nextBracket(getBr(e.symbol));
                break;
            case DIVIDER:
                break;
        }
    }
    private void nextSign(Sign sign, Element e){
        int prio = 0;
        if(!other.empty()){
            Element e1 = other.peek();
            if(e1.type == SIGN){
                prio = getSign(e1.symbol).priority;
            }else if(e1.type == LETTER){
                prio = getFunc(e1.symbol).priority;
            }
        }
        if(prio >= sign.priority){
            count(sign.priority);
        }
        other.push(e);
    }
    private void nextBracket(Bracket bracket){
        if(bracket.closes){
            for(int i = other.size() - 2; i >= 0; --i){
                Element e = other.elementAt(i);
                if(e.type == BRACKET){
                    if(i - 1 >= 0){
                        if(other.elementAt(i - 1).type == SIGN) {
                            Sign s = getSign(other.elementAt(i - 1).symbol);
                            count(s.priority + 1);
                        }else if(other.elementAt(i - 1).type == LETTER){
                            Function f = getFunc(other.elementAt(i - 1).symbol);
                            count(f.priority + 1);
                        }
                    }else {
                        count(0);
                    }
                    return;
                }
            }
        }
    }
    private void nextLetter(Element e){
        Double d = Constants.getValue(e.symbol);
        if(d != null){
            values.push(d);
            return;
        }
        Function f = getFunc(e.symbol);
        if(f != null){
            other.push(e);
            if(f.afterNumber){
                count(f.priority);
            }
            return;
        }
        throw new RuntimeException("Couldn't understand symbol '" + e.symbol + "'");

    }

    private void count(int priority){
        if(other.empty())
            return;
        Element e = other.pop();
        if(e.type == SIGN){
            Sign sign = getSign(e.symbol);
            if(sign.priority >= priority){
                Double d2 = values.pop();
                Double d1 = values.pop();
                values.push(sign.execute(d1, d2));
                count(priority);
            }else{
                other.push(e);
            }
        }else if(e.type == BRACKET){
            Bracket b = getBr(e.symbol);
            if(b.closes){
                count(0);
                count(priority);
            }else if(priority != 0){
                other.push(e);
            }
        }else if(e.type == LETTER){
            Function f = getFunc(e.symbol);
            if(f.priority >= priority){
                if(f.argCount == 1){
                    Double d1 = values.pop();
                    values.push(f.execute(d1));
                }else if(f.argCount == 2){
                    Double d2 = values.pop();
                    Double d1 = values.pop();
                    values.push(f.execute(d1, d2));
                }else if(f.argCount == 3){
                    Double d3 = values.pop();
                    Double d2 = values.pop();
                    Double d1 = values.pop();
                    values.push(f.execute(d1,d2,d3));
                }
                count(priority);
            }else{
                other.push(e);
            }
        }

    }
    public double getValue(){
        return values.peek();
    }
    public void clear(){
        values.clear();
        other.clear();
    }
}
