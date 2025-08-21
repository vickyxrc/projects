/*
 * Student information for assignment: Replace Vicky Chen in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone.
 *
 * On my honor, Vicky Chen, this programming assignment is my own work
 * and I have not provided this code
 * to any other student.
 *
 * UTEID: vc23777
 * email address: vickyxrc@utexas.edu
 * Number of slip days I am using: 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NameSurfer {

    // CS 314 students, explain your novel menu option here:
    // My novel menu option returns all the names from the data
    // file that are spelled alphabetically. I used comparison
    // operator to check if each character in a name is greater
    // that the previous character, and if it is, it gets stored
    // in an ArrayList of strings that gets returned.

    // CS 314 students, explain your interesting search / trend here:
    // The name trend I investivated was the popularity of names starting
    // the letter "X" over the decades. I felt like "X" is a very uncommon
    // first letter for a name, so I wondered if there was any pattern to its
    // popularity. The X names I found were:
    // Xander
    // Xavier
    // Xavi
    // Ximena
    // Xiomara
    // Xzavier
    // I explored the rankings of these names listed above for my investigation.
    // Result:
    // Xander
    // 1870: 0
    // 1880: 0
    // 1890: 0
    // 1900: 0
    // 1910: 0
    // 1920: 0
    // 1930: 0
    // 1940: 0
    // 1950: 0
    // 1960: 0
    // 1970: 0
    // 1980: 0
    // 1990: 928
    // 2000: 254
    // 2010: 196
    //
    // Xavier
    // 1870: 0
    // 1880: 812
    // 1890: 0
    // 1900: 0
    // 1910: 0
    // 1920: 0
    // 1930: 0
    // 1940: 869
    // 1950: 701
    // 1960: 547
    // 1970: 466
    // 1980: 186
    // 1990: 103
    // 2000: 71
    // 2010: 89
    //
    // Xavi
    // 1870: 0
    // 1880: 0
    // 1890: 0
    // 1900: 0
    // 1910: 0
    // 1920: 0
    // 1930: 0
    // 1940: 0
    // 1950: 0
    // 1960: 0
    // 1970: 0
    // 1980: 0
    // 1990: 0
    // 2000: 832
    // 2010: 0
    //
    // Ximena
    // 1870: 0
    // 1880: 0
    // 1890: 0
    // 1900: 0
    // 1910: 0
    // 1920: 0
    // 1930: 0
    // 1940: 0
    // 1950: 0
    // 1960: 0
    // 1970: 0
    // 1980: 0
    // 1990: 0
    // 2000: 272
    // 2010: 141
    //
    // Xiomara
    // 1870: 0
    // 1880: 0
    // 1890: 0
    // 1900: 0
    // 1910: 0
    // 1920: 0
    // 1930: 0
    // 1940: 0
    // 1950: 0
    // 1960: 0
    // 1970: 0
    // 1980: 0
    // 1990: 0
    // 2000: 986
    // 2010: 0
    //
    // Xzavier
    // 1870: 0
    // 1880: 0
    // 1890: 0
    // 1900: 0
    // 1910: 0
    // 1920: 0
    // 1930: 0
    // 1940: 0
    // 1950: 0
    // 1960: 0
    // 1970: 0
    // 1980: 0
    // 1990: 873
    // 2000: 547
    // 2010: 731
    //
    // Conclusion: Based on examining the rankings of each name, I found that
    // every single X name was more popular on average during recent decades than
    // in the older decades. I think it is interesting to see that X names did
    // not become within rank 1000 until recent decades.
    //

    private static final String NAME_FILE = "names.txt";

    // A few simple tests for the Names and NameRecord class.
    public static void simpleTest() {
        // Bob's nameRecord
        List<Integer> bobList = Arrays.asList(443, 345, 138, 89, 122, 225, 199, 583, 947, 0, 0);
        //Reference for adding multiple values at once to arraylist:
        //https://beginnersbook.com/2022/08/add-multiple-items-to-an-arraylist-in-java
        int bobBaseDec = 1900;
        ArrayList<Integer> bobRanks = new ArrayList<Integer>();
        bobRanks.addAll(bobList);
        int bobNumDec = 11;
        NameRecord bobRecord =  new NameRecord("Bob", bobBaseDec, bobRanks, bobNumDec);

        // Verna's NameRecord
        List<Integer> vernaList = Arrays.asList(129, 152, 184, 262, 346, 507, 0, 0, 0);
        int vernaBaseDec = 1910;
        ArrayList<Integer> vernaRanks = new ArrayList<Integer>();
        vernaRanks.addAll(vernaList);
        int vernaNumDec = 9;
        NameRecord vernaRecord =  new NameRecord("Verna", vernaBaseDec, vernaRanks, vernaNumDec);

        //Test 1: getName
        String expStrBob = "Bob";
        String actStrBob = bobRecord.getName();
        System.out.println("expected:" + expStrBob);
        System.out.println("actual:" + actStrBob);
        if (expStrBob.equals(actStrBob)) {
            System.out.println("passed: Test1 getName");
        } else {
            System.out.println("FAILED: Test1 getName");
        }

        //Test 2: getName
        String expStrVerna = "Verna";
        String actStrVerna = vernaRecord.getName();
        System.out.println("\nexpected:" + expStrVerna);
        System.out.println("actual:" + actStrVerna);
        if (expStrVerna.equals(actStrVerna)) {
            System.out.println("passed: Test2 getName");
        } else {
            System.out.println("FAILED: Test2 getName");
        }

        //Test3: getStartDecade
        int expIntBob = 1900;
        int actIntBob = bobRecord.getStartDecade();
        System.out.println("\nexpected:" + expIntBob);
        System.out.println("actual:" + actIntBob);
        if (expIntBob == actIntBob) {
            System.out.println("passed: Test3 getStartDecade");
        } else {
            System.out.println("FAILED: Test3 getStartDecade");
        }

        //Test4: getStartDecade
        int expIntVerna = 1910;
        int actIntVerna = vernaRecord.getStartDecade();
        System.out.println("\nexpected:" + expIntVerna);
        System.out.println("actual:" + actIntVerna);
        if (expIntVerna == actIntVerna) {
            System.out.println("passed: Test4 getStartDecade");
        } else {
            System.out.println("FAILED: Test4 getStartDecade");
        }

        //Test 5: getNumDecades
        expIntBob = 11;
        actIntBob = bobRecord.getNumDecades();
        System.out.println("\nexpected:" + expIntBob);
        System.out.println("actual:" + actIntBob);
        if (expIntBob == actIntBob) {
            System.out.println("passed: Test5 getNumDecade");
        } else {
            System.out.println("FAILED: Test5 getNumDecade");
        }

        //Test6: getNumDecades
        expIntVerna = 9;
        actIntVerna = vernaRecord.getNumDecades();
        System.out.println("\nexpected:" + expIntVerna);
        System.out.println("actual:" + actIntVerna);
        if (expIntVerna == actIntVerna) {
            System.out.println("passed: Test6 getNumDecades");
        } else {
            System.out.println("FAILED: Test6 getNumDecades");
        }

        //Test7: getRank
        expIntBob = 443; //at the starting decade
        actIntBob = bobRecord.getRank(0);
        System.out.println("\nexpected:" + expIntBob);
        System.out.println("actual:" + actIntBob);
        if (expIntBob == actIntBob) {
            System.out.println("passed: Test7 getRank");
        } else {
            System.out.println("FAILED: Test7 getRank");
        }

        //Test8: getRank
        expIntVerna = 184; //at 2 decades after starting decade
        actIntVerna = vernaRecord.getRank(2);
        System.out.println("\nexpected:" + expIntVerna);
        System.out.println("actual:" + actIntVerna);
        if (expIntVerna == actIntVerna) {
            System.out.println("passed: Test8 getRank");
        } else {
            System.out.println("FAILED: Test8 getRank");
        }

        //Test 9: mostPopularDecade
        expIntBob = 1930;
        actIntBob = bobRecord.mostPopularDecade();
        System.out.println("\nexpected:" + expIntBob);
        System.out.println("actual:" + actIntBob);
        if (expIntBob == actIntBob) {
            System.out.println("passed: Test9 mostPopularDecade");
        } else {
            System.out.println("FAILED: Test9 mostPopularDecade");
        }

        //Test10: mostPopularDecade
        expIntVerna = 1910;
        actIntVerna = vernaRecord.mostPopularDecade();
        System.out.println("\nexpected:" + expIntVerna);
        System.out.println("actual:" + actIntVerna);
        if (expIntVerna == actIntVerna) {
            System.out.println("passed: Test10 mostPopularDecade");
        } else {
            System.out.println("FAILED: Test10 mostPopularDecade");
        }

        //Test11: numDecadeRanked
        expIntBob = 9;
        actIntBob = bobRecord.numDecRanked();
        System.out.println("\nexpected:" + expIntBob);
        System.out.println("actual:" + actIntBob);
        if (expIntBob == actIntBob) {
            System.out.println("passed: Test11 numDecadeRanked");
        } else {
            System.out.println("FAILED: Test11 numDecadeRanked");
        }

        //Test12: numDecadeRanked
        expIntVerna = 6;
        actIntVerna = vernaRecord.numDecRanked();
        System.out.println("\nexpected:" + expIntVerna);
        System.out.println("actual:" + actIntVerna);
        if (expIntVerna == actIntVerna) {
            System.out.println("passed: Test12 numDecadeRanked");
        } else {
            System.out.println("FAILED: Test12 numDecadeRanked");
        }

        //Test13: allDecadeRanked
        Boolean expBoolBob = false;
        Boolean actBoolBob = bobRecord.allDecRanked();
        System.out.println("\nexpected:" + expBoolBob);
        System.out.println("actual:" + actBoolBob);
        if (expBoolBob == actBoolBob) {
            System.out.println("passed: Test13 allDecadeRanked");
        } else {
            System.out.println("FAILED: Test13 allDecadeRanked");
        }

        //Test14: allDecadeRanked
        Boolean expBoolVerna = false;
        Boolean actBoolVerna = vernaRecord.allDecRanked();
        System.out.println("\nexpected:" + expBoolVerna);
        System.out.println("actual:" + actBoolVerna);
        if (expBoolVerna == actBoolVerna) {
            System.out.println("passed: Test14 allDecadeRanked");
        } else {
            System.out.println("FAILED: Test14 allDecadeRanked");
        }

        //Test15: oneDecadeRanked
        expBoolBob = false;
        actBoolBob = bobRecord.oneDecRanked();
        System.out.println("\nexpected:" + expBoolBob);
        System.out.println("actual:" + actBoolBob);
        if (expBoolBob == actBoolBob) {
            System.out.println("passed: Test15 oneDecadeRanked");
        } else {
            System.out.println("FAILED: Test15 oneDecadeRanked");
        }

        //Test16: oneDecadeRanked
        expBoolVerna = false;
        actBoolVerna = vernaRecord.oneDecRanked();
        System.out.println("\nexpected:" + expBoolVerna);
        System.out.println("actual:" + actBoolVerna);
        if (expBoolVerna == actBoolVerna) {
            System.out.println("passed: Test16 oneDecadeRanked");
        } else {
            System.out.println("FAILED: Test16 oneDecadeRanked");
        }

        //Test17: alwaysMorePopular
        expBoolBob = false;
        actBoolBob = bobRecord.morePopular();
        System.out.println("\nexpected:" + expBoolBob);
        System.out.println("actual:" + actBoolBob);
        if (expBoolBob == actBoolBob) {
            System.out.println("passed: Test17 alwaysMorePopular");
        } else {
            System.out.println("FAILED: Test17 alwaysMorePopular");
        }

        //Test18: alwaysMorePopular
        expBoolVerna = false;
        actBoolVerna = vernaRecord.morePopular();
        System.out.println("\nexpected:" + expBoolVerna);
        System.out.println("actual:" + actBoolVerna);
        if (expBoolVerna == actBoolVerna) {
            System.out.println("passed: Test18 alwaysMorePopular");
        } else {
            System.out.println("FAILED: Test18 alwaysMorePopular");
        }

        //Test19: alwaysLessPopular
        expBoolBob = false;
        actBoolBob = bobRecord.lessPopular();
        System.out.println("\nexpected:" + expBoolBob);
        System.out.println("actual:" + actBoolBob);
        if (expBoolBob == actBoolBob) {
            System.out.println("passed: Test19 alwaysLessPopular");
        } else {
            System.out.println("FAILED: Test19 alwaysLessPopular");
        }

        //Test20: alwaysLessPopular
        expBoolVerna = true;
        actBoolVerna = vernaRecord.lessPopular();
        System.out.println("\nexpected:" + expBoolVerna);
        System.out.println("actual:" + actBoolVerna);
        if (expBoolVerna == actBoolVerna) {
            System.out.println("passed: Test20 alwaysLessPopular");
        } else {
            System.out.println("FAILED: Test20 alwaysLessPopular");
        }

        //Test21: toString
        expStrBob = "Bob\n" +
                "1900: 443\n" +
                "1910: 345\n" +
                "1920: 138\n" +
                "1930: 89\n" +
                "1940: 122\n" +
                "1950: 225\n" +
                "1960: 199\n" +
                "1970: 583\n" +
                "1980: 947\n" +
                "1990: 0\n" +
                "2000: 0\n";
        actStrBob = bobRecord.toString();
        System.out.print("\nexpected:\n" + expStrBob);
        System.out.println("actual:\n" + actStrBob);
        if (expStrBob.equals(actStrBob)) {
            System.out.println("passed: Test21 toString");
        } else {
            System.out.println("FAILED: Test21 toString");
        }

        //Test22: toString
        expStrVerna = "Verna\n" +
                "1910: 129\n" +
                "1920: 152\n" +
                "1930: 184\n" +
                "1940: 262\n" +
                "1950: 346\n" +
                "1960: 507\n" +
                "1970: 0\n" +
                "1980: 0\n" +
                "1990: 0\n";
        actStrVerna = vernaRecord.toString();
        System.out.print("\nexpected:\n" + expStrVerna);
        System.out.println("\nactual:\n" + actStrVerna);
        if (expStrVerna.equals(actStrVerna)) {
            System.out.println("passed: Test22 toString");
        } else {
            System.out.println("FAILED: Test22 toString");
        }
    }

    // main method. Driver for the whole program
    public static void main(String[] args) {
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    /* pre: namesDatabase != null
     * Ask user for options to perform on the given Names object.
     * Creates a Scanner connected to System.in.
     */
    private static void runOptions(Names namesDatabase) {
        Scanner keyboard = new Scanner(System.in);
        MenuChoices[] menuChoices = MenuChoices.values();
        MenuChoices menuChoice;
        do {
            showMenu();
            int userChoice = getChoice(keyboard) - 1;
            menuChoice = menuChoices[userChoice];
            if(menuChoice == MenuChoices.SEARCH) {
                //getMatches
                search(namesDatabase, keyboard);
                System.out.print("Enter a partial name: ");
                String partName = keyboard.nextLine();
                ArrayList<NameRecord> partResult = new ArrayList<>(namesDatabase.getMatches(partName));
                System.out.println("\nThere are " + partResult.size() + " matches for " + partName + ".\n");
                for (int element = 0; element < partResult.size(); element++) {
                    System.out.println(partResult.get(element).getName());
                }
            }
            else if (menuChoice == MenuChoices.ONE_NAME) {
                //getName method
                oneName(namesDatabase, keyboard);
                System.out.print("Enter a name: ");
                String name = keyboard.nextLine();
                NameRecord oneResult = namesDatabase.getName(name);
                String strOneRes = oneResult == null ?
                        name + " does not appear in any decade." :
                        oneResult.toString();
                System.out.println("\n" + strOneRes);
            }
            else if (menuChoice == MenuChoices.APPEAR_ONCE) {
                //rankedOnlyOneDecade method
                appearOnce(namesDatabase);
                ArrayList<String> onceResult = new ArrayList<>(namesDatabase.rankedOnlyOneDecade());
                System.out.println(onceResult.size() + " names appear in exactly one decade. The names are: ");
                for (int element = 0; element < onceResult.size(); element++) {
                    System.out.println(onceResult.get(element));
                }
            }
            else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
                //rankedEveryDecade method
                appearAlways(namesDatabase);
                ArrayList<String> everyResult = new ArrayList<>(namesDatabase.rankedEveryDecade());
                System.out.println(everyResult.size() + " names appear in every decade. The names are: ");
                for (int element = 0; element < everyResult.size(); element++) {
                    System.out.println(everyResult.get(element));
                }
            }
            else if (menuChoice == MenuChoices.ALWAYS_MORE) {
                //alwaysMorePopular method
                alwaysMore(namesDatabase);
                ArrayList<String> moreResult = new ArrayList<>(namesDatabase.alwaysMorePopular());
                System.out.println(moreResult.size() + " names are more popular in every decade.");
                for (int element = 0; element < moreResult.size(); element++) {
                    System.out.println(moreResult.get(element));
                }
            }
            else if (menuChoice == MenuChoices.ALWAYS_LESS) {
                //alwaysLessPopular method
                alwaysLess(namesDatabase);
                ArrayList<String> lessResult = new ArrayList<>(namesDatabase.alwaysLessPopular());
                System.out.println(lessResult.size() + " names are less popular in every decade.");
                for (int element = 0; element < lessResult.size(); element++) {
                    System.out.println(lessResult.get(element));
                }
            }
            else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
                // My method: alphabetical
                alphabetical(namesDatabase);
                ArrayList<String> alphaResult = new ArrayList<>(namesDatabase.alphabetical());
                System.out.println(alphaResult.size() + " names are spelled alphabetically.");
                for (int element = 0; element < alphaResult.size(); element++) {
                    System.out.println(alphaResult.get(element));
                }

            }
        } while(menuChoice != MenuChoices.QUIT);
        keyboard.close();
    }

    /* Display the names that are spelled alphabetically
     * pre: names != null
     * post: print out names that are spelled alphabetically
     */
    private static void alphabetical(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }

    }

    /* Create a Scanner and return connected to a File with the given name.
     * pre: fileName != null
     * post: Return a Scanner connected to the file or null
     * if the File does not exist in the current directory.
     */
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("\n***** ERROR IN READING FILE ***** ");
            System.out.println("Can't find this file "
                    + fileName + " in the current directory.");
            System.out.println("Error: " + e);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Be sure " + fileName + " is in this directory: ");
            System.out.println(currentDir);
            System.out.println("\nReturning null from method.");
            sc = null;
        }
        return sc;
    }

    /* Display the names that have appeared in every decade.
     * pre: n != null
     * post: print out names that have appeared in ever decade
     */
    private static void appearAlways(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }

    }

    /* Display the names that have appeared in only one decade.
     * pre: n != null
     * post: print out names that have appeared in only one decade
     */
    private static void appearOnce(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }

    }

    /* Display the names that have gotten more popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten more popular in each decade
     */
    private static void alwaysMore(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }

    }

    /* Display the names that have gotten less popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten less popular in each decade
     */
    private static void alwaysLess(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }

    }

    /* Display the data for one name or state that name has never been ranked.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: print out the data for n or a message that n has never been in the
     * top 1000 for any decade
     */
    private static void oneName(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }

    }

    /* Display all names that contain a substring from the user
     * and the decade they were most popular.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: display the data for each name.
     */
    private static void search(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }

    }

    /* Get choice from the user keyboard != null and is connected to System.in
     * return an int that is >= MenuChoices.SEARCH.ordinal()
     *  and <= MenuChoices.QUIT.ordinal().
     */
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // Add one due to zero based indexing of enums, but 1 based indexing of menu.
        final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
        while (choice < 1  || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    /* Ensure an int is entered from the keyboard.
     * pre: s != null and is connected to System.in
     * post: return the int typed in by the user.
     */
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // Show the user the menu.
    private static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("Enter 1 to search for names.");
        System.out.println("Enter 2 to display data for one name.");
        System.out.println("Enter 3 to display all names that appear in only "
                + "one decade.");
        System.out.println("Enter 4 to display all names that appear in all "
                + "decades.");
        System.out.println("Enter 5 to display all names that are more popular "
                + "in every decade.");
        System.out.println("Enter 6 to display all names that are less popular "
                + "in every decade.");
        System.out.println("Enter 7 to display all names that are spelled alphabetically.");
        System.out.println("Enter 8 to quit.");
        System.out.println();
    }

    /**
     * An enumerated type to hold the menu choices
     * for the NameSurfer program.
     */
    private static enum MenuChoices {
        SEARCH, ONE_NAME, APPEAR_ONCE, APPEAR_ALWAYS, ALWAYS_MORE,
        ALWAYS_LESS, STUDENT_SEARCH, QUIT;
    }
}