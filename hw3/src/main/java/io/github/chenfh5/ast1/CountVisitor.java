package io.github.chenfh5.ast1;

import io.github.chenfh5.ast0.Exp;
import io.github.chenfh5.ast0.Stmt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountVisitor implements ASTVisitor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int expCnt, stmtCnt, totalCnt;

    @Override
    public void visit(Exp e) {
        expCnt++;
        totalCnt++;
    }

    @Override
    public void visit(Stmt s) {
        stmtCnt++;
        totalCnt++;
    }

    public int[] report() {
        logger.info("exp count={}", expCnt);
        logger.info("stmt count={}", stmtCnt);
        logger.info("total count={}", totalCnt);
        return new int[] {expCnt,stmtCnt,totalCnt};
    }

}
