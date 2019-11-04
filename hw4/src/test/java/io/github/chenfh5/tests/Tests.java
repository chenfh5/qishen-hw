package io.github.chenfh5.tests;

import io.github.chenfh5.sorters.Sorter;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class Tests {

    private static <T extends Comparable<T>> Sorter<T> createSorter() {
        return null;
    }


    @Ignore("No Implement")
    @Test
    public void testString() {
        Sorter<String> sorter = createSorter();
        String[] animals = {"dog", "cat", "pig", "cow", "horse", "bird"};
        sorter.sort(animals);
        String[] sorted = {"bird", "cat", "cow", "dog", "horse", "pig"};
        assertArrayEquals(animals, sorted);
    }

}
