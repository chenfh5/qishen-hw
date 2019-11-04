package io.github.chenfh5.tests2;

import io.github.chenfh5.sorters.QuickSorter;
import io.github.chenfh5.sorters.Sorter;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSorterTests {

    private static <T extends Comparable<T>> Sorter<T> createSorter() {
        return new QuickSorter<T>();
    }

    // Alphabetical order
    @Test
    public void testString() {
        Sorter<String> sorter = createSorter();
        String[] animals = {"dog", "cat", "pig", "cow", "horse", "bird"};
        sorter.sort(animals);
        String[] sorted = {"bird", "cat", "cow", "dog", "horse", "pig"};
        assertArrayEquals(animals, sorted);
    }

    // Digital order
    @Test
    public void testInt() {
        Sorter<Integer> sorter = createSorter();
        Integer[] ints = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
        sorter.sort(ints);
        Integer[] sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(ints, sorted);
    }

    // Digital order
    @Test
    public void testDouble() {
        Sorter<Double> sorter = createSorter();
        Double[] doubles = {1.0, 1.2, 1.4, 1.6, 1.8, 1.1, 1.3, 1.5, 1.7, 1.9};
        sorter.sort(doubles);
        Double[] sorted = {1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9};
        assertArrayEquals(doubles, sorted);
    }

    // large case
    @Test
    public void testLargeints() {
        int len = 10000; // too big would cause StackOverflowError
        Sorter<Integer> sorter = createSorter();
        Integer[] input = new Integer[len];
        Integer[] output = new Integer[len];
        for (int i = 0; i < len; i++) {
            input[i] = len - 1 - i;
            output[i] = i;
        }

        sorter.sort(input);
        assertArrayEquals(input, output);
    }

    // empty case
    @Test
    public void testEmptyStr() {
        Sorter<String> sorter = createSorter();
        String[] animals = {};
        sorter.sort(animals);
        String[] sorted = {};
        assertArrayEquals(animals, sorted);
    }

}
