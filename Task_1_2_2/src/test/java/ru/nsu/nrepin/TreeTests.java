package ru.nsu.nrepin;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for Tree.
 */
public class TreeTests {

    public Tree<Integer> tree;

    public List<Integer> refDepthFirst;
    public List<Integer> refBreadthFirst;

    /**
     * Initialize reference lists before each test.
     */
    @BeforeEach
    public void initRefLists() {
        refDepthFirst = List.of(1, 2, 4, 10, 11, 12, 5, 6, 3, 7, 8, 9, 13, 14);
        refBreadthFirst = List.of(1, 2, 3, 4, 5, 7, 8, 9, 13, 14, 10, 11, 12, 6);
    }

    /**
     * Initialize tree before each test.
     */
    @BeforeEach
    public void initTree() {
        tree = new Tree<Integer>();
        tree.setValue(1);

        Tree<Integer> a = tree.add(2);

        Tree<Integer> aa = a.add(4);

        aa.add(10);
        aa.add(11);
        aa.add(12);

        Tree<Integer> ab = a.add(5);

        ab.add(6);

        Tree<Integer> b = tree.add(3);

        b.add(7);
        b.add(8);
        b.add(9);

        b.add(13);
        tree.add(b, 14);
    }

    /**
     * Test traversals.
     */
    @Test
    public void testTreeTraversals() {

        List<Integer> traversalBreadthFirst = tree.asList(Tree.TraversalType.BFS);
        List<Integer> traversalDepthFirst = tree.asList(Tree.TraversalType.DFS);

        Assertions.assertEquals(refBreadthFirst, traversalBreadthFirst);
        Assertions.assertEquals(refDepthFirst, traversalDepthFirst);
    }

    /**
     * Test iterators.
     */
    @Test
    public void testTreeIterators() {
        Iterator<Integer> iteratorDepthFirst = tree.iterator(Tree.TraversalType.DFS);
        Iterator<Integer> refIteratorDepthFirst = refDepthFirst.iterator();

        Iterator<Integer> iteratorBreadthFirst = tree.iterator(Tree.TraversalType.BFS);
        Iterator<Integer> refIteratorBreadthFirst = refBreadthFirst.iterator();

        Iterator<Integer> defaultIterator = tree.iterator();

        while (iteratorDepthFirst.hasNext()) {
            Integer refNext = refIteratorDepthFirst.next();

            Assertions.assertEquals(refNext, iteratorDepthFirst.next());
            Assertions.assertEquals(refNext, defaultIterator.next());
        }

        while (iteratorBreadthFirst.hasNext()) {
            Assertions.assertEquals(refIteratorBreadthFirst.next(), iteratorBreadthFirst.next());
        }
    }

    /**
     * Test different tree methods.
     */
    @Test
    public void testTreeMethods() {
        refDepthFirst = List.of(1, 155, 4, 10, 11, /*12, */5, 6, 3, 7, 8, 9, 13, 14);

        tree.remove(12);
        tree.getChild(0).setValue(155);

        List<Integer> traversalDepthFirst = tree.asList();

        Assertions.assertEquals(refDepthFirst, traversalDepthFirst);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> tree.getChild(100));
    }

    /**
     * Test ConcurrentModificationException.
     */
    @Test
    public void testConcurrentModificationException() {
        int count = 0;

        for (var i : tree) {
            if (count == 2) {
                Assertions.assertThrows(
                        ConcurrentModificationException.class,
                        () -> tree.getChild(1)
                                .getChild(0)
                                .setValue(123)
                );
            }

            if (count == 3) {
                Assertions.assertThrows(
                        ConcurrentModificationException.class,
                        () -> tree.getChild(1)
                                .getChild(0)
                                .add(1223)
                );
            }

            if (count == 4) {
                Assertions.assertThrows(
                        ConcurrentModificationException.class,
                        () -> tree.remove(14)
                );
            }

            if (count == 5) {
                Assertions.assertDoesNotThrow(() -> tree.setValue(1255));
            }

            count++;
        }
    }

    /**
     * Test prettyPrint().
     */
    @Test
    public void testPrettyPrint() {
        tree.prettyPrint();
    }
}
