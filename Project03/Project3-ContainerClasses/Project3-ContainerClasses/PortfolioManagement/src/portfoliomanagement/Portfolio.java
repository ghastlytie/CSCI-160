/**
 * File Name: Portfolio.java
 * Class: Portfolio
 * Author: Severen D. Denyer
 ***********************************************************************
 * Revision History - newest revisions first
 ***********************************************************************
 *
 *
 */
package portfoliomanagement;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author sdenyer
 */
public class Portfolio {

    private ArrayList<Stock> stockArray = new ArrayList<Stock>();

    public Portfolio() {

    }

    public void addStock(Stock newStock) {
        stockArray.add(newStock);
    }

    public Stock getStockAt(int index) {
        double[] a = {0.0, 1.0};
        Stock placeHolder = new Stock("a", a);
        return placeHolder;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append(String.format("%-20s%10s%10s%10s%10s%10s%10s", "Company",
                "Low", "Hi", "Net", "Ave", "Dev", "Lng"));

        for (int i = 0; i < stockArray.size(); i++) {
            str.append("\n");
            str.append(stockArray.get(i));
        }

        return str.toString();
    }

    /**
     * Unit test for Portfolio class expected output is below
     *
     * @param args
     */
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        double[] quotes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        String name = "A";
        portfolio.addStock(new Stock(name, quotes));
        name = "B";
        double[] quotes2 = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
        portfolio.addStock(new Stock(name, quotes2));
        System.out.println(portfolio);
        System.out.println("Expected Output without EC");
        System.out.println("Company                Low    Hi    Net    Ave    Dev  Lng  BestTrRt");
        System.out.println("A                     10.0 100.0   90.0   55.0  28.72    9     10.00");
        System.out.println("B                     10.0 100.0  -90.0   55.0  28.72    0       n/a");
    }
}
