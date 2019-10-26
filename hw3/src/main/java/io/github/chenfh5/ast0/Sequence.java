package io.github.chenfh5.ast0;

import io.github.chenfh5.ast1.ASTVisitor;

/**
 * ASTNode representing a sequence of statements.
 */
public class Sequence extends Stmt {
    public Sequence(Stmt stat1, Stmt stat2) {
        this.stat1 = stat1;
        this.stat2 = stat2;
    }

    @Override
    public String text() {
        return stat1.text() + "; " + stat2.text();
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    private Stmt stat1;
    private Stmt stat2;
}
