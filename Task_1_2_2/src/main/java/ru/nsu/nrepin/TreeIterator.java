package ru.nsu.nrepin;

import java.util.Iterator;
import java.util.List;

/**
 * Class that implements iterator for Tree class.
 *
 * @param <T> element type
 */
public class TreeIterator<T> implements Iterator<T> {

    private Iterator<Tree<T>> iterator;

    /**
     * Consructs new tree iterator.
     *
     * @param traversal traversal of tree
     */
    public TreeIterator(List<Tree<T>> traversal) {
        iterator = traversal.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {

        Tree<T> nextItem = iterator.next();

        nextItem.decrementIteratorCount();

        return nextItem.getValue();
    }
}
