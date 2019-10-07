package io.github.chenfh5.ast.ast1.exp;

/**
 * This class provides the text representation of the plus expression created by two operators
 */
public class PlusExp implements Exp {
    private static final String SPACE = " + ";
    private String plusText;

    public PlusExp(Exp exp1, Exp exp2) {
        plusText = exp1.text() + SPACE + exp2.text();
    }

    @Override
    public String text() {
        return plusText;
    }

}
