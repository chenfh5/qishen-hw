package io.github.chenfh5.ast.ast1.stmt;

import io.github.chenfh5.ast.ast1.exp.Exp;

/**
 * Declaration class declares a variable.
 */
public class DeclStmt implements Stmt {
    /**
     * String constant for text "var"
     */
    private static final String TEXT_VAR = "var ";

    /**
     * The string value for the Declaration statement
     */
    private String declText;

    /**
     * This constructor sets the string value of the declaration statement for an Expression
     *
     * @param exp : Variable which is to be declared.
     */
    public DeclStmt(Exp exp) {
        declText = TEXT_VAR + exp.text();
    }

    /**
     * This constructor sets the string value of the declaration statement for an Expression
     *
     * @param var : Variable which is to be declared.
     */
    public DeclStmt(String var) {
        declText = TEXT_VAR + var;
    }

    /**
     * This method takes the variable and returns the string representation of this
     * declaration statement
     *
     * @return The Declaration statement as a String
     */
    @Override
    public String text() {
        return declText;
    }

}
