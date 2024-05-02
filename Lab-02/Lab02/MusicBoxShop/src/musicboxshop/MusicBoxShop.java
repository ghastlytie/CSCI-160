/*
 *       Revision History newest first 
 * 01/30/2024      what you did                                      Severen Denyer
 ****************************************************************************
 * 
 */
package musicboxshop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The driver for the application.
 *
 * @author aapplin
 */
public class MusicBoxShop {

    private Orders orders = new Orders();

    public Mechanism getMechanism(Scanner in) {
        String composition = in.next();
        int movement = 0;
        try {
            movement = in.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("Tried to read the wrong data type.");
            System.exit(4);
        }
        return new Mechanism(composition, movement);
    }

    public MusicBox getMusicBox(Scanner in) {
        Mechanism m = getMechanism(in);
        int orderNumber = 0;
        int style = 0;
        int wood = 0;
        try {
            orderNumber = in.nextInt();
            style = in.nextInt();
            wood = in.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("Tried to read the wrong data type.");
            System.exit(5);
        }
        return new MusicBox(orderNumber, m, style, wood);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

}
