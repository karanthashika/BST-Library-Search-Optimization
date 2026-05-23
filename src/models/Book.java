package models;

/**
 * Book Class - Represents a single book in the library
 * This is the data model that stores book information
 */
public class Book implements Comparable<Book> {
    
    // Instance variables (attributes of a book)
    private int bookID;          // Unique identifier for each book
    private String bookName;     // Title of the book
    private String author;       // Author of the book
    private String category;     // Category/Genre of the book
    
    /**
     * Constructor to initialize a Book object
     * @param bookID - Unique book ID
     * @param bookName - Name of the book
     * @param author - Author of the book
     * @param category - Category of the book
     */
    public Book(int bookID, String bookName, String author, String category) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
    }
    
    // Getter methods (accessors)
    public int getBookID() {
        return bookID;
    }
    
    public String getBookName() {
        return bookName;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getCategory() {
        return category;
    }
    
    // Setter methods (mutators)
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Comparable interface implementation for BST sorting
     * Compares books by their ID
     * @param other - Book object to compare with
     * @return negative if this.bookID < other.bookID
     *         zero if they are equal
     *         positive if this.bookID > other.bookID
     */
    @Override
    public int compareTo(Book other) {
        return Integer.compare(this.bookID, other.bookID);
    }
    
    /**
     * toString method - Used to display book information
     * @return formatted string with all book details
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Author: %s | Category: %s", 
                             bookID, bookName, author, category);
    }
}
