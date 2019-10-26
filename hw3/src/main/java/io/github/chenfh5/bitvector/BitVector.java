package io.github.chenfh5.bitvector;

import java.util.Stack;

/**
 * set { 0, 1, 4, 7, 31 }
 * 10000000000000000000000010010011
 * <p>
 * For sets containing larger numbers,
 * an array of ints can be used,
 * where the first element represents bits 0-31, the second represents bits 32-63
 */
public class BitVector implements IBitVector {
    private int[] bit; // number of load
    private int sizeOfBit = 0;

    public BitVector(int cnt) {
        bit = new int[cnt];
    }

    @Override
    public boolean getBitAt(int i) {
        // find bucket
        int bucket = i / 32;
        if (bucket > bit.length) return false; // excessively init length
        String binStr = appendLeadingZero(Integer.toBinaryString(bit[bucket]));

        // find bit-position in this selected bucket
        int idx = i - (32 * bucket); // [0,31]
        return binStr.charAt(31 - idx) == '1';
    }

    @Override
    public void setBitAt(int i) {
        if (!getBitAt(i)) {
            sizeOfBit++;
            int bucket = i / 32;
            int idx = i - (32 * bucket);

            // find int-value
            int val = (int) Math.pow(2, idx);

            // excessively then add
            if (bucket > bit.length) {
                BitVector temp = new BitVector(bucket + 1);
                temp.copy(this);

                temp.bit[bucket] = temp.bit[bucket] + val;
                this.copy(temp);
            } else {
                bit[bucket] = bit[bucket] + val;
            }
        }
    }

    @Override
    public void clearBitAt(int i) {
        if (getBitAt(i)) {
            this.sizeOfBit--;
            int bucket = i / 32;
            int idx = i - (32 * bucket);
            int val = (int) Math.pow(2, idx);
            bit[bucket] = bit[bucket] - val;
        }
    }

    @Override
    public void copy(IBitVector b) {
        // reset all
        for (int i = 0; i < bit.length; i++) bit[i] = 0;

        Iterator<Integer> data = b.iterator();
        if (data.hasMoreElements()) {
            int i = data.nextElement();
            this.setBitAt(i);
        }

        this.sizeOfBit = b.size();
    }

    @Override
    public int size() {
        return sizeOfBit;
    }

    @Override
    public Iterator<Integer> iterator() {
        Stack<Integer> data = new Stack<>();

        for (int i = 0; i < bit.length; i++) {
            String binStr = appendLeadingZero(Integer.toBinaryString(bit[i]));
            for (int j = binStr.length() - 1; j >= 0; j--) {
                if (binStr.charAt(j) == '1') {
                    data.push(i * 32 + (31 - j));
                }
            }
        }
        return (Iterators) new Iterators<>(data);
    }

    public String appendLeadingZero(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        while (sBuilder.length() < 32) {
            sBuilder.insert(0, "0");
        }
        return sBuilder.toString();
    }

}
