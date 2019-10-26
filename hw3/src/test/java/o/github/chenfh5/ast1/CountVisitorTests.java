package o.github.chenfh5.ast1;

import io.github.chenfh5.ast0.*;
import io.github.chenfh5.ast1.CountVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CountVisitorTests {

    @Test
    @DisplayName("normal case")
    void test1() {
        // init Exp
        Exp one = new NumericLiteral(1);
        Exp three = new NumericLiteral(3);
        PlusExp exp = new PlusExp(one, three);
        // init Stmt
        Stmt decl = new DeclStmt("x");
        Stmt assign = new Assignment("x", exp);
        Stmt seq = new Sequence(decl, assign);

        // exec
        CountVisitor visitor = new CountVisitor();
        one.accept(visitor);
        three.accept(visitor);
        exp.accept(visitor);
        decl.accept(visitor);
        assign.accept(visitor);
        seq.accept(visitor);
        int[] res = visitor.report();

        // verify
        Assertions.assertEquals(res[0], 3);
        Assertions.assertEquals(res[1], 3);
        Assertions.assertEquals(res[2], 6);
    }

}
