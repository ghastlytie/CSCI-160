/** *********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 * 04/07/2024/ - Severen Denyer -
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package pwhldriver;

/**
 * A class to represent non-goalie position players. You must
 * <ol>
 * <li> define private data members for the additional attributes that are
 * specific to non-goalie players, essentially additional hitting
 * statistics</li>
 * <li> define a public constructor to take values for all the inherited and
 * local data members, which sets their data members' values to the parameter
 * values</li>
 * <li> define public getters for the data members to be used in calculating
 * aggregate statistics for the the team's hitting stats</li>
 * <li> define a public toString method that produces a String in the format
 * needed for the lookup method of Team <br>
 * <pre>n is a position named name and has statistics
 *    PA: k  BB: m  SO: p  H: q  AB: r  BI: s  HR: t HBP: u BA: x  OBP: y </pre> which is
 * discussed in the program assignment. The first 4 items come from the super
 * class toString()</li>
 * </ol>
 * If you do extra credits, you may need to define more methods, but all data
 * members must be private, so only object instances can directly access them.
 *
 * @author dbriggs
 */
public class Skater extends Player {

    //Declaration of private fields
    private int plusMinus;
    private int powerplayGoal;
    private int powerplayAssist;
    private int points;
    private double pointsPerGame;
    private int shorthandGoal;
    private int shorthandAssist;
    private int faceoffWin;
    private int numFaceoff;
    private double wonFaceoffPercent;
    private double penaltyMinutesPerGame;
    private int shots;
    private double shotPercent;
    private int shootoutGoal;
    private int shootoutAssist;
    private double shootoutPercent;

    /**
     * A parameterized constructor of the Skater class that calls inherits from
     * the Player abstract super class.
     *
     * @param num The Skater's number, passed to Player Super Class
     * @param name The Skater's name, passed to Player Super Class
     * @param pos The Skater's position, passed to Player Super Class
     * @param gp The Skater's amount of games played, passed to Player Super
     * Class
     * @param goals The Skater's goals amount, passed to Player Super Class
     * @param assists The Skater's assists amount, passed to Player Super Class
     * @param pim The Skater's infraction minutes, passed to Player Super Class
     * @param pM How many times a player is on the ice when the team scores
     * minus how many times they are on the ice when the opposing team scores
     * @param pPG The number of goals scored during a powerplay for their team
     * @param pPA The number of assists scored during a powerplay for their team
     * @param shG The number of goals scored during an opposing team's
     * powerplay.
     * @param shA The number of assists scored during an opposing team's
     * powerplay.
     * @param foW The number of faceoffs a player has won.
     * @param numFO The number of faceoffs a player participated in.
     * @param shots The number of shots a player has taken.
     * @param soG The number of goals scored during a shootout.
     * @param soA The number of assists scored during a shootout.
     */
    public Skater(int num, String name, char pos, int gp, int goals,
            int assists, int pim, int pM, int pPG, int pPA, int shG,
            int shA, int foW, int numFO, int shots, int soG, int soA) {
        super(num, name, pos, gp, goals, assists, pim);

        //Initialization of parameters
        plusMinus = pM;
        powerplayGoal = pPG;
        powerplayAssist = pPA;
        shorthandGoal = shG;
        shorthandAssist = shA;
        faceoffWin = foW;
        numFaceoff = numFO;
        this.shots = shots;
        shootoutGoal = soG;
        shootoutAssist = soA;

        //Calculates the non-passed stats for the player
        calcPoints();
        calcPointsPerGame();
        calcWonFaceoffPercent();
        calcPenaltyMinutesPerGame();
        calcShotPercent();
        calcShootoutPercent();
    }

    //Calculations of stats.
    /**
     * Calculates the amount of points a player has earned
     */
    private void calcPoints() {
        points = goals + assists;
    }

    /**
     * Calculates the average amount of points per game.
     */
    private void calcPointsPerGame() {
        pointsPerGame = (double) points / gamesPlayed;
    }

    /**
     * Calculates the percentage of faceoffs won.
     */
    private void calcWonFaceoffPercent() {
        wonFaceoffPercent = ((double) faceoffWin / (double) numFaceoff) * 100;
    }

    /**
     * Calculates the average amount of penalty infraction minutes per game.
     */
    private void calcPenaltyMinutesPerGame() {
        penaltyMinutesPerGame = (double) penaltyInfractionMinutes
                / (double) gamesPlayed;
    }

    /**
     * Calculates the shot made percentage.
     */
    private void calcShotPercent() {
        shotPercent = ((double) goals / (double) shots) * 100;
    }

    /**
     * Calculates the percentage of goals vs assists during a shootout.<br>
     */
    private void calcShootoutPercent() {
        shootoutPercent = ((double) shootoutGoal / (double) shootoutAssist) * 100;
    }

    //Getter Methods
    /**
     * Returns the skater points.<br>
     * <br>
     *
     * @return points The amount of points a skater has earned their team.<br>
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the skater's plusMinus stat.
     *
     * @return plusMinus The amount of points the skater's team scored while<br>
     * on the ice minus the amount scored while off the ice.<br>
     */
    public int getPlusMinus() {
        return plusMinus;
    }

    /**
     * Returns the skater's powerplay goals.<br>
     * <br>
     *
     * @return powerplayGoal<br>
     */
    public int getPowerplayGoals() {
        return powerplayGoal;
    }

    /**
     * Returns the skater's powerplay assists.<br>
     *
     * @return powerplayAssist<br>
     */
    public int getPowerplayAssists() {
        return powerplayAssist;
    }

    /**
     * Returns the skater's points scored per game.<br>
     *
     * @return pointsPerGame<br>
     */
    public double getPointsPerGame() {
        return pointsPerGame;
    }

    /**
     * Returns the skater's shorthand goals.<br>
     *
     * @return shorthandGoal<br>
     */
    public int getShorthandGoals() {
        return shorthandGoal;
    }

    /**
     * Returns the skater's shorthand assists<br>
     *
     * @return shorthandAssist<br>
     */
    public int getShorthandAssists() {
        return shorthandAssist;
    }

    /**
     * Returns the amount of faceoffs the skater has won.<br>
     *
     * @return faceoffWin<br>
     */
    public int getFaceoffWins() {
        return faceoffWin;
    }

    /**
     * Returns the number of faceoffs the skater participated in.<br>
     *
     * @return numFaceoff<br>
     */
    public int getNumFaceoff() {
        return numFaceoff;
    }

    /**
     * Returns the percentage of faceoffs won by the skater.<br>
     *
     * @return wonFaceoffPercent<br>
     */
    public double getWonFaceoffPercent() {
        return wonFaceoffPercent;
    }

    /**
     * Returns the skater's average amount of penalty minutes per game.<br>
     *
     * @return penaltyMinutesPerGame<br>
     */
    public double getPenaltyMinutesPerGame() {
        return penaltyMinutesPerGame;
    }

    /**
     * Returns the amount of shots the skater has taken.<br>
     *
     * @return shots<br>
     */
    public int getShots() {
        return shots;
    }

    /**
     * Returns the percentage of shots made by the skater.<br>
     *
     * @return shotPercent<br>
     */
    public double getShotPercent() {
        return shotPercent;
    }

    /**
     * Returns the amount of goals the skater has made during a shootout.<br>
     *
     * @return shootoutGoal<br>
     */
    public int getShootoutGoal() {
        return shootoutGoal;
    }

    /**
     * Returns the amount of assists the skater has made during a shootout.<br>
     *
     * @return shootoutAssist<br>
     */
    public int getShootoutAssist() {
        return shootoutAssist;
    }

    /**
     * Returns the percentage of goals vs assists made by the skater.<br>
     *
     * @return shootoutPercent<br>
     */
    public double getShootoutPercent() {
        return shootoutPercent;
    }

    /**
     * Overrides the default toString method to output a formatted string of<br>
     * the skater's information.<br>
     *
     * @return A formatted output of the skater's information.<br>
     */
    @Override
    public String toString() {
        return String.format("GP: %d G: %d A: %d PIM: %d PTS: %d +/-: %d "
                + "PPG: %d PPA: %d SHG: %d SHA: %d Pt/G: %.2f FOW: %d FO: %d "
                + "WF%%: %.1f PIMPG: %.2f SH: %d SH%%: %.1f SOG: %d SOA: %d "
                + "SO%%: %.1f ",
                gamesPlayed, goals, assists, penaltyInfractionMinutes,
                points, plusMinus, powerplayGoal, powerplayAssist,
                shorthandGoal, shorthandAssist, pointsPerGame, faceoffWin,
                numFaceoff, wonFaceoffPercent, penaltyMinutesPerGame, shots,
                shotPercent, shootoutGoal, shootoutAssist, shootoutPercent);
    }

    /**
     * Unit test for PositionPlayer - creates a PositionPlayer by calling the
     * constructor and then prints it out. There is no need to comment this out!
     * * Do NOT Change. Leave it at the bottom of the file!
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        Player skater = new Skater(25, "Alex Carpenter", 'F', 16, 8, 10, 0,
                0, 3, 5, 1, 0, 174, 291, 60, 1, 3);
        // test toString
        System.out.println((Skater) skater);
        System.out.println("Expected:");
        System.out.println("GP: 16 G: 8 A: 10 PIM: 0 PTS: 18 +/-: 0 PPG: 3 "
                + "PPA: 5 SHG: 1 SHA: 0 Pt/G: 1.13 FOW: 174 FO: 291 "
                + "WF%: 59.8 PIMPG: 0.00 SH: 60 SH%: 13.3 SOG: 1 SOA: "
                + "3 SO%: 33.3\n\n");
        // test accessors for Player class
        System.out.println(skater.getName() + " is a "
                + skater.getPositionName());
        System.out.println("Expected:");
        System.out.println("Alex Carpenter is a forward\n\n");
        Player p = new Skater(29, "Marie-Philip Poulin", 'F', 16, 8, 9, 8,
                9, 0, 1, 0, 0, 212, 370, 50, 1, 6);
        System.out.println((Skater) p);
        System.out.println("Expected:");
        System.out.println("GP: 16 G: 8 A: 9 PIM: 8 PTS: 17 +/-: 9 PPG: 0 "
                + "PPA: 1 SHG: 0 SHA: 0 Pt/G: 1.06 FOW: 212 FO: 370 "
                + "WF%: 57.3 PIMPG: 0.50 SH: 50 SH%: 16.0 SOG: 1 SOA: "
                + "6 SO%: 16.7\n\n");
    }
}
