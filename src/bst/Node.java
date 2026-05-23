package bst;

import models.Book;

/**
 * Node Class - Represents a single node in the Binary Search Tree
 * Each node contains a book and references to left and right children
 */
public class Node {
    
    // Instance variables
    public Book book;      // The book data stored in this node
    public Node left;      // Reference to left child (smaller book IDs)
    public Node right;     // Reference to right child (larger book IDs)
    
    /**
     * Constructor to create a new node with a book
     * @param book - The book object to be stored in this node
     */
    public Node(Book book) {
        this.book = book;
        this.left = null;   // Initially, left child is empty
        this.right = null;  // Initially, right child is empty
    }
}
