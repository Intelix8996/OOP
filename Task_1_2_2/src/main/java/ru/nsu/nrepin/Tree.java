package ru.nsu.nrepin;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class implements Tree data structure.
 *
 * @param <T> type of stored elements
 */
public class Tree<T> implements Iterable<T> {

    private T value;

    private List<Tree<T>> subtrees;

    private int dependentIteratorCount;

    /**
     * Defines types of tree traversal: depth-first and breadth-first.
     */
    public enum TraversalType {
        DFS, BFS
    }

    /**
     * Constructs new tree node with given value.
     *
     * @param val value to initialize node with
     */
    public Tree(T val) {
        value = val;
        subtrees = new LinkedList<>();
        dependentIteratorCount = 0;
    }

    /**
     * Constructs new tree node with default (null) value.
     */
    public Tree() {
        this(null);
    }

    /**
     * Creates default (DepthFirst) iterator over tree.
     *
     * @return tree iterator (DepthFirst)
     */
    @Override
    public Iterator<T> iterator() {
        return iterator(TraversalType.DFS);
    }

    /**
     * Creates iterator over tree of given type.
     *
     * @param type defines whether iteration will be depth-first or breath-first
     * @return tree iterator
     */
    public Iterator<T> iterator(TraversalType type) {

        List<Tree<T>> searchResult = traverseTree(type);

        for (var i : searchResult) {
            i.incrementIteratorCount();
        }

        return new TreeIterator<>(searchResult);
    }

    /**
     * Increments internal iterator counter.
     * This function is used to support <i>ConcurrentModificationException</i>.
     */
    public void incrementIteratorCount() {
        dependentIteratorCount++;
    }

    /**
     * Decrements internal iterator counter.
     * This function is used to support <i>ConcurrentModificationException</i>.
     */
    public void decrementIteratorCount() {
        dependentIteratorCount--;
    }

    /**
     * Returns child by given index.
     *
     * @param index index of child to be returned
     * @return tree child
     */
    public Tree<T> getChild(int index) {
        if (index >= subtrees.size()) {
            throw new IndexOutOfBoundsException();
        }

        return subtrees.get(index);
    }

    /**
     * Adds new element to current tree node.
     * Function wraps element into tree node and adds it to current node.
     *
     * @param newElem element to be added.
     * @return added tree node
     */
    public Tree<T> add(T newElem) {

        if (dependentIteratorCount > 0) {
            throw new ConcurrentModificationException();
        }

        Tree<T> newSubtree = new Tree<>(newElem);

        subtrees.add(newSubtree);

        return newSubtree;
    }

    /**
     * Adds new element to given tree node.
     * Function wraps element into tree node and adds it to given node.
     *
     * @param node    node to which new element will be added
     * @param newElem element to be added
     * @return added tree node
     */
    public Tree<T> add(Tree<T> node, T newElem) {
        return node.add(newElem);
    }

    /**
     * Remove element from tree.
     * Finds first occurrence of element in tree and removes it.
     *
     * @param elem element to be removed
     * @return <i>true</i> if element was successfully removed and
     *         <i>false</i> if element was not found
     */
    public boolean remove(T elem) {

        for (int i = 0; i < subtrees.size(); ++i) {
            Tree<T> subtree = subtrees.get(i);

            if (subtree.getValue() == elem) {

                if (dependentIteratorCount > 0) {
                    throw new ConcurrentModificationException();
                }

                subtrees.remove(i);
                return true;
            }

            if (subtree.remove(elem)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sets value of current node.
     *
     * @param value value to be set
     */
    public void setValue(T value) {

        if (dependentIteratorCount > 0) {
            throw new ConcurrentModificationException();
        }

        this.value = value;
    }

    /**
     * Returns value of current node.
     *
     * @return value of current node
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns default (DepthFirst) traversal as list.
     *
     * @return DepthFirst traversal
     */
    public List<T> asList() {
        return asList(TraversalType.DFS);
    }

    /**
     * Returns traversal of given type as list.
     *
     * @param type type of traversal
     * @return traversal of given type
     */
    public List<T> asList(TraversalType type) {

        List<Tree<T>> searchResult = traverseTree(type);
        List<T> list = new LinkedList<>();

        for (var i : searchResult) {
            list.add(i.getValue());
        }

        return list;
    }

    /**
     * Performs traversal of given type and returns it as list of nodes.
     *
     * @return traversal as list of nodes
     */
    public List<Tree<T>> traverseTree(TraversalType type) {
        List<Tree<T>> traversal;

        switch (type) {
            case DFS:
                traversal = traverseDepthFirst();
                break;
            case BFS:
                traversal = traverseBreadthFirst();
                break;
            default:
                throw new IllegalStateException();
        }

        return traversal;
    }

    /**
     * Performs DepthFirst traversal and returns it as list of nodes.
     *
     * @return traversal as list of nodes
     */
    private List<Tree<T>> traverseDepthFirst() {

        List<Tree<T>> result = new LinkedList<>();

        result.add(this);

        for (Tree<T> subtree : subtrees) {
            result.addAll(subtree.traverseDepthFirst());
        }

        return result;
    }

    /**
     * Performs BreadthFirst traversal and returns it as list of nodes.
     *
     * @return traversal as list of nodes
     */
    private List<Tree<T>> traverseBreadthFirst() {

        Queue<Tree<T>> queue = new ArrayDeque<>();
        List<Tree<T>> result = new LinkedList<>();

        queue.add(this);

        while (queue.size() != 0) {
            Tree<T> node = queue.poll();

            result.add(node);

            queue.addAll(node.subtrees);
        }

        return result;
    }

    /**
     * Prints tree with pseudo-graphics to standard output.
     */
    public void prettyPrint() {
        System.out.println("root");
        prettyPrint(3);
    }

    private void prettyPrint(int depth) {

        int offset = 0;

        if (getValue() != null) {
            for (int i = 0; i < depth; ++i) {
                System.out.print(" ");
            }

            System.out.print("|- ");
            System.out.println(getValue());

            offset = getValue().toString().length() + 2;
        }

        for (Tree<T> subtree : subtrees) {
            subtree.prettyPrint(depth + offset);
        }
    }
}
