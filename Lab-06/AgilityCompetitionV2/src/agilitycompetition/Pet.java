/**
 * File Name: Pet.java
 * Class: Pet
 * Author: Severen D. Denyer
 *                   Revision History
 * ***********************************************************************
 * 02/21/2024 - sdenyer - created class
 *                      - followed lab instructions to create the pet
 *                        class that inherits from the dog class.
 *
 */
package agilitycompetition;

/**
 * A class that extends the Dog class. Adds name and owner fields.
 *
 * @author sdenyer
 */
public class Pet extends Dog {

    private final String name;
    private final String owner;

    /**
     * Parameterized Constructor for the Pet class.
     *
     * @param name the dog's name
     * @param owner the dog's owner's name
     */
    public Pet(Date dob, String breed, double weight, String name,
            String owner) {
        super(dob, breed, weight);
        this.name = name;
        this.owner = owner;
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
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file. This one is written in
     * the input file format.
     *
     * @return a formatted string representing the values of the attributes for
     * a dog object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s %-15s%-12s",
                super.toString(), name, owner));
        return str.toString();
    }

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented to
     * impose a natural ordering on a group of objects. compareTo is used by the
     * Collections.sort routine to allow us to sort the competitors belonging to
     * some Java collection. Conditions are checked in decreasing importance and
     * as soon as a criteria fits we are finished looking at the comparison.
     *
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Object that) {
        int comparison = 0;
        // shouldn't be any null objects, but if there are
        // put them after this one
        if (that == null) {
            comparison = 1;
            // if the addresses are the same... they are equal
        } else if (this == that) {
            comparison = 0;
        } else {
            /* We will sort by owners's name. Since owner is a String,
                    this uses the String's compareTo() method */
            comparison = this.owner.compareTo(((Pet) that).getOwner());
            /* if they have the same owner we will sort by dog's name within
               that. So all of the Pets that belong to a single owner will be
               together */
            if (comparison == 0) {
                comparison = this.name.compareTo(((Pet) that).getName());
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

        Pet dog1 = new Pet(date1, "Toy Poodle", 10.2, "Eudora", "Anne");
        Pet dog2 = new Pet(date2, "Bulldog", 20.4, "Bubba", "Jesse");
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("testing compareTo() ");
        System.out.println("dog1.compareTo(dog2) " + dog1.compareTo(dog2));
        System.out.println("dog1.compareTo(dog1) " + dog1.compareTo(dog1));
        System.out.println("dog2.compareTo(dog1) " + dog2.compareTo(dog1));
    }

}
