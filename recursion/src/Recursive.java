/*  Student information for assignment:
 *
 *  On OUR honor, Vicky Chen and Laila Olvera,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 Name: Vicky Chen
 *  UTEID: vc23777
 *  email address: vickyxrc@utexas.edus
 *
 *  Student 2 Name: Laila Olvera
 *  UTEID: lo6293
 *  email address: luscinia1074@gmail.com
 *
 *  Grader name: Gracelynn Ray
 *  Section number: 50720
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

/**
 * Various recursive methods to be implemented.
 */
public class Recursive {

    private static final int INVALID = Integer.MAX_VALUE;

    /**
     * Problem 1: Returns the number of elements in data that are followed
     * directly by value that is double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed
     * immediately by double the value
     *
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by
     * a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        final int FIRST_INDEX = 0;
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }
        return nextIsDoubleHelper(data, FIRST_INDEX);
    }

    /**
     * helper recursive method for nextIsDouble
     * @param data array of values we are evaluating for next doubles
     * @param index index in the data array we are currently on
     *
     * pre: none
     * @return the number of elements that are directly followed by an element that is double its
     * value
     */
    private static int nextIsDoubleHelper(int[] data, int index) {
        final int FINAL_INDEX = data.length - 1;
        final int NO_NEXT_DOUBLE = 0;

        if (index == FINAL_INDEX) {
            return NO_NEXT_DOUBLE; //no following element. Next element can't be double
        } else {
            final int NEXT_INDEX = index + 1;
            final boolean NEXT_IS_DOUBLE = data[index] * 2 == data[NEXT_INDEX];

            if (NEXT_IS_DOUBLE) {
                //add count to the number elements with next element double
                return 1 + nextIsDoubleHelper(data, NEXT_INDEX);
            }
            else {
                return nextIsDoubleHelper(data, NEXT_INDEX);
            }

        }
    }

    /**
     * Problem 2: Draw a Sierpinski Carpet.
     *
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /**
     * Helper recursive method for drawCarpet
     * Draw the individual squares of the carpet.
     *
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     *
     * pre: none
     * post: draws a Sierspenski carpet pattern with the smallest squares being the defined size
     * limit
     */
    private static void drawSquares(Graphics g, int size, int limit,
                                    double x, double y) {
        if (size <= limit) {
            return;
        }
        else {
            size = size / 3; //divide each grid into 9 more grids
            //fill middle grid
            g.fillRect((int)(Math.round(x + size)), (int)(Math.round(y + size)), size, size);

            drawSquares(g, size, limit, x, y); //top left grid
            drawSquares(g, size, limit, (x + size), y); //top middle grid
            drawSquares(g, size, limit, (x + (size * 2)), y); //top right grid
            drawSquares(g, size, limit, x, (y + size)); //midlle left grid
            drawSquares(g, size, limit, (x + (size * 2)), (y + size)); //middle right grid
            drawSquares(g, size, limit, x, (y + (size * 2))); //bottom left grid
            drawSquares(g, size, limit, (x + size), (y + (size * 2))); //bottom middle grid
            drawSquares(g, size, limit, (x + (size * 2)), (y + (size * 2))); //botom right grid
        }
    }

    /**
     * Problem 3: Find the minimum difference possible between teams based on
     * ability scores. The number of teams may be greater than 2. The goal is
     * to minimize the difference between the team with the maximum total
     * ability and the team with the minimum total ability.
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * post: return the minimum possible difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     *
     * @param numTeams the number of teams to form
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team with the
     * maximum total ability and the team with the minimum total ability. The
     * return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        if (numTeams < 2) {
            throw new IllegalArgumentException("Must have at least 2 teams.");
        }
        if (abilities == null) {
            throw new IllegalArgumentException("Abilities cannot be null");
        }

        int[] teamSums = new int[numTeams];
        int abilitiesIndex = 0;
        return minDiffHelper(teamSums, abilities, numTeams, abilitiesIndex, Integer.MAX_VALUE);
    }

    /**
     *
     * @param teamSums array that tracks the sum of each team
     * @param abilities array of ability scores
     * @param numTeams total number of teams to divide our abilities
     * @param abilitiesIndex index in the abilities array of each round
     * @param minDiff the minimum difference between teams
     *
     * pre: none
     * @return the minimum difference between teams after we explore every team division
     */
    //Reference code for backtracking:
    // https://www.geeksforgeeks.org/partition-set-k-subsets-equal-sum/
    private static int minDiffHelper(int[] teamSums, int[] abilities, int numTeams,
                                     int abilitiesIndex, int minDiff) {
        //base cases
        if (numTeams > abilities.length) {
            return INVALID;
        }
        if (abilitiesIndex == abilities.length) {
            if (invalid(teamSums)) {
                return INVALID;
            }
            int [] sortedTeamSums = Arrays.copyOf(teamSums, numTeams);
            Arrays.sort(sortedTeamSums);
            return sortedTeamSums[numTeams-1] - sortedTeamSums[0];
        }

        for (int team = 0; team < numTeams; team++) {
            teamSums[team] += abilities[abilitiesIndex];
            int diff = minDiffHelper(teamSums, abilities, numTeams, abilitiesIndex + 1, minDiff);
            minDiff = Math.min(minDiff, diff);
            // Anti-choose
            teamSums[team] -= abilities[abilitiesIndex];
        }
        return minDiff;
    }

    /**
     * helper method for minDiffHelper to check if a division of abilities into teams is valid
     * @param teamSums array of the sum of scores of the teams
     * @return true if every team (index of array) has a score greater than 0. This means that
     * every team has at least one ability. Otherwise, return false.
     */
    private static boolean invalid(int[] teamSums) {
        for (int team = 0; team < teamSums.length; team++) {
            if (teamSums[team] == 0) {
                return true;
            }
        }
        return false;
    }
}