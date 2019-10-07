package io.github.chenfh5.ast.ast1.exp;

/**
 * This class provides the text representation of the variable expression created by string
 */
public class VarExp implements Exp {
    private String var;

    public VarExp(String var) {
        this.var = var;
    }

    @Override
    public String text() {
        return var;
    }

}
