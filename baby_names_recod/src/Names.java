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
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


/**
* A collection of NameRecords.
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names {
    private ArrayList<NameRecord> names;
    private int startDecade;
    private int numDecades;

    /**
     * Construct a new Names object based on the data source the Scanner
     * sc is connected to. Assume the first two lines in the
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded
     * and are not part of the resulting Names object.
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names
     * and positioned at the start of the data source.
     * Credit for understanding line.split:
     * https://www.geeksforgeeks.org/split-string-java-examples/
     * Credit for using parseInt:
     * //https://www.geeksforgeeks.org/how-to-convert-string-to-int-in-java/
     */
    public Names(Scanner sc) {
        startDecade = Integer.parseInt(sc.nextLine());
        numDecades = Integer.parseInt(sc.nextLine());
        names = new ArrayList<NameRecord>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            //separate string into array of substrings separated by spaces
            String[] parsedData = line.split("\\s+");
            String name = parsedData[0];

            ArrayList<Integer> ranks = new ArrayList<>();
            //checks that the name has at least one rank within top 1000
            boolean allZeros = true;
            for (int i = 1; i < parsedData.length; i++) {
                if (Integer.parseInt(parsedData[i]) != 0) {
                    allZeros = false;
                }
                ranks.add(Integer.parseInt(parsedData[i]));
            }
            // Only make NameRecord for names with correct number of ranks and at least one non-zero rank
            if (parsedData.length - 1 == numDecades && !allZeros) {
                names.add(new NameRecord(name, startDecade, ranks, numDecades));
            }
        }

        Collections.sort(names);
    }

   /**
    * Returns an ArrayList of NameRecord objects that contain a
    * given substring, ignoring case.  The names must be in sorted order based
    * on the names of the NameRecords.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list.
    * Credit for using iterator: //https://www.geeksforgeeks.org/iterators-in-java/
    */
   public ArrayList<NameRecord> getMatches(String partialName) {
       if ((partialName == null) || (partialName.isEmpty())) {
           throw new IllegalArgumentException("Parameter cannot be null or empty");
       }
       ArrayList<NameRecord> matches = new ArrayList<>();
       Iterator<NameRecord> namesIt = names.iterator();
       while (namesIt.hasNext()) {
           NameRecord currentRecord = namesIt.next();
           if (currentRecord.getName().toLowerCase().contains(partialName.toLowerCase())) {
               matches.add(currentRecord);
           }
       }
       return matches;

   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted
    * order based on the name of the NameRecords.
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> rankedEveryDecade() {
        ArrayList<String> topNames = new ArrayList<>();
        for (int element = 0; element < names.size(); element++) {
            if (names.get(element).allDecRanked()) {
                topNames.add(names.get(element).getName());
            }
        }
        return topNames;
   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better in exactly one decade. The Strings must be in sorted
    * order based on the name of the NameRecords.
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> rankedOnlyOneDecade() {
       ArrayList<String> result = new ArrayList<>();
       for (int element = 0; element < names.size(); element++) {
           if (names.get(element).oneDecRanked()) {
               result.add(names.get(element).getName());
           }
       }
       return result;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted
    * order based on the name of the NameRecords.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> alwaysMorePopular() {
       ArrayList<String> result = new ArrayList<>();
       for (int element = 0; element < names.size(); element++) {
           if (names.get(element).morePopular()) {
               result.add(names.get(element).getName());
           }
       }
       return result;

   }

   /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based
    * on the name of the NameRecords.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> alwaysLessPopular() {
       ArrayList<String> result = new ArrayList<>();
       for (int element = 0; element < names.size(); element++) {
           if (names.get(element).lessPopular()) {
               result.add(names.get(element).getName());
           }
       }
       return result;
   }

   /**
    * Return the NameRecord in this Names object that matches the given String ignoring case.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    * Credit for using iterator: //https://www.geeksforgeeks.org/iterators-in-java/
    */
   public NameRecord getName(String name) {
       if (name == null) {
           throw new IllegalArgumentException("The parameter name cannot be null");
	   }
       Iterator<NameRecord> namesIt = names.iterator();
       while (namesIt.hasNext()) {
           NameRecord currentRecord = namesIt.next();
           if (currentRecord.getName().equalsIgnoreCase(name)) {
               return currentRecord;
           }
       }
       return null;
   }

    /**
     * Pre: none
     * @return A list of all names that if spelled alphabetically.
     * The list is sorted in ascending order. If there are no NameRecords
     * that meet this criteria, an empty list is returned.
     *
     */
   public ArrayList<String> alphabetical() {
       ArrayList<String> result = new ArrayList<>();
       for (int element = 0; element < names.size(); element++) {
           String name = names.get(element).getName().toLowerCase();
           Boolean alphabetical = true;
           for (int lett = 1; lett < name.length(); lett++) {
               if (!(name.charAt(lett) >= name.charAt(lett - 1))) {
                   alphabetical = false;
               }
           }
           if (alphabetical) {
               result.add(names.get(element).getName());
           }
       }
       return result;
   }
}