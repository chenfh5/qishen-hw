package io.github.chenfh5.ast0;

import io.github.chenfh5.ast1.ASTVisitor;

/**
 * ASTNode representing a binary "+" expression
 */
public class PlusExp extends Exp {
    public PlusExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String text() {
        return left.text() + " + " + right.text();
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    private Exp left;
    private Exp right;
}