package io.github.chenfh5.ast0;

import io.github.chenfh5.ast1.ASTVisitor;

/**
 * Root of the AST Node hierarchy.
 */
public interface ASTNode {
    /**
     * Generate textual representation for subtree rooted at this node.
     */
    public String text();

    public void accept(ASTVisitor v);
}