package bst;

import models.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * BST Class - Implementation of Binary Search Tree for the library system
 * Stores books sorted by their ID for efficient searching
 */
public class BST {

    // Root node of the tree
    private Node root;

    /**
     * Constructor - Initialize an empty tree
     */
    public BST() {
        this.root = null;
    }

    /**
     * Add a new book to the BST
     * Time Complexity: O(log n) average case, O(n) worst case
     * @param book - The book to be added
     * @return true if added successfully, false if book ID already exists
     */
    public boolean addBook(Book book) {
        if (root == null) {
            // Tree is empty, create new root
            root = new Node(book);
            return true;
        } else {
            // Use helper method to insert recursively
            return addBookRecursive(root, book);
        }
    }

    /**
     * Helper method for recursive insertion
     * @param node - Current node being examined
     * @param book - Book to be inserted
     * @return true if added successfully, false if duplicate ID
     */
    private boolean addBookRecursive(Node node, Book book) {

        // Compare book ID with current node book ID
        int comparison = book.compareTo(node.book);

        if (comparison < 0) {

            // Go to left subtree
            if (node.left == null) {
                node.left = new Node(book);
                return true;
            } else {
                return addBookRecursive(node.left, book);
            }

        } else if (comparison > 0) {

            // Go to right subtree
            if (node.right == null) {
                node.right = new Node(book);
                return true;
            } else {
                return addBookRecursive(node.right, book);
            }

        } else {

            // Duplicate ID found
            return false;
        }
    }

    /**
     * Search for a book by ID
     * Time Complexity: O(log n) average case, O(n) worst case
     * @param bookID - Book ID to search
     * @return Book if found, null otherwise
     */
    public Book searchBook(int bookID) {
        return searchBookRecursive(root, bookID);
    }

    /**
     * Helper method for recursive search
     * @param node - Current node
     * @param bookID - ID to search
     * @return Book if found, null otherwise
     */
    private Book searchBookRecursive(Node node, int bookID) {

        // Base case: book not found
        if (node == null) {
            return null;
        }

        if (bookID < node.book.getBookID()) {

            // Search left subtree
            return searchBookRecursive(node.left, bookID);

        } else if (bookID > node.book.getBookID()) {

            // Search right subtree
            return searchBookRecursive(node.right, bookID);

        } else {

            // Book found
            return node.book;
        }
    }

    /**
     * Delete a book by ID
     * Time Complexity: O(log n) average case, O(n) worst case
     * @param bookID - ID of book to delete
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteBook(int bookID) {

        // First check if book exists
        if (searchBook(bookID) == null) {
            return false;
        }

        // Delete the book
        root = deleteBookRecursive(root, bookID);
        return true;
    }

    /**
     * Helper method for recursive deletion
     * @param node - Current node
     * @param bookID - Book ID to delete
     * @return Updated node after deletion
     */
    private Node deleteBookRecursive(Node node, int bookID) {

        if (node == null) {
            return null;
        }

        if (bookID < node.book.getBookID()) {

            // Go left
            node.left = deleteBookRecursive(node.left, bookID);

        } else if (bookID > node.book.getBookID()) {

            // Go right
            node.right = deleteBookRecursive(node.right, bookID);

        } else {

            // Book found - perform deletion

            // Case 1: No children (Leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: Only right child
            if (node.left == null) {
                return node.right;
            }

            // Case 3: Only left child
            if (node.right == null) {
                return node.left;
            }

            // Case 4: Two children
            // Find inorder successor (smallest node in right subtree)
            Node minNode = findMinNode(node.right);

            // Replace current node data
            node.book = minNode.book;

            // Delete duplicate node
            node.right = deleteBookRecursive(
                    node.right,
                    minNode.book.getBookID()
            );
        }

        return node;
    }

    /**
     * Find minimum node in subtree
     * @param node - Root node of subtree
     * @return Minimum node
     */
    private Node findMinNode(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * Update book information
     * @param bookID - ID of book
     * @param newName - New name
     * @param newAuthor - New author
     * @param newCategory - New category
     * @return true if updated successfully
     */
    public boolean updateBook(int bookID,
                              String newName,
                              String newAuthor,
                              String newCategory) {

        Book book = searchBook(bookID);

        if (book != null) {
            book.setBookName(newName);
            book.setAuthor(newAuthor);
            book.setCategory(newCategory);
            return true;
        }

        return false;
    }

    /**
     * Get all books in sorted order
     * Uses Inorder Traversal
     * @return List of books
     */
    public List<Book> getAllBooksInOrder() {

        List<Book> books = new ArrayList<>();
        inorderTraversal(root, books);

        return books;
    }

    /**
     * Inorder Traversal
     * Left -> Root -> Right
     * Gives sorted output
     */
    private void inorderTraversal(Node node,
                                  List<Book> books) {

        if (node != null) {

            // Visit left subtree
            inorderTraversal(node.left, books);

            // Add current node
            books.add(node.book);

            // Visit right subtree
            inorderTraversal(node.right, books);
        }
    }

    /**
     * Check if BST is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get total number of books
     * @return number of books
     */
    public int getTotalBooks() {
        return countNodes(root);
    }

    /**
     * Count nodes recursively
     * @param node - Current node
     * @return count
     */
    private int countNodes(Node node) {

        if (node == null) {
            return 0;
        }

        return 1
                + countNodes(node.left)
                + countNodes(node.right);
    }
}
