package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import models.Book;
import bst.BST;
import utils.SearchAnalyzer;
import java.util.List;

/**
 * LibrarySearchGUI Class - Main GUI application using Java Swing
 * This is the main window for the BST Library Search Optimization system
 */
public class LibrarySearchGUI extends JFrame {
    
    // Create the Binary Search Tree
    private BST bst;
    
    // GUI Components
    private JPanel panelMain;
    private JPanel panelInput;
    private JPanel panelDisplay;
    private JPanel panelSearch;
    
    // Input Fields
    private JTextField txtBookID;
    private JTextField txtBookName;
    private JTextField txtAuthor;
    private JTextField txtCategory;
    
    // Buttons
    private JButton btnAddBook;
    private JButton btnSearchBook;
    private JButton btnDeleteBook;
    private JButton btnUpdateBook;
    private JButton btnDisplayAll;
    private JButton btnCompareSearch;
    private JButton btnClear;
    
    // Display Area
    private JTextArea textAreaDisplay;
    private JScrollPane scrollPane;
    
    /**
     * Constructor - Initialize the GUI
     */
    public LibrarySearchGUI() {
        // Initialize the BST
        bst = new BST();
        
        // Set up the frame
        setTitle("BST Library Search Optimization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);  // Center on screen
        setResizable(true);
        
        // Initialize all GUI components
        initializeComponents();
        
        // Add components to frame
        add(panelMain);
        
        // Make frame visible
        setVisible(true);
    }
    
    /**
     * Initialize all GUI components
     */
    private void initializeComponents() {
        // Create main panel with BorderLayout
        panelMain = new JPanel(new BorderLayout(10, 10));
        panelMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create input panel (North)
        panelInput = createInputPanel();
        panelMain.add(panelInput, BorderLayout.NORTH);
        
        // Create search panel (West)
        panelSearch = createSearchPanel();
        panelMain.add(panelSearch, BorderLayout.WEST);
        
        // Create display panel (Center)
        panelDisplay = createDisplayPanel();
        panelMain.add(panelDisplay, BorderLayout.CENTER);
    }
    
    /**
     * Create the Input Panel for adding/updating books
     */
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Add / Update Book"));
        
        // Book ID
        panel.add(new JLabel("Book ID:"));
        txtBookID = new JTextField();
        panel.add(txtBookID);
        
        // Book Name
        panel.add(new JLabel("Book Name:"));
        txtBookName = new JTextField();
        panel.add(txtBookName);
        
        // Author
        panel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        panel.add(txtAuthor);
        
        // Category
        panel.add(new JLabel("Category:"));
        txtCategory = new JTextField();
        panel.add(txtCategory);
        
        // Buttons for Add, Update, Clear
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        btnAddBook = new JButton("Add Book");
        btnAddBook.addActionListener(e -> addBook());
        buttonPanel.add(btnAddBook);
        
        btnUpdateBook = new JButton("Update Book");
        btnUpdateBook.addActionListener(e -> updateBook());
        buttonPanel.add(btnUpdateBook);
        
        btnClear = new JButton("Clear Fields");
        btnClear.addActionListener(e -> clearFields());
        buttonPanel.add(btnClear);
        
        panel.add(buttonPanel);
        panel.add(new JLabel(""));  // Empty cell for grid alignment
        
        return panel;
    }
    
    /**
     * Create the Search Panel
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Search & Delete"));
        panel.setPreferredSize(new Dimension(200, 400));
        
        // Search by ID
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Book"));
        
        searchPanel.add(new JLabel("Enter Book ID:"));
        JTextField txtSearchID = new JTextField();
        searchPanel.add(txtSearchID);
        
        btnSearchBook = new JButton("Search");
        btnSearchBook.addActionListener(e -> searchBook(txtSearchID.getText()));
        searchPanel.add(btnSearchBook);
        
        // Compare Search Methods
        JPanel comparePanel = new JPanel();
        comparePanel.setLayout(new BoxLayout(comparePanel, BoxLayout.Y_AXIS));
        comparePanel.setBorder(BorderFactory.createTitledBorder("Compare Search"));
        
        btnCompareSearch = new JButton("Compare (Linear vs BST)");
        btnCompareSearch.addActionListener(e -> compareSearch(txtSearchID.getText()));
        comparePanel.add(btnCompareSearch);
        
        // Delete Book
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Book"));
        
        deletePanel.add(new JLabel("Enter Book ID:"));
        JTextField txtDeleteID = new JTextField();
        deletePanel.add(txtDeleteID);
        
        btnDeleteBook = new JButton("Delete");
        btnDeleteBook.addActionListener(e -> deleteBook(txtDeleteID.getText()));
        deletePanel.add(btnDeleteBook);
        
        // Display All Books
        btnDisplayAll = new JButton("Display All Books");
        btnDisplayAll.addActionListener(e -> displayAllBooks());
        
        // Add all sub-panels to main search panel
        panel.add(searchPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(comparePanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(deletePanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnDisplayAll);
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Create the Display Panel
     */
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Output Display"));
        
        // Text area for displaying results
        textAreaDisplay = new JTextArea();
        textAreaDisplay.setEditable(false);
        textAreaDisplay.setFont(new Font("Courier New", Font.PLAIN, 12));
        textAreaDisplay.setText("Welcome to BST Library Search Optimization!\n\n" +
                              "Click 'Add Book' to add books.\n" +
                              "Click 'Display All Books' to see all books.\n" +
                              "Use Search to find books by ID.\n" +
                              "Click 'Compare' to compare Linear vs BST search times.");
        
        // Scroll pane for text area
        scrollPane = new JScrollPane(textAreaDisplay);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Add a new book to the BST
     */
    private void addBook() {
        try {
            // Get input values from text fields
            int bookID = Integer.parseInt(txtBookID.getText());
            String bookName = txtBookName.getText().trim();
            String author = txtAuthor.getText().trim();
            String category = txtCategory.getText().trim();
            
            // Validate inputs
            if (bookName.isEmpty() || author.isEmpty() || category.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all fields!", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create new book object
            Book book = new Book(bookID, bookName, author, category);
            
            // Try to add to BST
            if (bst.addBook(book)) {
                textAreaDisplay.setText("✓ Book added successfully!\n\n" + book);
                clearFields();
                JOptionPane.showMessageDialog(this, 
                    "Book added successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Book ID already exists!", 
                    "Duplicate Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid Book ID (number)!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Search for a book by ID
     */
    private void searchBook(String bookIDStr) {
        try {
            int bookID = Integer.parseInt(bookIDStr);
            Book book = bst.searchBook(bookID);
            
            if (book != null) {
                textAreaDisplay.setText("✓ Book Found!\n\n" + book);
            } else {
                textAreaDisplay.setText("✗ Book with ID " + bookID + " not found!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid Book ID!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Delete a book by ID
     */
    private void deleteBook(String bookIDStr) {
        try {
            int bookID = Integer.parseInt(bookIDStr);
            
            if (bst.deleteBook(bookID)) {
                textAreaDisplay.setText("✓ Book with ID " + bookID + " deleted successfully!");
                JOptionPane.showMessageDialog(this, 
                    "Book deleted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                textAreaDisplay.setText("✗ Book with ID " + bookID + " not found!");
                JOptionPane.showMessageDialog(this, 
                    "Book not found!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid Book ID!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Update a book's information
     */
    private void updateBook() {
        try {
            int bookID = Integer.parseInt(txtBookID.getText());
            String newName = txtBookName.getText().trim();
            String newAuthor = txtAuthor.getText().trim();
            String newCategory = txtCategory.getText().trim();
            
            if (newName.isEmpty() || newAuthor.isEmpty() || newCategory.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all fields!", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (bst.updateBook(bookID, newName, newAuthor, newCategory)) {
                textAreaDisplay.setText("✓ Book updated successfully!\n\n" + 
                                      bst.searchBook(bookID));
                clearFields();
                JOptionPane.showMessageDialog(this, 
                    "Book updated successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                textAreaDisplay.setText("✗ Book with ID " + bookID + " not found!");
                JOptionPane.showMessageDialog(this, 
                    "Book not found!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid Book ID!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Display all books in inorder traversal (sorted by ID)
     */
    private void displayAllBooks() {
        if (bst.isEmpty()) {
            textAreaDisplay.setText("No books in the library!");
            return;
        }
        
        List<Book> books = bst.getAllBooksInOrder();
        StringBuilder sb = new StringBuilder();
        sb.append("========== ALL BOOKS (Sorted by ID) ==========\n");
        sb.append("Total Books: ").append(bst.getTotalBooks()).append("\n");
        sb.append("==========================================\n\n");
        
        int count = 1;
        for (Book book : books) {
            sb.append(count++).append(". ").append(book).append("\n\n");
        }
        
        textAreaDisplay.setText(sb.toString());
    }
    
    /**
     * Compare Linear Search vs BST Search performance
     */
    private void compareSearch(String bookIDStr) {
        try {
            int bookID = Integer.parseInt(bookIDStr);
            
            if (bst.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Add some books first!", 
                    "Empty Library", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Get all books for linear search
            List<Book> allBooks = bst.getAllBooksInOrder();
            
            // Perform both searches
            SearchAnalyzer.SearchResult linearResult = SearchAnalyzer.linearSearch(allBooks, bookID);
            SearchAnalyzer.SearchResult bstResult = SearchAnalyzer.bstSearch(bst, bookID);
            
            // Display comparison results
            StringBuilder sb = new StringBuilder();
            sb.append("========== SEARCH COMPARISON ==========\n\n");
            sb.append("Search ID: ").append(bookID).append("\n");
            sb.append("Total Books: ").append(bst.getTotalBooks()).append("\n\n");
            
            sb.append("--- LINEAR SEARCH (O(n)) ---\n");
            sb.append(linearResult).append("\n\n");
            
            sb.append("--- BST SEARCH (O(log n)) ---\n");
            sb.append(bstResult).append("\n\n");
            
            // Calculate speedup
            if (bstResult.executionTimeNanos > 0) {
                double speedup = (double) linearResult.executionTimeNanos / bstResult.executionTimeNanos;
                sb.append("--- SPEEDUP ---\n");
                sb.append(String.format("BST is %.2fx faster than Linear Search\n", speedup));
                sb.append(String.format("Linear comparisons: %d vs BST comparisons: %d\n",
                    linearResult.comparisons, bstResult.comparisons));
            }
            
            sb.append("\n========================================");
            textAreaDisplay.setText(sb.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid Book ID!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Clear all input fields
     */
    private void clearFields() {
        txtBookID.setText("");
        txtBookName.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtBookID.requestFocus();
    }
    
    /**
     * Main method - Entry point of the application
     */
    public static void main(String[] args) {
        // Create GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new LibrarySearchGUI();
        });
    }
}
