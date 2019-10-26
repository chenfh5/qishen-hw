package io.github.chenfh5.ast1;

import io.github.chenfh5.ast0.DeclStmt;
import io.github.chenfh5.ast0.Exp;
import io.github.chenfh5.ast0.PlusExp;
import io.github.chenfh5.ast0.Stmt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountVisitor implements ASTVisitor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int expCnt, stmtCnt;

    @Override
    public void visit(Exp e) {
        expCnt++;
    }

    @Override
    public void visit(Stmt s) {
        stmtCnt++;
    }

    @Override
    public void visit(PlusExp p) {
        // empty
    }

    @Override
    public void visit(DeclStmt d) {
        // empty
    }

    public int[] report() {
        logger.info("exp count={}", expCnt);
        logger.info("stmt count={}", stmtCnt);
        return new int[]{expCnt, stmtCnt};
    }

}
