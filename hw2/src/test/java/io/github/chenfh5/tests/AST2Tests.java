package io.github.chenfh5.tests;

import io.github.chenfh5.ast.ast1.exp.Exp;
import io.github.chenfh5.ast.ast1.exp.NumericLiteral;
import io.github.chenfh5.ast.ast1.stmt.Assignment;
import io.github.chenfh5.ast.ast1.stmt.DeclStmt;
import io.github.chenfh5.ast.ast1.stmt.Sequence;
import io.github.chenfh5.ast.ast1.stmt.Stmt;
import io.github.chenfh5.ast.ast2.ExpFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AST2Tests {

    @Test
    public void test1() {
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        Exp exp = new ExpFactory(one, three, "+");
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", exp);
        Stmt seq = new Sequence(decl, assign);
        assertEquals(seq.text(), "var x; x = 1 + 3");
    }

    @Test
    public void test2() {
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        Exp exp = new ExpFactory(one, three, "-");
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", exp);
        Stmt seq = new Sequence(decl, assign);
        assertEquals(seq.text(), "var x; x = 1 - 3");
    }

    @Test
    public void test3() {
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        Exp exp = new ExpFactory(one, three, "*");
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", exp);
        Stmt seq = new Sequence(decl, assign);
        assertEquals(seq.text(), "var x; x = 1 * 3");
    }

    @Test
    public void test4() {
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        Exp exp = new ExpFactory(one, three, "/");
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", exp);
        Stmt seq = new Sequence(decl, assign);
        assertEquals(seq.text(), "var x; x = 1 / 3");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test5() {
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        Exp exp = new ExpFactory(one, three, "o");
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", exp);
        Stmt seq = new Sequence(decl, assign);
        assertEquals(seq.text(), "var x; x = 1 / 3");
    }

}
