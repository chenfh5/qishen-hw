package io.github.chenfh5.ast0;

/**
 * Root of the statement subhierarchy.  
 *
 */
public abstract class Stmt implements ASTNode {
    @Override
    public abstract String text();   
}
