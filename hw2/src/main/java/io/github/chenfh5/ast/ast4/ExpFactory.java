package io.github.chenfh5.ast.ast4;

import io.github.chenfh5.ast.ast1.exp.Exp;
import io.github.chenfh5.ast.ast1.exp.NumericLiteral;
import io.github.chenfh5.ast.ast1.exp.StringLiteral;
import io.github.chenfh5.ast.ast3.ConnectExp;

public class ExpFactory {

    private static final ExpFactory instance = new ExpFactory();

    private ExpFactory() {
    }

    public static ExpFactory getInstance() {
        return instance;
    }

    private int numCnt = 0;
    private int strCnt = 0;
    private int connCnt = 0;


    public NumericLiteral makeNumberExp(int value) {
        numCnt++;
        return new NumericLiteral(value);
    }

    public StringLiteral makeStringExp(String value) {
        strCnt++;
        return new StringLiteral(value);
    }


    public ConnectExp makeConnExp(Exp exp1, Exp exp2, String op) {
        connCnt++;
        return new ConnectExp(exp1, exp2, op);
    }

    public void report() {
        StringBuilder sb = new StringBuilder();
        if (connCnt > 0) sb.append("Number of Connect nodes = ").append(connCnt).append("\n");
        if (numCnt > 0) sb.append("Number of NumericLiteral nodes = ").append(numCnt).append("\n");
        if (strCnt > 0) sb.append("Number of StringLiteral nodes = ").append(strCnt).append("\n");
        System.out.print(sb);
    }

}
