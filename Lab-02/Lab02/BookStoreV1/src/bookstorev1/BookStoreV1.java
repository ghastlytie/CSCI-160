<<<<<<< HEAD
/**
 *                Revision History (newest first)
 *************************************************************
 * 8/12/2021  Rewritten to fix the data file formats to avoid the
 *            Scanner bug.
 * 8/11/2021  Rewritten for a better version of Book  AApplin.
 * 8/30/2019  implemented first version - Anne Applin
 */
package bookstorev1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is being written specifically to show several things. <br>
 * <ol>
 * <li>How to check for required command line arguments</li>
 * <li>How to catch exceptions</li>
 * <li>How to test each problem we are trying to prevent</li>
 * <li>How to set up run properties in NetBeans</li>
 * <li>How to use HTML in Java documentation</li>
 * </ol>
 *
 * @author aapplin
 */
public class BookStoreV1 {

    // properties of this class (everything in Java is a class!)
    /**
     * An array of Book objects representing every book in the store.
     */
    private Book[] inventory;
    /**
     * The actual number of unique books (same ISBN) .
     */
    private int numberInInventory;

    /**
     * Purpose: open, read and close the data file. Loads the data into an array
     * of book objects.
     *
     * @param fileName the name of the file to be read in.
     */
    public void readInventory(String fileName) {
        try { // try to connect to a physical file
            Scanner inFile = new Scanner(new FileReader(fileName));
            if (!inFile.hasNext()) {
                System.err.println("file " + fileName + " is empty.");
                System.exit(3);
            }
            inventory = new Book[200]; // create the array
            numberInInventory = 0; // initialize the property
            while (inFile.hasNext()) { // as long as ther is data in the file
                String isbn = inFile.next(); // read the ISBN
                // read title with embedded spaces
                String title = inFile.nextLine();
                //read author with embedded spaces
                String author = inFile.nextLine();
                int pages = inFile.nextInt();
                double price = inFile.nextDouble();
                int numberOnShelf = inFile.nextInt();

                if (isbn.length() == 13 && numberOnShelf >= 0) {
                    inventory[numberInInventory] = new Book(isbn, title,
                            author, pages, price,
                            numberOnShelf);
                    numberInInventory++;
                }
            }
            System.out.println(numberInInventory + " books loaded.");
            inFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            System.exit(2);
        } catch (InputMismatchException ex) {
            System.err.println("tried to read the wrong data type.");
            System.exit(4);
        }
    } // end readInventory()

    /**
     * linear search algorithm. It is always the same except for the second
     * condition with has to be tailored to the objects being compared, and the
     * if condition. BUT it is always while there are still places to look and
     * we haven't found what we are looking for, move forward. If we have run
     * out of places to look, set the index to -1 indicating that we didn't find
     * the item.
     *
     * @param isbnToFind the thing we are looking for. In this case, an ISBN
     * @return the index where the book was found or a -1 if it was not
     */
    public int linearSearch(String isbnToFind) {
        int index = 0;
        while (index < numberInInventory
                && inventory[index].getIsbn().compareTo(isbnToFind) != 0) {
            index++;
        }
        if (index == numberInInventory) {
            index = -1;
        }
        return index;
    }

    /**
     * This method reads a shipment file one book at a time. If the book is
     * already in the inventory then we update the number of copies in stock. If
     * it is a new book we add it to the next location in the array and
     * increment the count of books that we carry.
     *
     * @param fileName the name of the file that has the new books in it.
     */
    public void updateInventory(String fileName) {
        try { // try to connect to a physical file
            Scanner inFile = new Scanner(new FileReader(fileName));
            if (!inFile.hasNext()) {
                System.err.println("File " + fileName + " is empty.");
                System.exit(5);
            }
            int newBooks = 0;
            int booksUpdated = 0;
            while (inFile.hasNext()) {// as long as there is data in the file.
                String isbn = inFile.next(); // read the ISBN.
                String title = inFile.nextLine().trim();
                String author = inFile.nextLine().trim();
                int pages = inFile.nextInt();
                double price = inFile.nextDouble();
                int numberInShipment = inFile.nextInt();
                if (isbn.length() == 13 && numberInShipment >= 0) { // valid data
                    int index = linearSearch(isbn); // see if we carry this book
                    if (index != -1) { // we found it, update the quantity
                        inventory[index].addToNumberOnShelf(numberInShipment);
                        booksUpdated++;
                    } else { // add it
                        inventory[numberInInventory] = new Book(isbn, title,
                                author, pages, price,
                                numberInShipment);
                        numberInInventory++;
                        newBooks++;
                    }
                }
            }
            System.out.println(booksUpdated + " books updated");
            System.out.println(newBooks + " books added.");
            inFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            System.exit(6);
        } catch (InputMismatchException ex) {
            System.err.println("Tried to read the wrong data type.");
            System.exit(7);
        }
    }

    /**
     * writeInventory writes out the books in the inventory to a file in the
     * correct format to be read in.
     *
     * @param fileName The name of the file to be written to.
     */
    public void writeInventory(String fileName) {
        try {
            PrintStream out = new PrintStream(new File(fileName));
            for (int i = 0; i < numberInInventory; i++) {
                out.println(inventory[i]); // magically calls Book's toString()
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Directory seems to be write protected.");
            System.exit(8);
        }
    }

    /**
     * The run() method is where all the action is. This one should tell the
     * whole story of what the program is doing.
     *
     * @param filenames the 3 required filenames
     */
    public void run(String[] fileNames) {
        readInventory(fileNames[0]); // first file name
        if (numberInInventory > 0) {
            System.out.printf("Store inventory loaded, %d titles in "
                    + "the store.%n", numberInInventory);
            updateInventory(fileNames[1]); // second file name
            System.out.printf("After update, store has %d titles in inventory.%n",
                    numberInInventory);
            writeInventory(fileNames[2]); // third file name
            System.out.println("Inventory written out.");
        }
    }

    /**
     * main method. There can only be ONE main method that runs at any given
     * time. That is why the main method is static.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java BookStoreV1 input update output");
            System.exit(1);
        }
        BookStoreV1 driver = new BookStoreV1();
        driver.run(args);
    }

}
=======
/**
 *                Revision History (newest first)
 *************************************************************
 * 8/12/2021  Rewritten to fix the data file formats to avoid the
 *            Scanner bug.
 * 8/11/2021  Rewritten for a better version of Book  AApplin.
 * 8/30/2019  implemented first version - Anne Applin
 */
package bookstorev1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is being written specifically to show several things. <br>
 * <ol>
 * <li>How to check for required command line arguments</li>
 * <li>How to catch exceptions</li>
 * <li>How to test each problem we are trying to prevent</li>
 * <li>How to set up run properties in NetBeans</li>
 * <li>How to use HTML in Java documentation</li>
 * </ol>
 *
 * @author aapplin
 */
public class BookStoreV1 {
    // properties of this class (everything in Java is a class!)
    /**
     * An array of Book objects representing every book in the store.
     */
    private Book[] inventory;
    /**
     * The actual number of unique books (same ISBN) .
     */
    private int numberInInventory;

    /**
     * Purpose: open, read and close the data file. Loads the data into an array
     * of book objects.
     *
     * @param fileName the name of the file to be read in.
     */
    public void readInventory(String fileName) {
        try { // try to connect to a physical file
            Scanner inFile = new Scanner(new FileReader(fileName));
            if(!inFile.hasNext()) {
                System.err.println("file " + fileName + " is empty.");
                System.exit(3);
            }
            inventory = new Book[200]; // create the array
            numberInInventory = 0; // initialize the property
            while (inFile.hasNext()) { // as long as ther is data in the file
                String isbn = inFile.next(); // read the ISBN
                // read title with embedded spaces
                String title  = inFile.nextLine();
                //read author with embedded spaces
                String author = inFile.nextLine();
                int pages = inFile.nextInt();
                double price = inFile.nextDouble();
                int numberOnShelf = inFile.nextInt();
                
                if (isbn.length() == 13 && numberOnShelf >= 0) {
                    inventory[numberInInventory] = new Book(isbn, title,
                                                    author, pages, price,
                                                    numberOnShelf);
                    numberInInventory++;
                }
            }
            System.out.println(numberInInventory + " books loaded.");
            inFile.close();
        }
        catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            System.exit(2);
        }
        catch (InputMismatchException ex) {
            System.err.println("tried to read the wrong data type.");
            System.exit(4);
        }
    } // end readInventory()

    /**
     * linear search algorithm. It is always the same except for the second
     * condition with has to be tailored to the objects being compared, and the
     * if condition. BUT it is always while there are still places to look and
     * we haven't found what we are looking for, move forward. If we have run
     * out of places to look, set the index to -1 indicating that we didn't find
     * the item.
     *
     * @param isbnToFind the thing we are looking for. In this case, an ISBN
     * @return the index where the book was found or a -1 if it was not
     */
    public int linearSearch(String isbnToFind) {
        int index = 0;
        while (index < numberInInventory
                && inventory[index].getIsbn().compareTo(isbnToFind) != 0) {
            index++;
        }
        if (index == numberInInventory) {
            index = -1;
        }
        return index;
    }

    /**
     * This method reads a shipment file one book at a time. If the book is
     * already in the inventory then we update the number of copies in stock. If
     * it is a new book we add it to the next location in the array and
     * increment the count of books that we carry.
     *
     * @param fileName the name of the file that has the new books in it.
     */
    public void updateInventory(String fileName) {
        try { // try to connect to a physical file
            Scanner inFile = new Scanner(new FileReader(fileName));
            if (!inFile.hasNext()) {
                System.err.println("File " + fileName + " is empty.");
                System.exit(5);
            }
            int newBooks = 0;
            int booksUpdated = 0;
            while (inFile.hasNext()) {// as long as there is data in the file.
                String isbn = inFile.next(); // read the ISBN.
                String title = inFile.nextLine().trim();
                String author = inFile.nextLine().trim();
                int pages = inFile.nextInt();
                double price = inFile.nextDouble();
                int numberInShipment = inFile.nextInt();
                if (isbn.length() == 13 && numberInShipment >= 0) { // valid data
                    int index = linearSearch(isbn); // see if we carry this book
                    if (index != -1) { // we found it, update the quantity
                        inventory[index].addToNumberOnShelf(numberInShipment);
                        booksUpdated++;
                    } else { // add it
                        inventory[numberInInventory] = new Book(isbn, title,
                                        author, pages, price,
                                        numberInShipment);
                        numberInInventory++;
                        newBooks++;
                    }
                }
            }
            System.out.println(booksUpdated + " books updated");
            System.out.println(newBooks + " books added.");
            inFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            System.exit(6);
        } catch (InputMismatchException ex) {
            System.err.println("Tried to read the wrong data type.");
            System.exit(7);
        }
    }

    /**
     * writeInventory writes out the books in the inventory to a file in the
     * correct format to be read in.
     *
     * @param fileName The name of the file to be written to.
     */
    public void writeInventory(String fileName) {
        try {
            PrintStream out = new PrintStream(new File(fileName));
            for (int i = 0; i < numberInInventory; i++) {
                out.println(inventory[i]); // magically calls Book's toString()
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Directory seems to be write protected.");
            System.exit(8);
        }
    }

    /**
     * The run() method is where all the action is. This one should tell the
     * whole story of what the program is doing.
     *
     * @param filenames the 3 required filenames
     */
    public void run(String[] fileNames) {
        readInventory(fileNames[0]); // first file name
        if (numberInInventory > 0) {
            System.out.printf("Store inventory loaded, %d titles in "
                                + "the store.%n", numberInInventory);
            updateInventory(fileNames[1]); // second file name
            System.out.printf("After update, store has %d titles in inventory.%n",
                    numberInInventory);
            writeInventory(fileNames[2]); // third file name
            System.out.println("Inventory written out.");
        }
    }

    /**
     * main method. There can only be ONE main method that runs at any given
     * time. That is why the main method is static.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length != 3) {
            System.err.println("Usage: java BookStoreV1 input update output");
            System.exit(1);
        }
        BookStoreV1 driver = new BookStoreV1();
        driver.run(args);
    }

}
>>>>>>> 5cdb46566a6ff75efed0969b3fa81367073a165b
