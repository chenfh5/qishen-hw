package io.github.chenfh5.tests;

import io.github.chenfh5.ast.ast1.exp.Exp;
import io.github.chenfh5.ast.ast1.exp.NumericLiteral;
import io.github.chenfh5.ast.ast1.exp.PlusExp;
import io.github.chenfh5.ast.ast1.stmt.Assignment;
import io.github.chenfh5.ast.ast1.stmt.DeclStmt;
import io.github.chenfh5.ast.ast1.stmt.Sequence;
import io.github.chenfh5.ast.ast1.stmt.Stmt;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ASTTests {

    @Test
    public void test1() {
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        PlusExp exp = new PlusExp(one, three);
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", exp);
        Stmt seq = new Sequence(decl, assign);
        assertEquals(seq.text(), "var x; x = 1 + 3");
    }

}
