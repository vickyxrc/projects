/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: vc23777
 *  email address: vickyxrc@utexas.edu
 *  TA name: Gracelyn Ray
 */

/*
 * Question.
 *
 * 1. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methods are reasonable.
 * However if all results from all college football teams are included
 * some unexpected results occur. Explain the unexpected results. You may
 * have to do some research on the various college football divisions to
 * make an informed answer. (What are the divisions within college
 * football? Who do teams play? How would this affect the
 * structure of the graph?)
 * 
 *      I noticed that in the games08.txt, a lot of non-division 1 teams were ranked pretty high
 * with a lot outranking division 1 teams. This is unexpected because division 1 teams come
 * from bigger schools with stronger players and funding, and just overall have a competitive
 * edge over lower division schools. This makes it surprising that lower division schools 
 * shows higher rankings.
 * 
 * 2. Suggest another way/method of ranking teams using the results from the graph. 
 * Thoroughly explain your method. The method can build on one of the three existing 
 * algorithms.
 * 
 *      Another way of ranking teams is separating each division to its own ranking, since
 * actually football rankings don't rank schools of different divisions together. Within
 * the vertex class, include a division information. In printMeanSqureError method, assign
 * d2 teams rank after d1 teams rank, and d3 after d2.
 * 
 * 

 */

public class GraphAndRankTester {

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     * @param args None expected.
     */
    public static void main(String[] args)  {
        graphTests();

        String actual = "2008ap_poll.txt";
        String gameResults = "div12008.txt";

        FootballRanker ranker = new FootballRanker(gameResults, actual);

        ranker.doUnweighted(true);
        ranker.doWeighted(true);
        ranker.doWeightedAndWinPercentAdjusted(true);

        System.out.println();
        doRankTests(ranker);

        System.out.println();

    }

    // tests on various simple Graphs
    private static void graphTests() {
        System.out.println("MY TEST CASES\n");
        myTestsCases();
    }

    /* The graph used here is the same one from the example
     * used to show Dijktra's algorithm from the slides on
     * Graphs. It may be useful to print out the priority queue
     * at the top of the main while loop in the dijktra method
     * to see if it matches the one in the slides. Note, the Java
     * PriorityQueue (which you CAN use in the dijkstra method)
     * breaks ties in an arbitrary manner, so the order of equal
     * elements in the priority queue may be different than those
     * shown in the slides. (And that is not a problem.)
     */
    private static void myTestsCases() {
        String [][] g1Edges =  
               {{"I", "D", "16"},
                {"I", "B", "2"},
                {"B", "D", "4"},
                {"B", "E", "22"},
                {"D", "E", "11"},
                {"E", "G", "36"},
                {"G", "O", "91"}};

        Graph g1 = getGraph(g1Edges, false);

        //dijkstra test 1
        System.out.println("DIJKSTRA TEST1 ON GRAPH 1");
        g1.dijkstra("I");
        String actualPath1 = g1.findPath("O").toString();
        String expected1 = "[I, B, D, E, G, O]";
        if (actualPath1.equals(expected1)) {
            System.out.println("PASSED DIJKSTRA TEST1.");
        } else {
            System.out.println("FAILED DIJKSTRA TEST1. Expected: " + expected1 + " actual "
             + actualPath1);
        }

        String [][] g2Edges =  
                {{"P", "R", "1"},
                {"P", "Q", "18"},
                {"R", "S", "13"},
                {"Q", "U", "39"},
                {"P", "T", "999999"},
                {"T", "V", "3"},
                {"V", "S", "46"},
                {"S", "U", "15"}};
        Graph g2 = getGraph(g2Edges, false);
        //dijkstra test 2
        System.out.println("\nDIJKSTRA TEST2 ON GRAPH 2");
        g2.dijkstra("P");
        String actualPath2 = g2.findPath("V").toString();
        String expected2 = "[P, R, S, V]";
        if (actualPath2.equals(expected2)) {
            System.out.println("PASSED DIJKSTRA TEST2.");
        } else {
            System.out.println("FAILED DIJKSTRA TEST2. Expected: " + expected2 + " actual "
             + actualPath2);
        }







        //findAllPaths Unweighted Test1
        System.out.println("\nFINDALLPATHS UNWEIGHTED TEST1 ON GRAPH 1");
        String[] expectedPaths = {
                "Name: E                    cost per path: 1.4000, num paths: 5",
                "Name: B                    cost per path: 1.6000, num paths: 5",
                "Name: D                    cost per path: 1.6000, num paths: 5",
                "Name: G                    cost per path: 1.8000, num paths: 5",
                "Name: I                    cost per path: 2.2000, num paths: 5",
                "Name: O                    cost per path: 2.6000, num paths: 5",};
        doAllPathsTests(g1, false, 4, 4.0, expectedPaths);


        //findAllPaths Unweighted Test2
        System.out.println("\nFINDALLPATHS UNWEIGHTED TEST2 ON GRAPH 2");
        String[] expectedPaths2 = {
                "Name: P                    cost per path: 1.5000, num paths: 6",
                "Name: S                    cost per path: 1.5000, num paths: 6",
                "Name: R                    cost per path: 1.6667, num paths: 6",
                "Name: Q                    cost per path: 1.8333, num paths: 6",
                "Name: T                    cost per path: 1.8333, num paths: 6",
                "Name: U                    cost per path: 1.8333, num paths: 6",
                "Name: V                    cost per path: 1.8333, num paths: 6"};
        doAllPathsTests(g2, false, 3, 3.0, expectedPaths2);



        //findAllPaths Weighted Test1
        System.out.println("\nFINDALLPATHS WEIGHTED TEST1 ON GRAPH 1");
        String[] expectedPaths3 = {
                "Name: D                    cost per path: 41.2000, num paths: 5",
                "Name: E                    cost per path: 41.2000, num paths: 5",
                "Name: B                    cost per path: 42.8000, num paths: 5",
                "Name: I                    cost per path: 44.4000, num paths: 5",
                "Name: G                    cost per path: 55.6000, num paths: 5",
                "Name: O                    cost per path: 128.4000, num paths: 5",};
        doAllPathsTests(g1, true, 5, 144.0, expectedPaths3);
        
        //findAllPaths Weighted Test2
        System.out.println("\nFINDALLPATHS WEIGHTED TEST2 ON GRAPH 2");
        String[] expectedPaths4 = {
                "Name: S                    cost per path: 28.1667, num paths: 6",
                "Name: R                    cost per path: 30.3333, num paths: 6",
                "Name: P                    cost per path: 30.8333, num paths: 6",
                "Name: U                    cost per path: 39.3333, num paths: 6",
                "Name: Q                    cost per path: 44.5000, num paths: 6",
                "Name: V                    cost per path: 51.1667, num paths: 6",
                "Name: T                    cost per path: 53.6667, num paths: 6"};
        doAllPathsTests(g2, true, 5, 81.0, expectedPaths4);
    }


    // return a Graph based on the given edges
    private static Graph getGraph(String[][] edges, boolean directed) {
        Graph result = new Graph();
        for (String[] edge : edges) {
            result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
            // If edges are for an undirected graph add edge in other direction too.
            if (!directed) {
                result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
            }
        }
        return result;
    }

    // Tests the all paths method. Run each set of tests twice to ensure the Graph
    // is correctly reseting each time
    private static void doAllPathsTests(Graph g, boolean weighted, int expectedDiameter, 
    double expectedCostOfLongestShortestPath, String[] expectedPaths) {
        for (int i = 0; i < 2; i++) {
            System.out.println("Test run = " + (i + 1));
            System.out.println("Find all paths weighted = " + weighted);
            g.findAllPaths(weighted);
            int actualDiameter = g.getDiameter();
            double actualCostOfLongestShortesPath = g.costOfLongestShortestPath();
            if (actualDiameter == expectedDiameter) {
                System.out.println("Passed diameter test.");
            } else {
                System.out.println("FAILED diameter test. "
                        + "Expected = "  + expectedDiameter +
                        " Actual = " + actualDiameter);
            }
            if (actualCostOfLongestShortesPath == expectedCostOfLongestShortestPath) {
                System.out.println("Passed cost of longest shortest path. test.");
            } else {
                System.out.println("FAILED cost of longest shortest path. "
                        + "Expected = "  + expectedCostOfLongestShortestPath +
                        " Actual = " + actualCostOfLongestShortesPath);
            }
            testAllPathsInfo(g, expectedPaths);
            System.out.println();
        }

    }

    // Compare results of all paths info on Graph to expected results.
    private static void testAllPathsInfo(Graph g, String[] expectedPaths) {
        int index = 0;

        for (AllPathsInfo api : g.getAllPaths()) {
            if (expectedPaths[index].equals(api.toString())) {
                System.out.println(expectedPaths[index] + " is correct!!");
            } else {
                System.out.println("ERROR IN ALL PATHS INFO: ");
                System.out.println("index: " + index);
                System.out.println("EXPECTED: " + expectedPaths[index]);
                System.out.println("ACTUAL: " + api.toString());
                System.out.println();
            }
            index++;
        }
        System.out.println();
    }

    // Test the FootballRanker on the given file.
    private static void doRankTests(FootballRanker ranker) {
        System.out.println("\nTESTS ON FOOTBALL TEAM GRAPH WITH FootBallRanker CLASS: \n");
        double actualError = ranker.doUnweighted(false);
        if (actualError == 13.7) {
            System.out.println("Passed unweighted test");
        } else {
            System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 13.7, actual: " + actualError);
        }

        actualError = ranker.doWeighted(false);
        if (actualError == 12.6) {
            System.out.println("Passed weigthed test");
        } else {
            System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 12.6, actual: " + actualError);
        }


        actualError = ranker.doWeightedAndWinPercentAdjusted(false);
        if (actualError == 6.3) {
            System.out.println("Passed unweighted win percent test");
        } else {
            System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST. Expected 6.3, actual: " + actualError);
        }
    }
}