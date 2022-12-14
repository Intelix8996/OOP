package ru.nsu.nrepin;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * Class that implements iterator for Tree class.
 *
 * @param <T> element type
 */
public class TreeIterator<T> implements Iterator<T> {

    private final Tree<T> rootNode;
    private final Tree.TraversalType traversalType;
    private final Stack<Tree<T>> stack;
    private final Queue<Tree<T>> queue;
    private final int expectedModCount;

    /**
     * Constructs new tree iterator.
     *
     * @param node starting node
     * @param traversalType traversal type
     */
    public TreeIterator(Tree<T> node, Tree.TraversalType traversalType) {
        this.traversalType = traversalType;
        this.rootNode = node;
        this.expectedModCount = node.getModCount();

        stack = new Stack<>();
        queue = new ArrayDeque<>();

        switch (traversalType) {
            case DFS:
                stack.push(node);
                break;
            case BFS:
                queue.add(node);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public boolean hasNext() {
        switch (traversalType) {
            case DFS:
                return !stack.isEmpty();
            case BFS:
                return !queue.isEmpty();
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public T next() {

        if (rootNode.getModCount() > expectedModCount) {
            throw new ConcurrentModificationException();
        }

        Tree<T> node;
        List<Tree<T>> subtrees;

        switch (traversalType) {
            case DFS:
                node = stack.pop();
                subtrees = node.getSubtrees();

                for (int i = subtrees.size() - 1; i >= 0; --i) {
                    stack.push(subtrees.get(i));
                }

                return node.getValue();
            case BFS:
                node = queue.poll();

                if (node == null) {
                    throw new NoSuchElementException();
                }

                subtrees = node.getSubtrees();

                queue.addAll(subtrees);

                return node.getValue();
            default:
                throw new IllegalStateException();
        }
    }
}
