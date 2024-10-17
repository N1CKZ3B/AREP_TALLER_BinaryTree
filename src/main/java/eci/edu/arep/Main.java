package eci.edu.arep;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        System.out.println("Creando un árbol binario de búsqueda y añadiendo elementos:");
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(9);
        bst.add(6);

        System.out.println("Árbol después de añadir elementos:");
        bst.printTree();

        System.out.println("\nBuscando elementos:");
        System.out.println("¿Contiene 7? " + bst.contains(7));
        System.out.println("¿Contiene 4? " + bst.contains(4));

        System.out.println("\nEliminando el elemento 3:");
        bst.remove(3);
        System.out.println("Árbol después de eliminar 3:");
        bst.printTree();

        System.out.println("\nAñadiendo elementos 4 y 8:");
        bst.add(4);
        bst.add(8);
        System.out.println("Árbol después de añadir 4 y 8:");
        bst.printTree();

        System.out.println("\nEncontrando el mínimo y máximo:");
        System.out.println("Mínimo: " + bst.findMin());
        System.out.println("Máximo: " + bst.findMax());

        System.out.println("\nAltura del árbol: " + bst.height());
        System.out.println("¿Está balanceado? " + bst.isBalanced());

        System.out.println("\nRecorridos del árbol:");
        System.out.print("In-order: ");
        bst.inOrderTraversal();
        System.out.print("\nPre-order: ");
        bst.preOrderTraversal();
        System.out.print("\nPost-order: ");
        bst.postOrderTraversal();
        System.out.print("\nLevel-order: ");
        bst.levelOrderTraversal();

        System.out.println("\n\nUsando el iterador para recorrer el árbol:");
        for (Integer value : bst) {
            System.out.print(value + " ");
        }

        System.out.println("\n\nLimpiando el árbol:");
        bst.clear();
        System.out.println("Árbol después de limpiar:");
        bst.printTree();
        System.out.println("Tamaño del árbol después de limpiar: " + bst.size());
    }
}