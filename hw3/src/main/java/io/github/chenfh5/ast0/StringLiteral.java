package io.github.chenfh5.ast0;

import io.github.chenfh5.ast1.ASTVisitor;

/**
 * ASTNode representing a string literal
 */
public class StringLiteral extends LiteralExp {
    public StringLiteral(String literal) {
        this.literal = literal;
    }

    @Override
    public String text() {
        return "\"" + literal + "\"";
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    private String literal;
}