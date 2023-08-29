package org.example;

import java.util.Scanner;

public class RedBlackTree {

    private static Node root = null;

    /**
     * this method to rotate a node counterclockwise.
     * @param myNode node for rotate
     * @return return node after rotate
     */
    Node rotateLeft(Node myNode) {
        System.out.printf("turn left\n");
        Node child = myNode.right;
        Node childLeft = child.left;

        child.left = myNode;
        myNode.right = childLeft;

        return child;
    }

    /**
     * this method to rotate the node clockwise.
     * @param myNode node for rotate
     * @return return node after rotate
     */
    Node rotateRight(Node myNode) {
        System.out.printf("turn right\n");
        Node child = myNode.left;
        Node childRight = child.right;

        child.right = myNode;
        myNode.left = childRight;

        return child;
    }

    /**
     * this method is for checking whether a node is red or not.
     */
    boolean isRed(Node myNode) {
        if (myNode == null) {
            return false;
        }
        return (myNode.color == true);
    }

    /**
     * this method is for changing the color of two nodes.
     */
    void swapColors(Node node1, Node node2) {
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    /**
     * this method is pasting into a left-handed red-black tree..
     */
    Node insert(Node myNode, int data) {
// Regular embed code
        if (myNode == null) {
            return new Node(data);
        }

        if (data < myNode.data) {
            myNode.left = insert(myNode.left, data);
        } else if (data > myNode.data) {
            myNode.right = insert(myNode.right, data);
        } else {
            return myNode;
        }

// Case 1.
        // when the right child is red and the left child is black or does not exist.
        if (isRed(myNode.right) && !isRed(myNode.left)) {
// Rotate node left
            myNode = rotateLeft(myNode);

// Swap child node colors should always be red
            swapColors(myNode, myNode.left);
        }

// Case 2
        // when the left child as well as the left grandson are highlighted in red
        if (isRed(myNode.left) && isRed(myNode.left.left)) {
// Rotate node to the right
            myNode = rotateRight(myNode);
            swapColors(myNode, myNode.right);
        }

// Case 3
        // when both left and right children are colored red.
        if (isRed(myNode.left) && isRed(myNode.right)) {
// Invert the color of the node's left and right children.
            myNode.color = !myNode.color;

// Change color to black.
            myNode.left.color = false;
            myNode.right.color = false;
        }

        return myNode;
    }

    /**
     * this method for traversal in order
     */
    void inorder(Node node) {
        if (node != null)
        {
            inorder(node.left);
            char c = 'R';
            if (node.color == false)
                c = 'B';
            System.out.print(node.data + ""+c+" ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        RedBlackTree node = new RedBlackTree();
        Scanner scan = new Scanner(System.in);

        char ch;
        do {
            System.out.println("Enter a number");

            int num = scan.nextInt();
            root = node.insert(root, num);

            node.inorder(root);
            System.out.println("\nDo you want to continue? (Enter y or n)");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}