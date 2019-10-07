package io.github.chenfh5.ast.ast1.exp;

/**
 * This class provides the text representation of the string expression created by the string
 */
public class StringLiteral extends Literal {
    private String strText;

    public StringLiteral(String strExp) {
        strText = strExp;
    }

    @Override
    public String text() {
        return strText;
    }

}
