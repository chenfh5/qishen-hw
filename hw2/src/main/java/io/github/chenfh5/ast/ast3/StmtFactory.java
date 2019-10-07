package io.github.chenfh5.ast.ast3;

import io.github.chenfh5.ast.ast1.exp.Exp;
import io.github.chenfh5.ast.ast1.stmt.Assignment;
import io.github.chenfh5.ast.ast1.stmt.DeclStmt;
import io.github.chenfh5.ast.ast1.stmt.Sequence;
import io.github.chenfh5.ast.ast1.stmt.Stmt;

public class StmtFactory {

    private int declCnt = 0;
    private int assignCnt = 0;
    private int seqCnt = 0;

    public DeclStmt makeDeclaration(String str) {
        declCnt++;
        return new DeclStmt(str);
    }

    public DeclStmt makeDeclaration(Exp exp) {
        declCnt++;
        return new DeclStmt(exp);
    }

    public Assignment makeAssignment(String var, Exp exp) {
        assignCnt++;
        return new Assignment(var, exp);
    }

    public Sequence makeSequence(Stmt stmt1, Stmt stmt2) {
        seqCnt++;
        return new Sequence(stmt1, stmt2);
    }

    public void report() {
        StringBuilder sb = new StringBuilder();
        if (declCnt > 0) sb.append("Number of Declaration nodes = ").append(declCnt).append("\n");
        if (assignCnt > 0) sb.append("Number of Assignment nodes = ").append(assignCnt).append("\n");
        if (seqCnt > 0) sb.append("Number of Sequence nodes = ").append(seqCnt).append("\n");
        System.out.print(sb);
    }

}
