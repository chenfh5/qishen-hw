/**
 *
 */
package io.github.chenfh5.ast.exp;

/**
 * This class provides the text representation of the Number expression
 */
public class NumericLiteral extends Literal {
    private String numExpText;

    public NumericLiteral(int value) {
        this.numExpText = String.valueOf(value);
    }

    public NumericLiteral(double value) {
        this.numExpText = String.valueOf(value);
    }

    @Override
    public String text() {
        return numExpText;
    }

}
