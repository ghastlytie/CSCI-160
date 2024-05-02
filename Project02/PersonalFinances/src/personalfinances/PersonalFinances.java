<<<<<<< HEAD
/**
 * File Name: PersonalFinances.java<br>
 * Class: PersonalFinances<br>
 * Author: Severen D. Denyer<br>
 * Revision History<br>
 * ***********************************************************************<br>
 * 02/12/2024 - sdenyer - created the PersonalFinances project<br>
 * - added Date.java to the PersonalFinances project<br>
 * 02/14/2024 - sdenyer - created run method<br>
 * 02/18/2024 - sdenyer - created the readFile() method<br>
 * - created the sortByCategory() method<br>
 * - created the swap() method<br>
 * - created the printSortByCategory() method<br>
 * - created the sortByCheckNumber() method<br>
 * - created the printRegister() method<br>
 *
 */
package personalfinances;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class designed to form an array of transaction objects and add the data<br>
 * from a given file to it to form a reciept of transactions.<br>
 * <br>
 *
 * @author sdenyer<br>
 */
public class PersonalFinances {

    Transaction[] transactionArray;
    int recordsRead = 0;

    /**
     * Reads the data from a given file into an array of transactionArray.<br>
     * <br>
     *
     * @param fileName the name of the file being read.<br>
     */
    public void readFile(String fileName) {
        try {
            Scanner inFile = new Scanner(new FileReader(fileName));
            if (!inFile.hasNext()) {
                System.err.println("file " + fileName + " is empty.");
                System.exit(3);
            }
            transactionArray = new Transaction[250];
            inFile.useDelimiter("[/, \t\n\r]+");

            String[] columnHeadings = new String[6];
            for (int i = 0; i < columnHeadings.length; i++) {
                columnHeadings[i] = inFile.next();
            }

            while (inFile.hasNext()) {

                int month = inFile.nextInt();
                int day = inFile.nextInt();
                int year = inFile.nextInt();

                Date date = new Date(month, day, year);

                String description = inFile.next().replaceAll("_", " ");
                double amount = inFile.nextDouble();
                String type = inFile.next().replaceAll("_", " ");
                String category = inFile.next().replaceAll("_", " ");

                transactionArray[recordsRead] = new Transaction(recordsRead,
                        date, description, amount, type, category);
                recordsRead++;
            }
            inFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            System.exit(2);
        } catch (InputMismatchException ex) {
            System.err.println("tried to read the wrong data type");
            System.exit(4);
        }
    }

    /**
     * Sorts the transaction array by category.<br>
     */
    public void sortByCategory() {
        int i, j, iMin;

        for (j = 0; j < recordsRead; j++) {
            iMin = j;

            for (i = j; i < recordsRead; i++) {
                if (transactionArray[i].getCategory().compareTo(
                        transactionArray[iMin].getCategory()) < 0) {
                    iMin = i;
                }
            }
            if (!(transactionArray[iMin].getCategory().equals(
                    transactionArray[j].getCategory()))) {
                swap(j, iMin);
            }
        }
    }

    /**
     * Sorts the transactions array by check number.<br>
     */
    public void sortByCheckNumber() {
        int i, j, iMin;

        for (j = 0; j < recordsRead; j++) {
            iMin = j;

            for (i = j; i < recordsRead; i++) {
                if (transactionArray[i].getCheckNum()
                        < transactionArray[iMin].getCheckNum()) {
                    iMin = i;
                }
            }
            if (!(transactionArray[iMin].getCheckNum()
                    == transactionArray[j].getCheckNum())) {
                swap(j, iMin);
            }
        }
    }

    /**
     * Switches the position of two values within an array.<br>
     * <br>
     *
     * @param i the index of one of the items to be swapped.<br>
     * @param j the index of the other item to be swapped.<br>
     */
    public void swap(int i, int j) {
        Transaction temp = transactionArray[i];
        transactionArray[i] = transactionArray[j];
        transactionArray[j] = temp;
    }

    /**
     * Creates a formatted string of all transactions by category.<br>
     * <br>
     *
     * @return a formatted string of transactions by category.<br>
     */
    public String printCategorySubTotals() {
        sortByCategory();
        StringBuilder str = new StringBuilder();
        double total = 0.0;
        double debitTotal = 0.0;
        double creditTotal = 0.0;

        str.append(String.format("%7s %-10s %-30s %15s\n",
                "Check#", "Date", "Description", "Amount"));

        for (int i = 0; i < recordsRead - 1; i++) {

            if (transactionArray[i].getCategory().equals(
                    transactionArray[i + 1].getCategory())) {
                total += transactionArray[i].getAmount();
                str.append(String.format("%-87s\n", transactionArray[i]));
            } else {
                str.append(String.format("%-87s\n", transactionArray[i]));
                total += transactionArray[i].getAmount();
                str.append(String.format(
                        "Subtotal for category %-13s%,30.2f\n\n",
                        transactionArray[i].getCategory(),
                        total));
                total = 0.0;
            }

            if (transactionArray[i].getType().equals("debit")) {
                debitTotal += transactionArray[i].getAmount();
            } else if (transactionArray[i].getType().equals("credit")) {
                creditTotal += transactionArray[i].getAmount();
            }
        }
        str.append(String.format(
                "Total Debits:%,15.2f\tTotalCredits:%,15.2f",
                debitTotal, creditTotal));
        return str.toString();
    }

    /**
     * Creates a formatted string of all transactions and a balance <br>
     * by check number.<br>
     * <br>
     *
     * @param balance the balance argument passed from the command line<br>
     * @return a formatted string of transactions by check number.<br>
     */
    public String printRegister(double balance) {
        sortByCheckNumber();
        StringBuilder str = new StringBuilder();
        double savings = 0.0;
        double balanceStart = balance;

        str.append(String.format("\n%7s %-10s %-30s %15s %-7s %-13s %8s\n",
                "Check#", "Date", "Description", "Amount", "Type",
                "Category", "Balance Forward"));

        str.append(String.format("%,102.2f\n", balance));

        for (int i = 0; i < recordsRead; i++) {

            if (transactionArray[i].getType().equals("debit")) {
                balance -= transactionArray[i].getAmount();
            } else if (transactionArray[i].getType().equals("credit")) {
                double savingsPercent = transactionArray[i].getAmount() * 0.1;
                savings += savingsPercent;
                balance += transactionArray[i].getAmount() - savingsPercent;

            }
            str.append(String.format("%-87s%,15.2f\n",
                    transactionArray[i], balance));
        }
        if (balance > balanceStart) {
            savings += balance - balanceStart;
            balance -= balance - balanceStart;
        }
        str.append(String.format("Savings Balance\tAdj Checking Balance\n"
                + "%,12.2f%,27.2f", savings, balance));
        return str.toString();
    }

    /**
     * The run method reads the file passed from the arguments array. Prints<br>
     * the string returned from printCategorySubTotals(), then prints<br>
     * the string returned from printRegister() after passing it the balance<br>
     * from args.<br>
     * <br>
     *
     * @param args command line arguments passed from main<br>
     */
    public void run(String[] args) {
        readFile(args[0]);
        System.out.println(printCategorySubTotals());
        System.out.println(printRegister(Double.parseDouble(args[1])));
    }

    /**
     * The program driver<br>
     * <br>
     *
     * @param args the command line arguments<br>
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage prog inputFile balance");
            System.exit(1);
        }
        PersonalFinances pf = new PersonalFinances();
        pf.run(args);
    }
}
=======
/**
 * File Name: PersonalFinances.java
 * Class: PersonalFinances
 * Author: Severen D. Denyer
 *                   Revision History
 * ***********************************************************************
 * 02/12/2024 - sdenyer - created the PersonalFinances project
 *                      - added Date.java to the PersonalFinances project
 * 02/14/2024 - sdenyer - created run method
 *                      -  
 */
package personalfinances;

/**
 * 
 * @author sdenyer
 */
public class PersonalFinances {
    
    
    
    
    
    /**
     * 
     * @param args 
     */
    public void run(String[] args) {
        
    }
    
    
        
    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length < 1) {
            System.err.println("Usage prog inputFile balance");
            System.exit(1);
        }
        PersonalFinances pf = new PersonalFinances();
        pf.run(args);
        
        
    }
    
}
>>>>>>> 5cdb46566a6ff75efed0969b3fa81367073a165b
