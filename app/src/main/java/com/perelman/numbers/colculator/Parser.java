package com.perelman.numbers.colculator;

import com.perelman.numbers.colculator.letters.Constants;
import com.perelman.numbers.colculator.letters.Functions;

import java.util.Stack;
import static com.perelman.numbers.colculator.Element.*;
import static com.perelman.numbers.colculator.brackets.Brackets.getBr;
import static com.perelman.numbers.colculator.letters.Functions.getFunc;
import static com.perelman.numbers.colculator.signs.Signs.getSign;

public class Parser {
    private StringBuilder sb = new StringBuilder();
    public Stack<Element> parse(String str) {
        if (str.isEmpty())
            str = "0";
        Stack<Element> stack = new Stack<>();
        int lt;
        sb.append(str.charAt(0));
        lt = type(str.charAt(0));
        for (int i = 1; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == ' ')
                continue;
            int type = type(c);
            if (lt != type || type == BRACKET || type == SIGN) {
                Element e = new Element(sb.toString(), lt);
                sb.setLength(0);
                stack.push(e);
            }
            lt = type;
            sb.append(c);
        }
        Element e = new Element(sb.toString(), lt);
        sb.setLength(0);
        stack.push(e);
        return updateStack(stack);
    }

    private static int type(char c) {
        int type;
        if (c >= '0' && c <= '9' || c == '.') {
            type = NUMBER;
        } else if (getBr(c) != null) {
            type = BRACKET;
        } else if (getSign(c) != null) {
            type = SIGN;
        } else if(c == ',' || c == ';') {
            type = DIVIDER;
        } else{
            type = LETTER;
        }
        return type;
    }

    public Stack<Element> updateStack(Stack<Element> stack) {
        Stack<Element> elements = new Stack<>();
        int brackets = 0;
        //разворот стека в положительное направление
        //заодно проверяем скобки
        while (!stack.empty()) {
            Element e = stack.pop();
            if(e.type == BRACKET) {
                if (getBr(e.symbol).closes) {
                    --brackets;
                } else {
                    ++brackets;
                }
            }
            elements.push(e);
        }
        for (; brackets < 0; ++brackets) {
            elements.push(new Element("(", BRACKET));
        }
        //разворот стека в обратное направление
        //заодно проверяем знаки
        Element last = elements.pop();
        if(last.type == SIGN && last.symbol.equals("-")){
            stack.push(new Element("-1", NUMBER));
            stack.push(new Element("*", SIGN));
        }else if(last.type == LETTER){
            addCorrectLetter(last, stack);
        }else{
            stack.push(last);
        }

        while (!elements.empty()) {
            Element e = elements.pop();
            switch (e.type) {
                case SIGN:
                    if (openBracket(last) || last.symbol.equals(",")) {
                        if (e.symbol.equals("-")) {
                            stack.push(new Element("-1", NUMBER));
                            stack.push(new Element("*", SIGN));
                            last=stack.peek();
                        }else{
                            stack.push(e);
                        }
                    } else {
                        stack.push(e);
                        last = e;
                    }
                    break;
                case LETTER:
                    addCorrectLetter(e, stack);
                    break;
                default:
                    last = e;
                    stack.push(e);
            }
        }
        for(; brackets > 0; --brackets){
            stack.push(new Element(")", BRACKET));
        }
        //разворот стека в положительном направлении
        //заодно преобразуем (1 + 2)(1 + 2) в (1 + 2) * (1 + 2)
        last = stack.pop();
        elements.push(last);
        while (!stack.empty()) {
            Element element = stack.pop();
            if (closeBracket(element) && (openBracket(last) || startFunc(last) || element.type == NUMBER || constant(element)) ||
                    element.type == NUMBER && (constant(last) || startFunc(last) || openBracket(last)) ||
                    constant(element) && (openBracket(last) || constant(last) || startFunc(last) || last.type == NUMBER)) {
                elements.push(new Element("*", SIGN));
            }
            elements.push(element);
            last = element;
        }
        return elements;
    }

    private static boolean startFunc(Element e) {
        return e.type == LETTER && (getFunc(e.symbol) != null) && !getFunc(e.symbol).afterNumber;
    }
    private static boolean constant(Element e){
        return e.type == LETTER && Constants.getValue(e.symbol) != null;
    }

    private static boolean openBracket(Element e) {
        return e.type == BRACKET && !getBr(e.symbol).closes;
    }

    private static boolean closeBracket(Element e) {
        return e.type == BRACKET && getBr(e.symbol).closes;
    }
    private void addCorrectLetter(Element e, Stack<Element> add){
        if(Constants.getValue(e.symbol) != null || Functions.getFunc(e.symbol) != null){
            add.push(e);
        }else{
            for(int i = 0; i < e.symbol.length(); ++i){
                sb.append(e.symbol.charAt(i));
                String s = sb.toString();
                if(Constants.getValue(s) != null || Functions.getFunc(s) != null){
                    add.push(new Element(s, LETTER));
                    sb.setLength(0);
                }
            }
            if(sb.length() != 0){
                String s = sb.toString();
                sb.setLength(0);
                throw new RuntimeException("Couldn't understand symbol '" + s + "'");
            }
        }
    }
}
