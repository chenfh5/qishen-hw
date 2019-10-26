package o.github.chenfh5.BitVectorTest;

import io.github.chenfh5.bitvector.BitVector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BitVectorTests {

    @Test
    @DisplayName("normal case")
    void test1() {
        BitVector data = new BitVector(999);

        // set
        data.setBitAt(0);
        Assertions.assertTrue(data.getBitAt(0));
        data.setBitAt(1);
        Assertions.assertTrue(data.getBitAt(1));
        data.setBitAt(31);
        Assertions.assertTrue(data.getBitAt(31));
        data.setBitAt(32);
        Assertions.assertTrue(data.getBitAt(32));
        data.setBitAt(1024);
        Assertions.assertTrue(data.getBitAt(1024));
        data.setBitAt(3196);
        Assertions.assertTrue(data.getBitAt(3196));

        // size
        Assertions.assertEquals(data.size(), 6);

        // clear
        data.clearBitAt(32);
        Assertions.assertFalse(data.getBitAt(32));
        data.clearBitAt(1024);
        Assertions.assertFalse(data.getBitAt(1024));

        // size
        Assertions.assertFalse(data.getBitAt(1023));
        data.clearBitAt(1023);
        Assertions.assertEquals(data.size(), 4);


        // copy then verify
        BitVector data2 = new BitVector(990);
        Assertions.assertEquals(data2.size(), 0);
        data2.copy(data);
        Assertions.assertEquals(data2.size(), 4);
        Assertions.assertFalse(data2.getBitAt(1024));
        Assertions.assertTrue(data2.getBitAt(3196));
    }

}
