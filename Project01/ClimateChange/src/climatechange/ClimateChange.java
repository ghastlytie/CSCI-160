<<<<<<< HEAD
/**
 * File Name: ClimateChange.java<br>
 * Class: ClimateChange<br>
 * Author: Severen D. Denyer<br>
 ***********************************************************************<br>
 * Revision History - newest revisions first<br>
 ***********************************************************************<br>
 * 
 * 01/28/2024 - sdenyer - Created the firstFallFrost() method<br>
 * 01/27/2024 - sdenyer - Created the ClimateChange project.<br>
 *     - Created the run() method<br>
 *     - Created the findMaxIndex() method<br>
 *     - Created the findMinIndex() method<br>
 *     - Created the average() method<br>
 */
package climatechange;

//imports
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Description: This class reads a file containing the temperature data from<br>
 *  Portland International Jetport staring on 01/01/1941 to 12/31/2020.<br>
 * The data is read through to find the highest and lowest temperatures, the<br>
 * average of the highest and lowest temperatures, the starting index of eachM<br>
 * year, the first fall frost dates of each year, the average fall frost date<br>
 * by decade, and an analysis of the results.<br>
 * 
 * @author sdenyer<br>
 */
public class ClimateChange {
    
    /**
     * The findMaxIndex() method finds the index of the maximum value in an<br>
     * array passed to it.<br>
     * @param array an integer array<br>
     * @return maxIndex - an integer index value<br>
     */
    public int findMaxIndex(int[] array) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * The findMinIndex() method finds the index of the minimum value in an<br>
     * array passed to it.<br>
     * @param array integer array<br>
     * @return minIndex - an integer index value<br>
     */
    public int findMinIndex(int[] array) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    /**
     * The average() method calculates the average of the values in a given<br>
     * array.<br>
     * @param array an integer array<br>
     * @return avg - a double average of the values<br>
     */
    public double average(int[] array) {
        //vars
        int sum = 0;
        double avg;
        
        //adds all the data together
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        
        //computes, then returns the average value
        avg = (sum / (double)(array.length - 1));
        return avg;
    }
    
    /**
     * The firstFallFrost() method gets the index of the<br>
     * first fall frost of a given year.<br>
     * @param tmin the minimum temperature array<br>
     * @param yearStartIndex the index of the start of a given year<br>
     * @param yearEndIndex the index of the end of a given year<br>
     * @return firstFrostIndex the index of the first temperature under 32<br>
     * degrees F after summer.<br>
     */
    public int firstFallFrost(int[] tmin, int yearStartIndex, int yearEndIndex) {
        
        int midYearIndex = yearStartIndex + 180;
        int firstFrostIndex = midYearIndex;
        
        while(tmin[firstFrostIndex] > 32 && firstFrostIndex < yearEndIndex) {
            firstFrostIndex++;
        }
        
        return firstFrostIndex;
    }
    
    
    
    /**
     * The run() method executes the main program body.
     * @param args
     * @throws IOException 
     */
    public void run(String args) throws IOException {
        
        //vars
        Scanner scan = new Scanner(System.in);
        Scanner fileScan = null;
        FileInputStream fileStream = null;
        String fileName = "PortlandWeather1941to2020.txt";
        int dataRecordsCount;
        int maxTempIndex;
        int minTempIndex;
        int[] yearStartIndex;

        //create a scanner object for that file
        fileStream = new FileInputStream(fileName);
        fileScan = new Scanner(fileStream);

        //reads how many data records there are in the file
        dataRecordsCount = fileScan.nextInt();

        //creates the data arrays for month, day, year, tmax, and tmin
        //  tmax is the maximum temperature in Fahrenheit on that day
        //  tmin is the minimum temperature in Fahrenheit on that day
        int month[] = new int[dataRecordsCount];
        int day[] = new int[dataRecordsCount];
        int year[] = new int[dataRecordsCount];
        int tmax[] = new int[dataRecordsCount];
        int tmin[] = new int[dataRecordsCount];

        //moves the scanner down to the first data point
        for (int i = 0; i < 3; i++) {
            fileScan.nextLine();
        }

        //sets the scanner to break at whitespace, new lines,
        //  and forward slashes
        fileScan.useDelimiter("[/ \t\n\r]+");

        //assigns the file input data to the appropriate array
        for (int i = 0; i < dataRecordsCount; i++) {
            month[i] = fileScan.nextInt();
            day[i] = fileScan.nextInt();
            year[i] = fileScan.nextInt();
            tmax[i] = fileScan.nextInt();
            tmin[i] = fileScan.nextInt();
        }
        
        //gets the indexes of the highest and lowest recorded temps
        maxTempIndex = findMaxIndex(tmax);
        minTempIndex = findMinIndex(tmin);
        
        //outputs the average high and low of the recorded temps
        average(tmax);
        average(tmin);
        
        //outputs the highest temp and the date it occured
        System.out.printf("The highest temperature in the data is: "
                + "%d degrees F which occured on %d/%d/%d.\n",
                 tmax[maxTempIndex], month[maxTempIndex], day[maxTempIndex],
                year[maxTempIndex]);

        //outputs the lowest temp and the date it occured
        System.out.printf("The lowest temperature in the data is: "
                + "%d degrees F which occured on %d/%d/%d.\n",
                 tmin[minTempIndex], month[minTempIndex], day[minTempIndex],
                year[minTempIndex]);
                
        //outputs the average high temps
        System.out.printf("The average high temperature was %.2f degrees F.\n",
                average(tmax));
        
        //outputs the average low temps
        System.out.printf("The average low temperature was %.2f degrees F.\n",
                average(tmin));
        
        //gets and outputs the index of each new year's starting date
        yearStartIndex = new int[(year[year.length - 1] - year[0]) + 1];
        System.out.println("Number of years: " + yearStartIndex.length);
        System.out.printf("%-4s%17s", "Year", "Starting Index\n");
        for(int i = 0; i <= yearStartIndex.length - 1; i++) {
            yearStartIndex[i] = (int)(i * 365.25);
            System.out.printf("%-4d%16d\n", year[(int)(i * 365.25)], yearStartIndex[i]);
        }
        
        //outputs the list of first frost dates by year
        System.out.printf("\n%-4s%23s\n", "Year", "First Fall Frost");
        int[] fFFIndex = new int[yearStartIndex.length];
        for(int i = 0; i < yearStartIndex.length; i++) {
            int yearEndIndex = yearStartIndex[i] + 365;
            fFFIndex[i] = firstFallFrost(tmin, 
                    yearStartIndex[i], yearEndIndex);
            
            System.out.printf("%-15d%02d/%02d\n", year[yearStartIndex[i]], 
                    month[fFFIndex[i]], day[fFFIndex[i]]);
        }
    }
        
    /**
     * The main() method.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.err.println("Usage: java ClimateChange args");
            System.exit(1);
        }
        ClimateChange cc = new ClimateChange();
        cc.run(args[0]);
    }
}
=======
/**
 * File Name: ClimateChange.java<br>
 * Class: ClimateChange<br>
 * Author: Severen D. Denyer<br>
 ***********************************************************************<br>
 * Revision History - newest revisions first<br>
 ***********************************************************************<br>
 * 
 * 01/28/2024 - sdenyer - Created the firstFallFrost() method<br>
 * 01/27/2024 - sdenyer - Created the ClimateChange project.<br>
 *     - Created the run() method<br>
 *     - Created the findMaxIndex() method<br>
 *     - Created the findMinIndex() method<br>
 *     - Created the average() method<br>
 */
package climatechange;

//imports
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Description: This class reads a file containing the temperature data from<br>
 *  Portland International Jetport staring on 01/01/1941 to 12/31/2020.<br>
 * The data is read through to find the highest and lowest temperatures, the<br>
 * average of the highest and lowest temperatures, the starting index of eachM<br>
 * year, the first fall frost dates of each year, the average fall frost date<br>
 * by decade, and an analysis of the results.<br>
 * 
 * @author sdenyer<br>
 */
public class ClimateChange {
    
    /**
     * The findMaxIndex() method finds the index of the maximum value in an<br>
     * array passed to it.<br>
     * @param array an integer array<br>
     * @return maxIndex - an integer index value<br>
     */
    public int findMaxIndex(int[] array) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * The findMinIndex() method finds the index of the minimum value in an<br>
     * array passed to it.<br>
     * @param array integer array<br>
     * @return minIndex - an integer index value<br>
     */
    public int findMinIndex(int[] array) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    /**
     * The average() method calculates the average of the values in a given<br>
     * array.<br>
     * @param array an integer array<br>
     * @return avg - a double average of the values<br>
     */
    public double average(int[] array) {
        //vars
        int sum = 0;
        double avg;
        
        //adds all the data together
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        
        //computes, then returns the average value
        avg = (sum / (double)(array.length - 1));
        return avg;
    }
    
    /**
     * The firstFallFrost() method gets the index of the<br>
     * first fall frost of a given year.<br>
     * @param tmin the minimum temperature array<br>
     * @param yearStartIndex the index of the start of a given year<br>
     * @param yearEndIndex the index of the end of a given year<br>
     * @return firstFrostIndex the index of the first temperature under 32<br>
     * degrees F after summer.<br>
     */
    public int firstFallFrost(int[] tmin, int yearStartIndex, int yearEndIndex) {
        
        int midYearIndex = yearStartIndex + 180;
        int firstFrostIndex = midYearIndex;
        
        while(tmin[firstFrostIndex] > 32 && firstFrostIndex < yearEndIndex) {
            firstFrostIndex++;
        }
        
        return firstFrostIndex;
    }
    
    
    
    /**
     * The run() method executes the main program body.
     * @param args
     * @throws IOException 
     */
    public void run(String args) throws IOException {
        
        //vars
        Scanner scan = new Scanner(System.in);
        Scanner fileScan = null;
        FileInputStream fileStream = null;
        String fileName = "PortlandWeather1941to2020.txt";
        int dataRecordsCount;
        int maxTempIndex;
        int minTempIndex;
        int[] yearStartIndex;

        //create a scanner object for that file
        fileStream = new FileInputStream(fileName);
        fileScan = new Scanner(fileStream);

        //reads how many data records there are in the file
        dataRecordsCount = fileScan.nextInt();

        //creates the data arrays for month, day, year, tmax, and tmin
        //  tmax is the maximum temperature in Fahrenheit on that day
        //  tmin is the minimum temperature in Fahrenheit on that day
        int month[] = new int[dataRecordsCount];
        int day[] = new int[dataRecordsCount];
        int year[] = new int[dataRecordsCount];
        int tmax[] = new int[dataRecordsCount];
        int tmin[] = new int[dataRecordsCount];

        //moves the scanner down to the first data point
        for (int i = 0; i < 3; i++) {
            fileScan.nextLine();
        }

        //sets the scanner to break at whitespace, new lines,
        //  and forward slashes
        fileScan.useDelimiter("[/ \t\n\r]+");

        //assigns the file input data to the appropriate array
        for (int i = 0; i < dataRecordsCount; i++) {
            month[i] = fileScan.nextInt();
            day[i] = fileScan.nextInt();
            year[i] = fileScan.nextInt();
            tmax[i] = fileScan.nextInt();
            tmin[i] = fileScan.nextInt();
        }
        
        //gets the indexes of the highest and lowest recorded temps
        maxTempIndex = findMaxIndex(tmax);
        minTempIndex = findMinIndex(tmin);
        
        //outputs the average high and low of the recorded temps
        average(tmax);
        average(tmin);
        
        //outputs the highest temp and the date it occured
        System.out.printf("The highest temperature in the data is: "
                + "%d degrees F which occured on %d/%d/%d.\n",
                 tmax[maxTempIndex], month[maxTempIndex], day[maxTempIndex],
                year[maxTempIndex]);

        //outputs the lowest temp and the date it occured
        System.out.printf("The lowest temperature in the data is: "
                + "%d degrees F which occured on %d/%d/%d.\n",
                 tmin[minTempIndex], month[minTempIndex], day[minTempIndex],
                year[minTempIndex]);
                
        //outputs the average high temps
        System.out.printf("The average high temperature was %.2f degrees F.\n",
                average(tmax));
        
        //outputs the average low temps
        System.out.printf("The average low temperature was %.2f degrees F.\n",
                average(tmin));
        
        //gets and outputs the index of each new year's starting date
        yearStartIndex = new int[(year[year.length - 1] - year[0]) + 1];
        System.out.println("Number of years: " + yearStartIndex.length);
        System.out.printf("%-4s%17s", "Year", "Starting Index\n");
        for(int i = 0; i <= yearStartIndex.length - 1; i++) {
            yearStartIndex[i] = (int)(i * 365.25);
            System.out.printf("%-4d%16d\n", year[(int)(i * 365.25)], yearStartIndex[i]);
        }
        
        //outputs the list of first frost dates by year
        System.out.printf("\n%-4s%23s\n", "Year", "First Fall Frost");
        int[] fFFIndex = new int[yearStartIndex.length];
        for(int i = 0; i < yearStartIndex.length; i++) {
            int yearEndIndex = yearStartIndex[i] + 365;
            fFFIndex[i] = firstFallFrost(tmin, 
                    yearStartIndex[i], yearEndIndex);
            
            System.out.printf("%-15d%02d/%02d\n", year[yearStartIndex[i]], 
                    month[fFFIndex[i]], day[fFFIndex[i]]);
        }
    }
        
    /**
     * The main() method.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.err.println("Usage: java ClimateChange args");
            System.exit(1);
        }
        ClimateChange cc = new ClimateChange();
        cc.run(args[0]);
    }
}
>>>>>>> 5cdb46566a6ff75efed0969b3fa81367073a165b
