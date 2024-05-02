/**
 * File Name: PortfolioManagement.java
 * Class: PortfolioManagement
 * Author: Severen D. Denyer
 ***********************************************************************
 * Revision History - newest revisions first
 ***********************************************************************
 * 03/16/2024 - sdenyer - created the project and class.
 *                      -
 *
 */
package portfoliomanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Description:
 *
 * @author sdenyer
 */
public class PortfolioManagement {

    private Portfolio portfolio;

    public void readFile(String fileName) {

        try {
            Scanner fileScan = new Scanner(new FileReader(fileName));
            if (!fileScan.hasNext()) {
                System.out.println("No data in file " + fileName);
                System.exit(0);
            }

            fileScan.useDelimiter("\s");

            while (fileScan.hasNext()) {
                String title = fileScan.nextLine();
                ArrayList<Double> readPrices = new ArrayList<>();
                while (fileScan.hasNextDouble()) {
                    readPrices.add(fileScan.nextDouble());
                }
                fileScan.nextLine();

                double[] pricesArray = new double[readPrices.size()];

                for (int i = 0; i < pricesArray.length; i++) {
                    pricesArray[i]
                            = Double.parseDouble(readPrices.get(i).toString());
                }

                portfolio.addStock(new Stock(title, pricesArray));
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            System.exit(2);
        } catch (InputMismatchException ex) {
            System.err.println("Tried to read the wrong data type");
            System.exit(4);
        }
    }

    public void run(String arg) {
        portfolio = new Portfolio();
        readFile(arg);
        System.out.println(portfolio.toString());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage Java inputFile");
            System.exit(1);
        }
        PortfolioManagement pm = new PortfolioManagement();
        pm.run(args[0]);
    }

}
