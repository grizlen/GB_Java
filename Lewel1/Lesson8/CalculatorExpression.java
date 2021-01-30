package ru.geekbrains.HW1_8;

public class CalculatorExpression {
    private String src;
    private int len, pos;
    private char ch;
    private int number;
    private char command;
    public CalculatorExpression(String src) {
        this.src = src;
        len = src.length();
        pos = 0;
        next();
    }
    private void next(){
        if (pos < len) ch = src.charAt(pos++);
        else ch = '\0';
    }
    private boolean scanNumber(){
        if (!Character.isDigit(ch)) return false;
        StringBuilder result = new StringBuilder();
        while (Character.isDigit(ch)){
            result.append(ch);
            next();
        }
        number = Integer.parseInt(result.toString());
        command = '\0';
        return true;
    }
    private boolean scanMultDiv(){
        if (ch == '*' || ch == '/') {
            command = ch;
            next();
            return true;
        }
        return false;
    }
    private boolean scanAddSub(){
        if (ch == '+' || ch == '-') {
            command = ch;
            next();
            return true;
        }
        return false;
    }
    private int factor(){
        if (scanNumber()){
            return number;
        }
        else return 0;
    }
    private int term(){
        int result = factor();
        while (scanMultDiv()){
            if (command == '*') result *= factor();
            else result /= factor(); //divbyzero не учитывается
        }
        return result;
    }
    public int evaluate(){
        int result = term();
        while (scanAddSub()){
            if (command == '+') result += term();
            else result -= term();
        }
        return result;
    }
}
