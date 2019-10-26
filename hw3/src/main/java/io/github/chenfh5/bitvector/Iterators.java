package io.github.chenfh5.bitvector;

import java.util.NoSuchElementException;
import java.util.Stack;

public class Iterators<T> implements Iterator {
    private Stack<T> data;

    Iterators(Stack<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasMoreElements() {
        return !data.empty();
    }

    @Override
    public T nextElement() {
        if (hasMoreElements()) {
            return data.pop();
        }
        throw new NoSuchElementException("no more remaining elements");
    }
}

