package io.github.chenfh5.ast.stmt;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the text representation of the Sequence statement created by two stmts
 */
public class Sequence implements Stmt {
    private static final String SEMICOLON = "; ";
    private StringBuilder statementText;

    public Sequence(Stmt... stmts) {
        statementText = new StringBuilder();
        List<String> ints = new ArrayList<>();
        for (Stmt e : stmts) ints.add(e.text());
        statementText.append(String.join(SEMICOLON, ints));
    }

    @Override
    public String text() {
        String stmtText = new String(statementText);
        return stmtText.trim();
    }

}
