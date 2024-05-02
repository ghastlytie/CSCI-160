/**
 * File Name: Transaction.java<br>
 * Class: Transaction<br>
 * Author: Severen D. Denyer<br>
 *                   Revision History<br>
 * ***********************************************************************<br>
 * 02/12/2024 - sdenyer - created class<br>
 *                      - added class fields<br>
 *                      - created parameterized constructor<br>
 *                      - created accessor methods<br>
 *                      - created toString() methods<br>
 */
package personalfinances;

/**
 * A class to hold a single transaction, access it's fields, and return <br>
 * a string representation of the information.<br>
 *<br>
 * @author sdenyer<br>
 */
public class Transaction {

    private final int checkNum;
    private final Date date;
    private final String description;
    private final double amount;
    private final String type;
    private final String category;

    /**
     * A parameterized constructor of Single Transaction.<br>
     * <br>
     * @param checkNum an integer value of the check number<br>
     * @param date the transaction date<br>
     * @param description the transaction description<br>
     * @param amount the transaction amount<br>
     * @param type the transaction type<br>
     * @param category the transaction category<br>
     */
    public Transaction(int checkNum, Date date, String description,
            double amount, String type, String category) {
        this.checkNum = checkNum;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
    }

    /**
     * accessor method for checkNum<br>
     * <br>
     * @return the check number<br>
     */
    public int getCheckNum() {
        return checkNum;
    }
    
    /**
     * accessor method for date<br>
     * <br>
     * @return the transaction date<br>
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * accessor method for description<br>
     * <br>
     * @return the transaction description<br>
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * accessor method for amount<br>
     * <br>
     * @return the transaction amount<br>
     */
    public double getAmount() {
        return amount;
    }

    /**
     * accessor method for type<br>
     * <br>
     * @return the transaction type<br>
     */
    public String getType() {
        return type;
    }

    /**
     * accessor method for category<br>
     * <br>
     * @return the transaction category<br>
     */
    public String getCategory() {
        return category;
    }

    /**
     * A toString method to return the fields as a string.<br>
     * <br>
     * @return a formatted collection of the private fields<br>
     */
    public String toString() {
        return String.format("%7d %-10s %-30s %,15.2f %-7s %s",
                checkNum, date.toString(), description,
                amount, type, category);
    }
}
