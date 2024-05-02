/** *********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 * 03/2024 - Jemma Droppo - modifications to support PWHL
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package pwhldriver;

/**
 * A class to represent the goalies on a team. You must
 * <ol>
 * <li> define private data members for the additional attributes that are
 * specific to goalies, essentially a few additional statistics </li>
 * <li> define a public constructor to take values for all the inherited and
 * local data members, which sets their data members' values to the parameter
 * values</li>
 * <li> define public getters for the count data members to be used in
 * calculating aggregate statistics for the the team's goalies</li>
 * <li> define a public toString method that produces a String in the format
 * needed for the lookup method of Team </li>
 * </ol>
 * <pre>    n is a goalie named name and has statistics
 *    PA: k  BB: m  SO: p  H: q  IP: r  ER: s  ERA: x  WHIP: y</pre> which is discussed in
 * the program assignment. The first 4 items come from the super class
 * toString() If you do extra credits, you may need to define more methods, but
 * all data members must be private, so only object instances can directly
 * access them.
 *
 * @author dbriggs
 */
public class Goalie extends Player {
    // class properties go here

    private String timePlayed;
    private int minutesPlayed;
    private int secondsPlayed;
    private int goalsAgainst;
    private int shutouts;
    private double goalsAgainstAverage;
    private int win;
    private int loss;
    private int overtime;
    private int saves;
    private int shotsAgainst;
    private double savePercent;
    private int shootoutRecieved;
    private int shootoutGoal;
    private double shootoutSavePercent;

    // class implementation goes here
    public Goalie(int num, String name, char position, int gamesPlayed,
            int goals, int assists, int penaltyInfractionMinutes,
            String timePlayed, int goalsAgainst, int shutouts, int wins,
            int losses, int overtime, int saves, int shotsAgainst,
            int shotsRecievedInShootout, int shootoutGoalsAgainst) {
        super(num, name, position, gamesPlayed, goals, assists,
                penaltyInfractionMinutes);

        calcMinutesPlayed(timePlayed);
        calcSecondsPlayed(timePlayed);

    }

    //Calculations for stats
    private void calcMinutesPlayed(String timePlayed) {
        minutesPlayed = Integer.parseInt(timePlayed.split(":")[0]);
    }

    private void calcSecondsPlayed(String timePlayed) {
        secondsPlayed = Integer.parseInt(timePlayed.split(":")[1]);
    }
    
    //Accessor Methods
    private int getMinutesPlayed() {
        return minutesPlayed;
    }
    
    private int getSecondsPlayed() {
        return secondsPlayed;
    }

    /**
     * Unit test for goalie. There is no need to comment this out! Do NOT
     * Change. Leave it at the bottom of the file!
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        Goalie goalie = new Goalie(20, "Elaine Chuli", 'G', 6, 0, 0, 0,
                "359:23", 8, 0, 5, 1, 0, 182, 190, 0, 0);
        // test toString
        System.out.println(goalie);
        System.out.println("Expectd Output:\n"
                + "Min: 359:32 GA: 8 SO: 0 GAA: 1.34 W: 5 L: 1 OT: 0 SVS: 182 "
                + "SA: 190 SAV%: 0.958 SOA: 0 SOGA: 0 SO%: 0.000 GP: 06 G: 0 A: 0"
                + " PIM: 0\n\n");
        // test accessors for Player class
        System.out.println(goalie.getName() + " is a "
                + goalie.getPositionName());
        System.out.println("Expected Output: ");
        System.out.println("Elaine Chuli is a goalie\n\n");
        Player p = new Goalie(29, "Nicole Hensley", 'G', 10, 0, 1, 2,
                "604:28", 18, 1, 6, 3, 1, 258, 276, 0, 0);
        System.out.println((Goalie) p);
        System.out.println("Expectd Output:\n"
                + "Min: 604:28 GA: 18 SO: 1 GAA: 1.79 W: 6 L: 3 OT: 1 SVS: 258 "
                + "SA: 190 SAV%: 0.935 SOA: 0 SOGA: 0 SO%: 0.000 GP: 10 G: 0 A: 1"
                + " PIM: 2\n\n");
        p = new Goalie(35, "Maddie Rooney", 'G', 7, 0, 0, 0,
                "427:38", 13, 2, 4, 1, 2, 153, 166, 3, 1);
        System.out.println((Goalie) p);
        System.out.println("Expectd Output:\n"
                + "Min: 427:38 GA: 13 SO: 2 GAA: 1.82 W: 4 L: 1 OT: 2 SVS: 153 "
                + "SA: 166 SAV%: 0.922 SOA: 3 SOGA: 1 SO%: 0.667 GP: 07 G: 0 A: 0"
                + " PIM: 0\n\n");

    }

}
