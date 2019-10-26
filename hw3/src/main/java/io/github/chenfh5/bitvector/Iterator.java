package io.github.chenfh5.bitvector;

/**
 * A generic iterator
 */
public interface Iterator<T> {

    /**
     * Check if there are more elements
     */
    boolean hasMoreElements();

    /**
     * Return the next element
     */
    T nextElement();
}