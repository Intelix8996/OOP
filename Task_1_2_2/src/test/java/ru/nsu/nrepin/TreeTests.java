package ru.nsu.nrepin;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeTests {

    public Tree<Integer> tree;

    public List<Integer> refDFS;
    public List<Integer> refBFS;

    @BeforeEach
    public void initRefLists() {
        refDFS = List.of( 1, 2, 4, 10, 11, 12, 5, 6, 3, 7, 8, 9, 13, 14 );
        refBFS = List.of( 1, 2, 3, 4, 5, 7, 8, 9, 13, 14, 10, 11, 12, 6 );
    }

    @BeforeEach
    public void initTree() {
        tree = new Tree<Integer>();
        tree.setValue(1);

        Tree<Integer> a = tree.add(2);
        Tree<Integer> b = tree.add(3);

        Tree<Integer> aa = a.add(4);

        aa.add(10);
        aa.add(11);
        aa.add(12);

        Tree<Integer> ab = a.add(5);

        ab.add(6);

        b.add(7);
        b.add(8);
        b.add(9);

        b.add(13);
        tree.add(b, 14);
    }

    @Test
    public void testTreeTraversals() {

        List<Integer> traversalBFS = tree.asList(Tree.TraversalType.BFS);
        List<Integer> traversalDFS = tree.asList(Tree.TraversalType.DFS);

        Assertions.assertEquals(refBFS, traversalBFS);
        Assertions.assertEquals(refDFS, traversalDFS);
    }

    @Test
    public void testTreeIterators() {
        Iterator<Integer> iteratorDFS = tree.iterator(Tree.TraversalType.DFS);
        Iterator<Integer> refIteratorDFS = refDFS.iterator();

        Iterator<Integer> iteratorBFS = tree.iterator(Tree.TraversalType.BFS);
        Iterator<Integer> refIteratorBFS = refBFS.iterator();

        Iterator<Integer> defaultIterator = tree.iterator();

        while (iteratorDFS.hasNext() && refIteratorDFS.hasNext() && defaultIterator.hasNext()) {
            Integer refNext = refIteratorDFS.next();

            Assertions.assertEquals(refNext, iteratorDFS.next());
            Assertions.assertEquals(refNext, defaultIterator.next());
        }

        while (iteratorBFS.hasNext() && refIteratorBFS.hasNext()) {
            Assertions.assertEquals(refIteratorBFS.next(), iteratorBFS.next());
        }
    }

    @Test
    public void testTreeMethods() {
        refDFS = List.of( 1, 155, 4, 10, 11, /*12, */5, 6, 3, 7, 8, 9, 13, 14 );

        tree.remove(12);
        tree.getChild(0).setValue(155);

        List<Integer> traversalDFS = tree.asList();

        Assertions.assertEquals(refDFS, traversalDFS);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> tree.getChild(100));
    }

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

    @Test
    public void testPrettyPrint() {
        tree.prettyPrint();
    }
}
