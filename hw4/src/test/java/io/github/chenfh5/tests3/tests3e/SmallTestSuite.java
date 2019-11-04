package io.github.chenfh5.tests3.tests3e;

/**
 * Test suite to be used in CS5500 Fall 2019 Section 1 Homework 4, question 3.
 */

import io.github.chenfh5.sorters.MergeSorter;
import io.github.chenfh5.sorters.Sorter;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class SmallTestSuite {

    /**
     * Method for creating a sorter. Modify this to use your own sorter.
     *
     * @return
     */
    private static <T extends Comparable<T>> Sorter<T> createSorter() {
        return new MergeSorter<>();
    }

    // -----------------------------------------------
    // sort a list with one String element
    @Test
    public void testOneString() {
        Sorter<String> sorter = createSorter();
        String[] animals = {"dog"};
        sorter.sort(animals);
        String[] sorted = {"dog"};
        assertArrayEquals(animals, sorted);
    }

    // -----------------------------------------------
    // sort a list with two String elements
    @Test
    public void testTwoStrings() {
        Sorter<String> sorter = createSorter();
        String[] animals = {"dog", "cat"};
        sorter.sort(animals);
        String[] sorted = {"cat", "dog"};
        assertArrayEquals(animals, sorted);
    }

    // -----------------------------------------------
    // sort a list with one int
    @Test
    public void testOneInt() {
        Sorter<Integer> sorter = createSorter();
        Integer[] ints = {1};
        sorter.sort(ints);
        Integer[] sorted = {1};
        assertArrayEquals(ints, sorted);
    }


}