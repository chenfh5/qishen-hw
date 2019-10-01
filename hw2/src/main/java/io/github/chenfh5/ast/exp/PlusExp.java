package io.github.chenfh5.ast.exp;

/**
 * This class provides the text representation of the plus expression created by two operators
 */
public class PlusExp implements Exp {
    private static final String SPACE = " + ";
    private String plusText;

    public PlusExp(Exp operand1, Exp operand2) {
        plusText = operand1.text() + SPACE + operand2.text();
    }

    @Override
    public String text() {
        return plusText;
    }

}
