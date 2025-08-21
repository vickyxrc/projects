/*  Student information for assignment:
 *
 *  On my honor, <Vicky Chen>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: vc23777
 *  email address: vickyxrc@utexas.edu
 *  Number of slip days I am using: 1
 */

import java.util.ArrayList;
import java.util.Iterator;

public class NameRecord implements Comparable<NameRecord>{
    private String name;
    private int startDecade;
    private ArrayList<Integer> ranks;
    private int numDecades;


    /**
     * @param name, startDecade, ranks
     * pre: ranks must have at least one non-zero value (ranked within 1000),
     *      ranks.size() == numDecades
     *
     */
    public NameRecord(String name, int startDecade, ArrayList<Integer> ranks, int numDecades) {
        this.name = name;
        this.startDecade = startDecade;
        this.ranks = ranks;
        this.numDecades = numDecades;
    }

    /**
     * pre: none
     * @return the value stored in the name instance variable of this NameRecord
     */
    public String getName() {
        return name;
    }

    /**
     * pre: none
     * @return the value stored in the startDecade instance variable of this NameRecord
     */
    public int getStartDecade() {
        return startDecade;
    }

    /**
     * pre: none
     * @return the number of decades of ranking this NameRecord has, stored in instance variable
     */
    public int getNumDecades() {
        return numDecades;
    }

    /**
     * @param decade from the start year
     * @return the rank of the given decade, stored in instance variable
     */
    public int getRank(int decade) {
        return ranks.get(decade);

    }

    /**
     * indicates the name's most popular decade
     * pre: none
     * @return the decade in which the name had the lowest (most popular) non-zero rank
     */
    public int mostPopularDecade () {
        int mostPopular = Integer.MAX_VALUE;
        int mostPopDec = startDecade;
        for (int decade = 0; decade < ranks.size(); decade++) {
            if ((getRank(decade) <= mostPopular) && (getRank(decade) != 0)) {
                mostPopular = getRank(decade);
                mostPopDec = startDecade + (decade * 10);
            }
        }
        return mostPopDec;
    }

    /**
     * indicates the number of decades this name is ranked
     * pre: none
     * @return number of decades in which the name has a rank > 0
     */
    public int numDecRanked() {
        int numDec = 0;
        for (int decade = 0; decade < ranks.size(); decade++) {
            if (getRank(decade) != 0) {
                numDec++;
            }
        }
        return numDec;
    }

    /**
     * indicates if a name has ranks > 0 for all decades
     * pre: none
     * @return true if the name has all decades ranked (rank > 0), otherwise false
     */
    public boolean allDecRanked() {
        if (numDecRanked() == numDecades) {
            return true;
        }
        return false;
    }

    /**
     * indicates if a name has exactly one decade with rank > 0
     * pre: none
     * @return true if the name has exactly one decade ranked (rank > 0), otherwise false
     */
    public boolean oneDecRanked() {
        if (numDecRanked() == 1) {
            return true;
        }
        return false;
    }

    /**
     * indicates whether a name gets more popular every decade
     * pre: none
     * @return true if the ranks decreases each decade (gets more popular), otherwise false
     */
    public boolean morePopular() {
        //Begins checking at the second decade
        for (int decade = 1; decade < ranks.size(); decade++) {
            int currRank = getRank(decade);
            int prevRank = getRank(decade - 1);
            //false if any decade other than starting is rank 0 because that means 2 consecutive 0's
            //or the decade to the left has smaller (more popular) ranking
            if ((currRank == 0) || ((prevRank != 0) && !(currRank < prevRank))){
                return false;
            }
        }
        return true;
    }

    /**
     * indicates whether a name gets less popular every decade
     * pre: none
     * @return true if the ranks increases each decade (gets less popular), otherwise false
     */
    public boolean lessPopular() {
        //Begins checking the second to last decade and loops in reverse
        for (int decade = ranks.size() - 2; decade >= 0; decade--) {
            int currRank = getRank(decade);
            int prevRank = getRank(decade + 1);
            //false if any decade other than last is rank 0 because that means 2 consecutive 0's
            //or the decade to the right has smaller (more popular) ranking
            if ((currRank == 0) || ((prevRank != 0) && !(currRank < prevRank))){
                return false;
            }
        }
        return true;
    }

    /**
     * pre:none
     * @return a string representation of a name record with formatted ranks of each decade
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(getName() + "\n");
        for (int decade = 0; decade < ranks.size(); decade++) {
            sb.append((startDecade + decade * 10) + ": " + getRank(decade) + "\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param other the NameRecord to be compared.
     * @return int value < 0 if the name of this NameRecord has less value than the other NameRecord
     * (if name is higher on the alphabet), and an int value > 0 if vice versa
     */
    public int compareTo(NameRecord other) {
        if (other == null) {
            throw new IllegalArgumentException("NameRecord cannot be null");
        }
        return getName().compareTo(other.getName());
    }


}
