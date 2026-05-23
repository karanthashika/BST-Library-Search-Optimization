# BST Library Search Optimization - Setup & Run Guide

## Project Overview
Binary Search Tree based Library Search Optimization - A beginner-friendly Java Swing GUI application for managing a library system with optimized search operations.

## Prerequisites
1. **Java Development Kit (JDK)** - Version 11 or higher
2. **Visual Studio Code** with Java extensions
3. **Extension Pack for Java** (includes Debugger for Java, Test Runner for Java, Visual Studio IntelliCode)

## Folder Structure
```
BST-Library-Search-Optimization/
├── src/
│   ├── models/          (Data Models)
│   │   └── Book.java
│   ├── bst/            (Binary Search Tree Implementation)
│   │   ├── Node.java
│   │   └── BST.java
│   ├── gui/            (GUI Components)
│   │   └── LibrarySearchGUI.java
│   ├── utils/          (Utility Classes)
│   │   └── SearchAnalyzer.java
│   └── lib/            (External libraries - if needed)
├── README.md           (This file)
└── compile_and_run.bat (Batch file for Windows)
```

## How to Run the Project

### Method 1: Using VS Code Terminal (Recommended for Beginners)

#### Step 1: Open the Project in VS Code
```bash
1. Open VS Code
2. File → Open Folder
3. Select: c:\Users\karan\OneDrive\BST-Library-Search-Optimization
4. Click "Select Folder"
```

#### Step 2: Compile the Java Files
```bash
1. Click Terminal → New Terminal (or Ctrl + `)
2. Run the following commands:

# Navigate to the src folder
cd src

# Compile all Java files
javac -d . models/Book.java
javac -d . bst/Node.java
javac -d . bst/BST.java
javac -d . utils/SearchAnalyzer.java
javac -d . gui/LibrarySearchGUI.java
```

After compilation, you'll see these new folders created:
- `models/` (with Book.class)
- `bst/` (with Node.class, BST.class)
- `gui/` (with LibrarySearchGUI.class)
- `utils/` (with SearchAnalyzer.class, SearchResult.class)

#### Step 3: Run the Application
```bash
# Make sure you're in the src folder
# Run the main class
java gui.LibrarySearchGUI
```

The GUI window will appear!

---

### Method 2: Using Command Prompt

```bash
# Navigate to project folder
cd c:\Users\karan\OneDrive\BST-Library-Search-Optimization\src

# Compile
javac models/Book.java
javac bst/Node.java
javac bst/BST.java
javac utils/SearchAnalyzer.java
javac gui/LibrarySearchGUI.java

# Run
java gui.LibrarySearchGUI
```

---

### Method 3: Create a Batch File (For Easy Running)

Create a file named `compile_and_run.bat` in the project root:

```batch
@echo off
cd src
echo Compiling Java files...
javac models/Book.java
javac bst/Node.java
javac bst/BST.java
javac utils/SearchAnalyzer.java
javac gui/LibrarySearchGUI.java
echo.
echo Running the application...
java gui.LibrarySearchGUI
pause
```

Then double-click `compile_and_run.bat` to compile and run.

---

## Features & How to Use

### 1. **Add Book**
- Enter Book ID (number)
- Enter Book Name
- Enter Author
- Enter Category
- Click "Add Book"

### 2. **Display All Books**
- Click "Display All Books"
- Shows all books in sorted order (by ID)
- Uses inorder traversal of BST

### 3. **Search Book**
- Enter Book ID in search field
- Click "Search"
- Shows book details if found

### 4. **Delete Book**
- Enter Book ID
- Click "Delete"
- Book is removed from library

### 5. **Update Book**
- Enter Book ID
- Update Name, Author, Category
- Click "Update Book"

### 6. **Compare Search Methods**
- Add multiple books first
- Enter a Book ID to search
- Click "Compare (Linear vs BST)"
- See performance comparison

---

## File Descriptions

### 1. **Book.java** (models/)
- **Purpose**: Data model for a book
- **Attributes**: bookID, bookName, author, category
- **Methods**: Getters, setters, compareTo(), toString()

### 2. **Node.java** (bst/)
- **Purpose**: Represents a node in the BST
- **Attributes**: book (data), left (left child), right (right child)
- **Size**: ~20 lines

### 3. **BST.java** (bst/)
- **Purpose**: Binary Search Tree implementation
- **Key Methods**:
  - `addBook()` - O(log n) average
  - `searchBook()` - O(log n) average
  - `deleteBook()` - O(log n) average
  - `updateBook()` - O(log n) average
  - `getAllBooksInOrder()` - O(n), inorder traversal
  - `getTotalBooks()` - Count all nodes

### 4. **SearchAnalyzer.java** (utils/)
- **Purpose**: Compare Linear Search vs BST Search
- **Methods**:
  - `linearSearch()` - O(n) time
  - `bstSearch()` - O(log n) time
- **Output**: Comparisons, time, speedup factor

### 5. **LibrarySearchGUI.java** (gui/)
- **Purpose**: Main GUI application
- **Components**:
  - Input Panel (Add/Update books)
  - Search Panel (Search/Delete/Compare)
  - Display Panel (Show results)
- **Layout**: BorderLayout with nested panels

---

## Example Usage Walkthrough

### Adding Books:
1. Book ID: 101, Name: "Java Programming", Author: "James Gosling", Category: "Technology"
2. Book ID: 205, Name: "Data Structures", Author: "Mark Allen Weiss", Category: "Technology"
3. Book ID: 103, Name: "The Hobbit", Author: "J.R.R. Tolkien", Category: "Fantasy"

### Searching:
- Click "Display All Books" → See all 3 books in sorted order (101, 103, 205)
- Search for ID 205 → Found instantly
- Enter 205 in Compare and see BST outperforms linear search

---

## Time Complexity Summary

| Operation | Linear Search | BST |
|-----------|---------------|-----|
| Add | O(1) | O(log n) avg |
| Search | O(n) | O(log n) avg |
| Delete | O(n) | O(log n) avg |
| Display All | O(n) | O(n) |

---

## Troubleshooting

### Issue: "Cannot find symbol: class Book"
**Solution**: Make sure all files are in correct packages (models, bst, gui, utils)

### Issue: "main" method is not found
**Solution**: Run `java gui.LibrarySearchGUI` (not just `java LibrarySearchGUI`)

### Issue: GUI window doesn't appear
**Solution**: Check terminal for errors. Make sure JDK is installed correctly.

### Issue: Class not found after compilation
**Solution**: Make sure you're running from the `src` folder and compiled with `-d .` option

---

## How to Explain in Viva

### Key Points:
1. **BST Advantage**: O(log n) search vs O(n) linear search
2. **Inorder Traversal**: Gives sorted output
3. **Deletion**: Three cases - leaf, one child, two children
4. **GUI**: Simple swing components - JPanel, JButton, JTextArea
5. **Time Complexity**: Explain with an example

### Example Answer:
*"In this project, I've implemented a Binary Search Tree to store books sorted by ID. When searching for a book, BST takes O(log n) time compared to O(n) for linear search. The GUI uses Java Swing with panels for input, search, and output. The inorder traversal displays books in sorted order. The SearchAnalyzer compares both methods to show the performance advantage of BST."*

---

## Key Concepts for Viva

1. **Binary Search Tree**: Self-balancing tree with left < parent < right
2. **Inorder Traversal**: Left → Root → Right (gives sorted output)
3. **Complexity Analysis**: 
   - Best: O(log n) when tree is balanced
   - Worst: O(n) when tree becomes linear
4. **Swing Components**: JFrame, JPanel, JButton, JTextArea
5. **Package Structure**: Organizing code for maintainability

---

## Additional Notes

- The project uses simple Java Swing for GUI (no advanced frameworks)
- All code is well-commented for learning
- Each class has a single responsibility
- Easy to extend with more features (like persistence, user authentication)

---

Good luck with your viva! 🎓
