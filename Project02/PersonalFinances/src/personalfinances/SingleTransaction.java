/**
 * File Name: SingleTransaction.java
 * Class: SingleTransaction
 * Author: Severen D. Denyer
 *                   Revision History
 * ***********************************************************************
 * 02/12/2024 - sdenyer - created class
 *                      - added class fields
 *                      - created parameterized constructor
 *                      - created accessor methods
 *                      - created toString() methods
 */
package personalfinances;

/**
 * A class to hold a single transaction, access it's fields, and return a string
 * representation of the information.
 *
 * @author Severen
 */
public class SingleTransaction {

    private final int checkNum;
    private final Date date;
    private final String description;
    private final double amount;
    private final String type;
    private final String category;

    /**
     * A parameterized constructor of Single Transaction
     * @param checkNum an integer value of the check number
     * @param date a Date object
     * @param description a 
     * @param amount
     * @param type
     * @param category 
     */
    public SingleTransaction(int checkNum, Date date, String description,
            double amount, String type, String category) {
        this.checkNum = checkNum;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    /**
     * A toString method to return the fields as a string.
     * @return a formatted collection of the private fields
     */
    @Override
    public String toString() {
        return String.format("%7d %-10s %-30s %15.2f %-7s %s",
                checkNum, date.toString(), description,
                amount, type, category);
    }
}
