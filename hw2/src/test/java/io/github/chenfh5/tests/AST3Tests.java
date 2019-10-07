package io.github.chenfh5.tests;

import io.github.chenfh5.ast.ast1.exp.Exp;
import io.github.chenfh5.ast.ast1.stmt.Stmt;
import io.github.chenfh5.ast.ast3.ExpFactory;
import io.github.chenfh5.ast.ast3.StmtFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AST3Tests {

    @Test
    public void test1() {
        ExpFactory expFactory = new ExpFactory();
        StmtFactory stmtFactory = new StmtFactory();

        // exp construct
        Exp one = expFactory.makeNumberExp(1);
        Exp two = expFactory.makeNumberExp(3);
        Exp exp = expFactory.makeConnExp(one, two, "+");

        // stmt construct
        Stmt decl = stmtFactory.makeDeclaration("x");
        Stmt assign = stmtFactory.makeAssignment("x", exp);
        Stmt seq = stmtFactory.makeSequence(decl, assign);

        System.out.println("exp report");
        expFactory.report();
        System.out.println("stmt report");
        stmtFactory.report();
        assertEquals(seq.text(), "var x; x = 1 + 3");
    }

    @Test
    public void test2() {
        ExpFactory expFactory = new ExpFactory();
        StmtFactory stmtFactory = new StmtFactory();

        // exp construct
        Exp one = expFactory.makeNumberExp(1);
        Exp two = expFactory.makeNumberExp(3);
        Exp exp = expFactory.makeConnExp(one, two, "-");

        // stmt construct
        Stmt decl = stmtFactory.makeDeclaration("x");
        Stmt assign = stmtFactory.makeAssignment("x", exp);
        Stmt seq = stmtFactory.makeSequence(decl, assign);

        System.out.println("exp report");
        expFactory.report();
        System.out.println("stmt report");
        stmtFactory.report();
        assertEquals(seq.text(), "var x; x = 1 - 3");
    }

    @Test
    public void test3() {
        ExpFactory expFactory = new ExpFactory();
        StmtFactory stmtFactory = new StmtFactory();

        // exp construct
        Exp one = expFactory.makeNumberExp(1);
        Exp two = expFactory.makeNumberExp(3);
        Exp exp = expFactory.makeConnExp(one, two, "*");

        // stmt construct
        Stmt decl = stmtFactory.makeDeclaration("x");
        Stmt assign = stmtFactory.makeAssignment("x", exp);
        Stmt seq = stmtFactory.makeSequence(decl, assign);

        System.out.println("exp report");
        expFactory.report();
        System.out.println("stmt report");
        stmtFactory.report();
        assertEquals(seq.text(), "var x; x = 1 * 3");
    }

    @Test
    public void test4() {
        ExpFactory expFactory = new ExpFactory();
        StmtFactory stmtFactory = new StmtFactory();

        // exp construct
        Exp one = expFactory.makeNumberExp(1);
        Exp two = expFactory.makeNumberExp(3);
        Exp exp = expFactory.makeConnExp(one, two, "/");

        // stmt construct
        Stmt decl = stmtFactory.makeDeclaration("x");
        Stmt assign = stmtFactory.makeAssignment("x", exp);
        Stmt seq = stmtFactory.makeSequence(decl, assign);

        System.out.println("exp report");
        expFactory.report();
        System.out.println("stmt report");
        stmtFactory.report();
        assertEquals(seq.text(), "var x; x = 1 / 3");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test5() {
        ExpFactory expFactory = new ExpFactory();
        StmtFactory stmtFactory = new StmtFactory();

        // exp construct
        Exp one = expFactory.makeNumberExp(1);
        Exp two = expFactory.makeNumberExp(3);
        Exp exp = expFactory.makeConnExp(one, two, "o");

        // stmt construct
        Stmt decl = stmtFactory.makeDeclaration("x");
        Stmt assign = stmtFactory.makeAssignment("x", exp);
        Stmt seq = stmtFactory.makeSequence(decl, assign);

        System.out.println("exp report");
        expFactory.report();
        System.out.println("stmt report");
        stmtFactory.report();
        assertEquals(seq.text(), "var x; x = 1 / 3");
    }

}
