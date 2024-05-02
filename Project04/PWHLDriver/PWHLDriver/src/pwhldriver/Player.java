/** *********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 * 04/07/2024/ - Severen Denyer -
 * 12/2020  - Anne Applin - added static array.
 * 08/04/2016 - Anne Applin - formatting only
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package pwhldriver;

/**
 * The super class for the goalie and skater classes that unifies them. It
 * implements the Comparable interface so that a Team's players can be sorted by
 * playerNumber in the Map. You must
 * <ol>
 * <li> define data members for the common data(always make data members for a
 * super class protected)</li>
 * <li> define a constructor that initializes all data members</li>
 * <li> define getters for the data members that you need to produce the program
 * output</li>
 * <li> a public toString method that returns a String in the format:<br>
 * <pre>PA: k BB: m SO: p H: q</pre><br>
 * where k, m, p, and q are the values for plate appearances, walks,
 * strike-outs, and hits.</li>
 * <li> IF YOU DO THE FIRST EXTRA CREDIT define protected methods for the
 * update-able data members(that is, for counts that can change; you can assume
 * no players are traded, so name and playerNumber and position, and throwsRightHanded
 * will not change) the update methods should ADD to the existing values.</li>
 * </ol>
 * All constructors and methods should be declared protected(or private, if they
 * are auxiliary).
 *
 * @author dbriggs
 */
public abstract class Player implements Comparable<Player> {

    // all class properties should be protected    
    protected int playerNumber;
    protected String name;
    protected char position;
    protected int gamesPlayed;
    protected int goals;
    protected int assists;
    protected int penaltyInfractionMinutes;

    // class method implementations go here Constructor, getters, and toString()   
    protected Player(int num, String name, char pos, int gp, int goals,
            int assists, int pim) {
        playerNumber = num;
        this.name = name;
        position = pos;
        gamesPlayed = gp;
        this.goals = goals;
        this.assists = assists;
        penaltyInfractionMinutes = pim;
        
    }
    
    protected int getNumber() {
        return playerNumber;
    }
    
    protected String getName() {
        return name;
    }
    
    protected int getGoals() {
        return goals;
    }
    
    protected int getAssists() {
        return assists;
    }
    
    protected int getPIM() {
        return penaltyInfractionMinutes;
    }
    

    /**
     * returns a string representing the position playerNumber of the player
     *
     * @return a string from the static array.
     */
    protected String getPositionName() {
        return position == 'G' ? "goalie" : position == 'F' ? "forward" : "defence";
    }

    // toString goes here
    @Override
    public String toString() {
        return ("GP: " + gamesPlayed + "G: " + goals + "A: " + assists +
                "PIM: " + penaltyInfractionMinutes);
    }
    
    /**
     * compares two Players by playerNumber. DO NOT CHANGE! <br />
     *
     * @param that a Player object
     * @return an integer value < 0 , 0, or > 0
     */
    @Override
    public int compareTo(Player that) {
        return this.playerNumber - that.playerNumber;
    }
}
