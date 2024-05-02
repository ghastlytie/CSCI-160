/**
 * File Name: Stock.java
 * Class: Stock
 * Author: Severen D. Denyer
 ***********************************************************************
 * Revision History - newest revisions first
 ***********************************************************************
 * 03/16/2024 - sdenyer - created the Stock class.
 *
 */
package portfoliomanagement;

/**
 * Description:
 *
 * @author sdenyer
 */
public class Stock {

    private String companyName = null;
    private double[] prices = null;
    private double minimum;
    private double maximum;
    private double netChange;
    private double averagePrice;
    private double standardDeviation;
    private int longestUpwardTrend = 0;

    public Stock(String companyName, double[] prices) {
        this.companyName = companyName;
        this.prices = prices;
        calcMinMaxNetAndAverage();
        calcStdDev();
        calcLUT();
    }

    /**
     * 
     */
    private void calcMinMaxNetAndAverage() {
        double sum = 0.0;

        minimum = Double.MAX_VALUE;
        maximum = Double.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minimum) {
                minimum = prices[i];
            }

            if (prices[i] > maximum) {
                maximum = prices[i];
            }

            netChange = Math.abs(prices[0] - prices[prices.length - 1]);

            sum += prices[i];
        }

        averagePrice = sum / prices.length;
    }

    /**
     * 
     */
    private void calcStdDev() {
        double diffSqrSum = 0;

        for (int i = 0; i < prices.length; i++) {
            diffSqrSum += Math.pow((prices[i] - averagePrice), 2);
        }

        standardDeviation = Math.sqrt(diffSqrSum / prices.length);
    }
    
    /**
     * 
     */
    private void calcLUT() {
        int trendCount = 0;
        int k = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                k = trendCount;
                trendCount = 0;
            } else {
                trendCount++;
            }

            if (k > longestUpwardTrend) {
                longestUpwardTrend = k;
            }

            if (trendCount > longestUpwardTrend) {
                longestUpwardTrend = trendCount;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%-20s%10.2f%10.2f%10.2f%10.2f%10.2f%10d",
                companyName, minimum, maximum, netChange, averagePrice, 
                standardDeviation, longestUpwardTrend));

        return str.toString();
    }

    public static void main(String[] args) {
        double[] quotes = {114.5, 120.6, 130.2, 128.1, 126.7, 129.3};
        String name = "Apple Inc";
        Stock s = new Stock(name, quotes);
        System.out.println(s);
        System.out.println("Expected Output without EC");
        System.out.println("Apple Inc.           114.5 130.2   14.8  "
                + "124.9   5.59   2     7.85");
    }
}
