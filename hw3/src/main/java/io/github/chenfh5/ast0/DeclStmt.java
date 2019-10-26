package io.github.chenfh5.ast0;

import io.github.chenfh5.ast1.ASTVisitor;

/**
 * ASTNode representing a variable declaration
 */
public class DeclStmt extends Stmt {
    public DeclStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public String text() {
        return "var " + varName;
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    private String varName;
}