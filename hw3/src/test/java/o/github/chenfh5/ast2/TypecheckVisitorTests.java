package o.github.chenfh5.ast2;

import io.github.chenfh5.ast0.*;
import io.github.chenfh5.ast2.TypecheckVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class TypecheckVisitorTests {

    @Test
    @DisplayName("normal case")
    void test1() {
        // init Exp
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        PlusExp plusExp = new PlusExp(one, three);
        // init Stmt
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", plusExp);
        Stmt seq = new Sequence(decl, assign);

        // exec
        TypecheckVisitor visitor = new TypecheckVisitor();
        plusExp.accept(visitor);
        decl.accept(visitor);
        List<String> res = visitor.report();

        // verify
        Assertions.assertEquals(res.size(), 0);
    }

    @Test
    @DisplayName("error PlusExp case 1")
    void test2() {
        // init Exp
        Exp num = new NumericLiteral(1);
        Exp str = new StringLiteral("3");
        PlusExp plusExp = new PlusExp(num, str);

        // exec
        TypecheckVisitor visitor = new TypecheckVisitor();
        plusExp.accept(visitor);
        List<String> res = visitor.report();

        // verify
        Assertions.assertEquals(res.size(), 1);
    }

    @Test
    @DisplayName("error PlusExp case 2")
    void test3() {
        // init Exp
        Exp num = new NumericLiteral(1);
        Exp str = new StringLiteral("3");
        PlusExp plusExp = new PlusExp(str, num);

        // exec
        TypecheckVisitor visitor = new TypecheckVisitor();
        plusExp.accept(visitor);
        List<String> res = visitor.report();

        // verify
        Assertions.assertEquals(res.size(), 1);
    }

    @Test
    @DisplayName("error DeclStmt")
    void test4() {
        // init Stmt
        Stmt decl1 = new DeclStmt("x");
        Stmt decl2 = new DeclStmt("y");
        Stmt decl3 = new DeclStmt("z");
        Stmt decl4 = new DeclStmt("x");

        // exec
        TypecheckVisitor visitor = new TypecheckVisitor();
        decl1.accept(visitor);
        decl2.accept(visitor);
        decl3.accept(visitor);
        decl4.accept(visitor);
        List<String> res = visitor.report();

        // verify
        Assertions.assertEquals(res.size(), 1);
    }

    @Test
    @DisplayName("error mix PlusExp and DeclStmt")
    void test5() {
        // init Exp
        Exp num = new NumericLiteral(1);
        Exp str = new StringLiteral("3");
        PlusExp plusExp = new PlusExp(num, str);
        // init Stmt
        Stmt decl1 = new DeclStmt("x");
        Stmt decl2 = new DeclStmt("y");
        Stmt decl3 = new DeclStmt("z");
        Stmt decl4 = new DeclStmt("x");

        // exec
        TypecheckVisitor visitor = new TypecheckVisitor();
        plusExp.accept(visitor);
        decl1.accept(visitor);
        decl2.accept(visitor);
        decl3.accept(visitor);
        decl4.accept(visitor);
        List<String> res = visitor.report();

        // verify
        Assertions.assertEquals(res.size(), 2);
    }

}
