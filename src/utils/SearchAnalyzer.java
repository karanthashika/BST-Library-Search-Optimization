package utils;

import models.Book;
import bst.BST;
import java.util.List;

/**
 * SearchAnalyzer Class - Compares Linear Search vs BST Search performance
 * This utility helps demonstrate the time complexity difference
 */
public class SearchAnalyzer {
    
    /**
     * Perform Linear Search - Search through all books sequentially
     * Time Complexity: O(n) - worst case, must check every book
     * @param books - List of all books
     * @param bookID - ID to search for
     * @return SearchResult object with search details
     */
    public static SearchResult linearSearch(List<Book> books, int bookID) {
        // Record the starting time
        long startTime = System.nanoTime();
        
        int comparisons = 0;
        Book foundBook = null;
        
        // Check each book one by one
        for (Book book : books) {
            comparisons++;
            if (book.getBookID() == bookID) {
                foundBook = book;
                break;  // Stop when book is found
            }
        }
        
        // Record the ending time
        long endTime = System.nanoTime();
        
        // Calculate elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;
        
        // Return result object with all details
        return new SearchResult("Linear Search", foundBook, comparisons, elapsedTime);
    }
    
    /**
     * Perform BST Search - Search using Binary Search Tree structure
     * Time Complexity: O(log n) average case - much faster!
     * @param bst - The Binary Search Tree
     * @param bookID - ID to search for
     * @return SearchResult object with search details
     */
    public static SearchResult bstSearch(BST bst, int bookID) {
        // Record the starting time
        long startTime = System.nanoTime();
        
        // Search using BST (internal comparisons are counted)
        Book foundBook = bst.searchBook(bookID);
        
        // Record the ending time
        long endTime = System.nanoTime();
        
        // Calculate elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;
        
        // Estimate comparisons: approximately log2(n) for a balanced tree
        int estimatedComparisons = (int) Math.ceil(Math.log(bst.getTotalBooks() + 1) / Math.log(2));
        
        // Return result object with all details
        return new SearchResult("BST Search", foundBook, estimatedComparisons, elapsedTime);
    }
    
    /**
     * Inner class to store and display search results
     */
    public static class SearchResult {
        public String searchMethod;      // Which method was used
        public Book foundBook;           // The book found (or null)
        public int comparisons;          // Number of comparisons made
        public long executionTimeNanos;  // Time taken in nanoseconds
        
        /**
         * Constructor for SearchResult
         */
        public SearchResult(String searchMethod, Book foundBook, int comparisons, long executionTimeNanos) {
            this.searchMethod = searchMethod;
            this.foundBook = foundBook;
            this.comparisons = comparisons;
            this.executionTimeNanos = executionTimeNanos;
        }
        
        /**
         * Get formatted string representation of the result
         */
        @Override
        public String toString() {
            if (foundBook == null) {
                return String.format("%s: Book NOT FOUND\n" +
                                   "Comparisons: %d\n" +
                                   "Time: %.3f microseconds",
                                   searchMethod, comparisons, executionTimeNanos / 1000.0);
            } else {
                return String.format("%s: FOUND\n" +
                                   "Comparisons: %d\n" +
                                   "Time: %.3f microseconds\n" +
                                   "Book: %s",
                                   searchMethod, comparisons, executionTimeNanos / 1000.0, foundBook);
            }
        }
    }
}