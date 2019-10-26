package io.github.chenfh5.ast0;

import io.github.chenfh5.ast1.ASTVisitor;

/**
 * ASTNode representing a variable
 */
public class VarExp extends Exp {
    public VarExp(String varName) {
        this.varName = varName;
    }

    @Override
    public String text() {
        return varName;
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    private String varName;
}