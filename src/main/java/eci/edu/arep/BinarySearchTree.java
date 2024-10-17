package eci.edu.arep;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractSet<E> {
    private Node root;
    private int size;

    private class Node {
        E value;
        Node left, right;

        Node(E value) {
            this.value = value;
        }
    }
    public void printTree() {
        printTreeHelper(root, "", true);
    }

    private void printTreeHelper(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.value);
            printTreeHelper(node.right, prefix + (isLeft ? "│   " : "    "), false);
            printTreeHelper(node.left, prefix + (isLeft ? "│   " : "    "), true);
        }
    }

    @Override
    public boolean add(E value) {
        if (contains(value)) {
            return false;
        }
        root = insert(root, value);
        size++;
        return true;
    }

    private Node insert(Node node, E value) {
        if (node == null) {
            return new Node(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof Comparable)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        E value = (E) obj;
        if (!contains(value)) {
            return false;
        }
        root = delete(root, value);
        size--;
        return true;
    }

    private Node delete(Node node, E value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = delete(node.left, value);
        } else if (cmp > 0) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.value = findMin(node.right).value;
            node.right = delete(node.right, node.value);
        }
        return node;
    }

    @Override
    public boolean contains(Object obj) {
        if (!(obj instanceof Comparable)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        E value = (E) obj;
        return search(root, value) != null;
    }

    private Node search(Node node, E value) {
        if (node == null || node.value.equals(value)) {
            return node;
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public void preOrderTraversal() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.value + " ");
        }
    }

    public E findMin() {
        if (isEmpty()) {
            return null;
        }
        return findMin(root).value;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public E findMax() {
        if (isEmpty()) {
            return null;
        }
        return findMax(root).value;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }



    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<E> {
        private Stack<Node> stack = new Stack<>();

        BSTIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node node = stack.pop();
            pushLeft(node.right);
            return node.value;
        }
    }
}