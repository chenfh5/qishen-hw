package io.github.chenfh5.bitvector;

/**
 * A bitvector.
 */
public interface IBitVector {
    /**
     * Set the bit at position i.
     */
    public void setBitAt(int i);

    /**
     * Determine if the bit at position i is set.
     */
    public boolean getBitAt(int i);

    /**
     * Clear the bit at position i.
     */
    public void clearBitAt(int i);

    /**
     * Set the bits in the argument BitVector b.
     */
    public void copy(IBitVector b);

    /**
     * Determine the number of non-zero bits in the BitVector.
     */
    public int size();

    /**
     * Iterator over the Integer values represented by this
     * BitVector.
     */
    public Iterator<Integer> iterator();
}