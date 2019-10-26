package io.github.chenfh5.ast1;

import io.github.chenfh5.ast0.Exp;
import io.github.chenfh5.ast0.Stmt;

// counts the number of expressions and statements in the AST that it visits
public interface ASTVisitor {
    void visit(Exp e);

    void visit(Stmt s);
}
