/***********************************************************************
 * REVISION HISTORY (Newest First)
 * **********************************************************************
 * 03/2024 - Jemma Droppo - modifications to support PWHL
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments 
 ***********************************************************************/

package pwhldriver;

import java.util.*;

/**
 * A class to hold a number of team rosters for the teams of a league.
 * You must use a Map data member to hold the Team objects, either a HashMap
 * or a TreeMap may be used.
 * You must
 * <ol>
 * <li> code a constructor that takes a Scanner object and which loads
 *     the Map data member with the collection of Team objects from the file
 *     given by the Scanner.</li>
 * <li> code a public method String lookupPlayer(String t, int n)
 *     that returns the appropriate choice from the following three results
 *    <ol>
 *     <li>No team named t is in the league.</li>
 *     <li>No player with number n is on the roster for the t team.</li>
 *     <li><code><player stats for player n of team t></code></li>
 *    </ol>
 *     where the format of the last is given in the program assignment.</li>
 * <li> code a public method String calcTeamStats(String t)
 *     that returns the appropriate choice from the following results</li>
 *     <ol>
 *     <li>No team named t is in the league.</li>
 *     <li><code><team stats for t></code></li>
 *     where the format of the last is given in the program assignment.</li>
 * <li> code a public method String calcLeagueStats()
 *     that returns the appropriate choice from the following three results</li>
 *     <ol>
 *     <li>No teams are in the league.<li>
 *     <li><code><the league stats for all teams></code></li>
 *     where the format of the last is given in the program assignment.
 *     Consult the program assignment for their specifications.</li>
 * <li> code a public method String update(Scanner scnr)
 *     that updates a player's information and/or returns the appropriate choice
 *     from the following results</li>
 *     <ol>
 *     <li>No team named t is in the league.</li>
 *     <li>No player with number n is on the roster for the t team</li>
 *     <li><updated player statistics></li>
 *     Where the updated player statistics matches with the requirements in the
 *     original assignment handout.
 *     </ol>
 *</ol> 
 * @author David Briggs
 */
public class League{
    /*
         Properties 
    */
    
    
    /**
     * Constructor for League.  Reads the input file given and validated
     * on the command line.
     * @param s a Scanner object that has already been connected to a 
     *    physical file in the driver.
     */
    public League(Scanner s){
        
        
    }

    

    /**
     * Lookup a specific player on a specific team.
     * be sure to use ignore case for all string inputs
     * @param team a String representing the team name
     * @param playerNum an int representing the player we are looking up
     * @return a string representing the results of the lookup.  
     */
    public String lookup(String team, int playerNum){
        StringBuilder str = new StringBuilder();
        str.append("lookup stub");
        return str.toString();
    }

    /**
     * Calculate the pitching statistics for a given team
     * be sure to use ignore case for all string inputs
     * @param team a String that is the team name - the key for the map
     * @return a String representing the results of the calculations
     */
    public String calcTeamStats(String team){
        StringBuilder str = new StringBuilder();
        str.append("Team stub");
        return str.toString();
    }

    /**
     * Calculate the hitting statistics for a given team
     * be sure to use ignore case for all string inputs
     * @param team a String that is the team name - the key for the map
     * @return a String representing the results of the calculations
     */
    public String calcLeagueStats(){
        StringBuilder str = new StringBuilder();
        str.append("League stub");
        return str.toString();
    }


    /**
     * Allows the players on a team to update statistics based on the
     * outcome of a current game. (ideally will update two teams from one game)
     * There is no need to comment this out! 
     * Do NOT Change until you implement it.
     * @param scnr a keyboard Scanner object
     * @return A String as specified
     */
    public String update(Scanner scnr){
        StringBuilder str = new StringBuilder();
        str.append("update stub");
        return str.toString();
    }
}