@echo off
REM Batch file to compile and run the BST Library Search Optimization project

cd src

echo ========================================
echo Compiling Java files...
echo ========================================

javac models/Book.java
javac bst/Node.java
javac bst/BST.java
javac utils/SearchAnalyzer.java
javac gui/LibrarySearchGUI.java

echo.
echo ========================================
echo Compilation complete!
echo Starting the application...
echo ========================================
echo.

java gui.LibrarySearchGUI

pause
