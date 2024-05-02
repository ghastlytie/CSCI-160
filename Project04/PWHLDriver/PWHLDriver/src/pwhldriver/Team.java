/***********************************************************************
 * REVISION HISTORY (Newest First)
 *********************************************************************** 
 * 04/07/2024/ - Severen Denyer - 
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ***********************************************************************/ 

package pwhldriver;

import java.util.TreeMap;

/**
 * A class to represent a team and its roster.  It needs data members
 * for the name of the team, which will be String w/o embedded blanks,
 * for example, RedSox, BlueJay, Yankees, and WhiteSox, and a Java
 * Collection class for the players on the team.
 * <br>
 * You must use a Map member for the
 * players.  You can set up the Map to lookup players by number,
 * which is more efficient than iterating over a list of players.
 * <br>
 * You must define
 * <ol>
 * <li> a public constructor that takes a name and a map of
 * Player objects(which will be invoked by the League class constructor).
 * <code>public Team(String tname, TreeMap<Integer, Player> roster)</code>
 *   and creates an instance for that team
 * 
 * <li> a public method
 *   <code>String  lookupPlayer(int n)</code>
 * 
 * that returns the appropriate one of the follow two results
 * (assuming t is the name of this team)
 * <br>
 * No player with number n is on the roster for the t.<br> 
 * or<br>
 * <pre><stats for that player></pre>
 * <br>
 The format of the latter is given in the program assignment and
 is based on whether the player is a goalie or a skater.
 You should write the toString method for Goalie and Skater
 to return the appropriate portion of that result.  Dynamic dispatch
 will take care of the rest.</li>
 * 
 * <li> a public method
 * <code>String calcTeamStats()</code>
 * <br>
 * that returns the appropriate choice from the following two
 * results(assuming t is the name of the team)
 * <ol>
 * <li>The t appear to have no pitchers at this time.</li>
 * <li> <code><aggregated pitching stats for t></code></li>
 * </ol>
 * where the format of the latter is discussed in the program assignment.</li>
 * 
 * If you do the extra credit, you will need to define additional methods.
 * Note, all data members should be private, so only the Team object instance 
 * can directly access them.
 * @author dbriggs
 */
public class Team{
    // class implementation goes here

    
    
    /**
     * Unit test for Team - Creates a map with two players and calls the Team
     * Constructor There is no need to comment this out! 
	 * Do NOT Change except to uncomment the stats calls. Leave
     * it at the bottom of the file!
     *
     * @param args command line args
     */
        /**
     *
     * @param args
     */
    public static void main(String[] args){
        TreeMap<Integer, Player> players = new TreeMap<>();
        players.put(65, new Goalie(65, "Jonathan", 1, true, 416, 23, 80,
               111, 259, 32));  
        players.put(35, new Goalie(35, "Eric", 1, true, 2, 2, 2, 2, 2, 2));
        players.put(30, new Goalie(30, "Derek", 1, true, 3, 3, 3, 3, 3, 3));

        players.put(26, new Skater(26, "Brock", 4, true, 1443, 33, 83,
                422, 454, 274, 50, 2));
        Team team = new Team("RedSox", players);
        for (Integer num : players.keySet()) {
            System.out.println(players.get(num));
        }
        System.out.println("Expected Output: \n"
                + "PA: 1443 BB: 33 SO: 83 H: 422 AB: 454 BI: 274 HR: 50 "
                + "HBP: 2 BA: 0.930  OBP: 0.317\n"
                + "PA: 416 BB: 23 SO: 80 H: 111 IP: 86 1/3 ER: 32 ERA: 3.34 "
                + "WHIP: 1.55 \n\n");
        System.out.println(team.lookupPlayer(65));
        System.out.println("Expected Output: \n"
                + "The RedSox's player number 65 is a pitcher named Jonathan "
                + "with statistics:\n"
                + "PA: 416 BB: 23 SO: 80 H: 111 IP: 86 1/3 ER: 32 ERA: 3.34 "
                + "WHIP: 1.55\n\n");
        System.out.println(team.lookupPlayer(3));
        System.out.println("Expected Output: \n"
                + "No player with number 3 is on the roster for RedSox.");
        //System.out.println(team.calculatePitchingStats());
        System.out.println("There are 3 pitchers on the RedSox and their "
                + "aggregated statistics are \n" 
                + "PA: 421 BB: 28 SO: 85 H: 116 IP: 264 ER: 37 ERA: 3.784 "
                + "WHIP: 1.64");
        //System.out.println(team.calculateHittingStats());
        System.out.println("There are 1 hitters on the RedSox and their "
                + "aggregated statistics are \n" 
                + "PA: 1443 BB: 33 SO: 83 H: 422 AB: 454 BI: 274 HR: 50 "
                + "HBP: 2 BA: 0.930 OBP: 0.317");
    }
}