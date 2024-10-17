package eci.edu.arep;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    void testAdd() {
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(7));
        assertFalse(bst.add(5));
        assertEquals(3, bst.size());
    }

    @Test
    void testRemove() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        assertTrue(bst.remove(3));
        assertFalse(bst.contains(3));
        assertEquals(2, bst.size());

        assertFalse(bst.remove(10)); // Elemento no existente
    }

    @Test
    void testContains() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(7));
        assertFalse(bst.contains(10));
    }

    @Test
    void testFindMinMax() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(9);

        assertEquals(1, bst.findMin());
        assertEquals(9, bst.findMax());
    }

    @Test
    void testHeight() {
        assertEquals(-1, bst.height()); // Árbol vacío

        bst.add(5);
        assertEquals(0, bst.height());

        bst.add(3);
        bst.add(7);
        assertEquals(1, bst.height());

        bst.add(1);
        assertEquals(2, bst.height());
    }

    @Test
    void testIsBalanced() {
        assertTrue(bst.isBalanced()); // Árbol vacío está balanceado

        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertTrue(bst.isBalanced());

        bst.add(1);
        bst.add(2);
        assertFalse(bst.isBalanced());
    }

    @Test
    void testClear() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        assertFalse(bst.isEmpty());

        bst.clear();

        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
    }

    @Test
    void testIterator() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(9);

        List<Integer> elements = new ArrayList<>();
        for (Integer element : bst) {
            elements.add(element);
        }

        assertEquals(List.of(1, 3, 5, 7, 9), elements);
    }

    @Test
    void testTraversals() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(9);

        assertEquals("1 3 5 7 9 ", captureOutput(bst::inOrderTraversal));
        assertEquals("5 3 1 7 9 ", captureOutput(bst::preOrderTraversal));
        assertEquals("1 3 9 7 5 ", captureOutput(bst::postOrderTraversal));
        assertEquals("5 3 7 1 9 ", captureOutput(bst::levelOrderTraversal));
    }


    private String captureOutput(Runnable method) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        method.run();
        System.setOut(System.out);
        return out.toString();
    }

    @Test
    void testSequentialAddRemove() {
        for (int i = 0; i < 10; i++) {
            bst.add(i);
        }
        assertEquals(10, bst.size());

        for (int i = 0; i < 10; i++) {
            assertTrue(bst.remove(i));
        }
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
    }

    @Test
    void testHeightOfSkewedTree() {
        for (int i = 1; i <= 10; i++) {
            bst.add(i);
        }
        assertEquals(9, bst.height()); // Debe ser una altura de 9 (nodos en línea)
    }

    @Test
    void testIsBalancedWithMultipleAdditions() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        assertTrue(bst.isBalanced());

        bst.add(2);
        bst.add(4);
        bst.add(1);
        assertFalse(bst.isBalanced());
    }

    @Test
    void testTraversalsInUnbalancedTree() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(3);
        bst.add(7);
        bst.add(12);
        bst.add(17);

        assertEquals("3 5 7 10 12 15 17 ", captureOutput(bst::inOrderTraversal));
        assertEquals("10 5 3 7 15 12 17 ", captureOutput(bst::preOrderTraversal));
        assertEquals("3 7 5 12 17 15 10 ", captureOutput(bst::postOrderTraversal));
        assertEquals("10 5 15 3 7 12 17 ", captureOutput(bst::levelOrderTraversal));
    }

    @Test
    void testIteratorWithEmptyTree() {
        List<Integer> elements = new ArrayList<>();
        for (Integer element : bst) {
            elements.add(element);
        }
        assertTrue(elements.isEmpty());
    }

    @Test
    void testAddDuplicateElements() {
        bst.add(5);
        bst.add(5); // Duplicado
        assertEquals(1, bst.size()); // Debería seguir siendo 1
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(8);

        // Remover un nodo con dos hijos (5)
        assertTrue(bst.remove(5));
        assertFalse(bst.contains(5));
        assertEquals(6, bst.size());
        assertTrue(bst.contains(6)); // El nuevo raíz debe ser 6
    }

    @Test
    void testClearOnLargeTree() {
        for (int i = 0; i < 1000; i++) {
            bst.add(i);
        }
        assertFalse(bst.isEmpty());

        bst.clear();
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
    }

}