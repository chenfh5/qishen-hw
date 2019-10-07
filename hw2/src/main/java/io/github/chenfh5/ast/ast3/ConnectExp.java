package io.github.chenfh5.ast.ast3;


import io.github.chenfh5.ast.ast1.exp.Exp;

public class ConnectExp implements Exp {

    private static final String plus = " + ";
    private static final String minus = " - ";
    private static final String mul = " * ";
    private static final String div = " / ";
    private String txt;

    public ConnectExp(Exp exp1, Exp exp2, String op) {
        String opOut = "";
        switch (op) {
            case "+":
                opOut = plus;
                break;
            case "-":
                opOut = minus;
                break;
            case "*":
                opOut = mul;
                break;
            case "/":
                opOut = div;
                break;
            default:
                throw new IllegalArgumentException("Unknown op, only +-*/ allowed");
        }
        txt = exp1.text() + opOut + exp2.text();
    }

    @Override
    public String text() {
        return txt;
    }
}
