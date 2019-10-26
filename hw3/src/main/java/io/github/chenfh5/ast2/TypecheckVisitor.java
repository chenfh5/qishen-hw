package io.github.chenfh5.ast2;

import io.github.chenfh5.ast0.*;
import io.github.chenfh5.ast1.ASTVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TypecheckVisitor implements ASTVisitor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int plusExpTypeErrorCnt, declStmtTypeErrorCnt;
    private Set<String> DeclStmtSet = new HashSet<>();

    private List<String> typeErrorList = new ArrayList<>(); // total

    @Override
    public void visit(Exp e) {
        // empty
    }

    @Override
    public void visit(Stmt s) {
        // empty
    }

    @Override
    public void visit(PlusExp p) {
        Exp left = p.getLeft();
        Exp right = p.getRight();

        if (left instanceof NumericLiteral && right instanceof StringLiteral) {
            plusExpTypeErrorCnt++;
            typeErrorList.add("typeError PlusExp: 1st is NumericLiteral and 2nd is StringLiteral");
        } else if (left instanceof StringLiteral && right instanceof NumericLiteral) {
            plusExpTypeErrorCnt++;
            typeErrorList.add("typeError PlusExp: 1st is StringLiteral and 2nd is NumericLiteral");
        }
    }

    @Override
    public void visit(DeclStmt d) {
        if (!DeclStmtSet.contains(d.getVarName())) {
            DeclStmtSet.add(d.getVarName());
        } else {
            declStmtTypeErrorCnt++;
            typeErrorList.add(String.format("typeError DeclStmt: duplicate variable is %s", d.getVarName()));
        }
    }

    public List<String> report() {
        logger.info("PlusExp typeError count={}", plusExpTypeErrorCnt);
        logger.info("DeclStmt typeError count={}", declStmtTypeErrorCnt);
        logger.info("typeError should be {}", typeErrorList);
        return typeErrorList;
    }

}
