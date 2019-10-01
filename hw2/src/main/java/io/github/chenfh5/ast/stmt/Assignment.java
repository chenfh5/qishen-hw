package io.github.chenfh5.ast.stmt;

import io.github.chenfh5.ast.exp.Exp;

/**
 * Assignment class assigns a value to a variable or an expression and provides a text representation of this assignment.
 */
public class Assignment implements Stmt {

    private static final String EQUAL = " = ";
    private String assignText;

    public Assignment(String var, Exp exp) {
        assignText = var + EQUAL + exp.text();
    }

    @Override
    public String text() {
        return assignText;
    }

}
