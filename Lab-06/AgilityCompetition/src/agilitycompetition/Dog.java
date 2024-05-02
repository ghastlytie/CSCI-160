/**
 * Revision History
 *******************************************************************
 * 09.20.2021 fixed the compareTo()
 * 02.01.2021  Updated the lab
 * 2014  cleaned up and JavaDoc added
 * 2013  rewritten in Java
 * 2005  original version written in C++
 */
package agilitycompetition;

/**
 * Course: CSCI 160
 * File: Dog
 * Uses: Date class
 * Extends: nothing
 * Implements: Comparable
 * Presumption: All dogs have these attributes
 * A class to represent a Dog competing in an Agility Contest.
 * Version 01.02
 * @author aapplin
 */
public class Dog implements Comparable {

    private final Date birthDate;
    private final String breed;
    private final double weight;
    private final String name;
    private final String owner;
    private final ElapsedTime time;

    /**
     * Parameterized Constructor for the Dog class.
     *
     * @param dob the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     * @param name the dog's name
     * @param owner the dog's owner's name
     * @param time the dog's elapsed time on the course (ElapsedTime)
     */
    public Dog(Date dob, String breed, double weight, String name,
            String owner, ElapsedTime time) {
        this.birthDate = dob;
        this.breed = breed;
        this.weight = weight;
        this.name = name;
        this.owner = owner;
        this.time = time;
    }

    /**
     * Accessor for birthdate
     *
     * @return the current value of birthdate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @return
     */
    public String getBreed() {
        return breed;
    }

    /**
     *
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Accessor for the name attribute
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the owner attribute
     *
     * @return the value of owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Accessor for the course time
     *
     * @return the value of course time
     */
    public ElapsedTime getTime() {
        return time;
    }

    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file. This one is 
     * written in the input file format.
     *
     * @return a formatted string representing the values of the attributes for
     * a dog object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator(); // end of line for this OS
        str.append(String.format("%4d %4d %4d %-25s", 
                birthDate.getDay(), birthDate.getMonth(), 
                birthDate.getYear(), breed));
        str.append(eol);
        str.append(String.format("%7.2f  %-15s%-12s %d %d %d %d", 
                weight, name, owner, time.getHours(),
                time.getMinutes(), time.getSeconds(),
                time.getMilliseconds()));
        return str.toString();
    }

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented 
     * to impose a natural ordering on a group of objects. compareTo is used 
     * by the Collections.sort routine to allow us to sort the competitors 
     * belonging to some Java collection. Conditions are checked in
     * decreasing importance and as soon as a criteria fits we are 
     * finished looking at the comparison.
     *
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Object that) {
        int comparison  = 0;        
        // shouldn't be any null objects, but if there are
        // put them after this one
        if (that == null){
            comparison = 1;            
            // if the addresses are the same... they are equal
        } else if (this == that) {
            comparison = 0;
        } else { // compare elapsed times
            comparison = this.time.compareTo(((Dog)that).time);
            if (comparison == 0) { // times are the same, compare names
                comparison = this.getName().compareTo(((Dog)that).getName());
            }
        }
        return comparison;
    }

    /**
     * Unit Test for the dog class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tests Dog class only. to run, right-click 
        // and choose Run File
        Date date1 = new Date(7, 26, 2006);
        Date date2 = new Date(10, 17, 2017);

        Dog dog1 = new Dog(date1, "Toy Poodle", 10.2, "Eudora",
                "Anne", new ElapsedTime(0, 2, 35, 40));
        Dog dog2 = new Dog(date2, "Bulldog", 20.4, "Bubba",
                "Jesse", new ElapsedTime(0, 5, 24, 50));
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("testing compareTo() ");
        System.out.println("dog1.compareTo(dog2) " + dog1.compareTo(dog2));
        System.out.println("dog1.compareTo(dog1) " + dog1.compareTo(dog1));
        System.out.println("dog2.compareTo(dog1) " + dog2.compareTo(dog1));
    }

}
