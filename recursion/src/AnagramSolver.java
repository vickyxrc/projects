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
 *  email address: vickyxrc@utexas.edu
 *
 *  Student 2 Name: Laila Olvera
 *  UTEID: lo6293
 *  email address: luscinia1074@gmail.com
 *
 *  Grader name: Gracelynn Ray
 *  Section number: 50720
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnagramSolver {
    private static final int UNLIMITED = 0;

    private Map<String, LetterInventory> dictionary;
    private ArrayList<String> validWords = new ArrayList<>();
    private int maxWords;

    /*
     *
     * pre: list != null
     * post:
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary cannot be null");
        }
        this.dictionary = new HashMap<>();
        for (String word : dictionary) {
            if (!this.dictionary.containsKey(word)) {
                this.dictionary.put(word, new LetterInventory(word));
            }
        }
    }

    /*
     * Return a list of anagrams that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     *
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
     * post:
     * @param s:
     * @param maxWords:
     * @return:
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        if (s == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        for (int character = 0; character < s.length(); character++) {
            if (s.toLowerCase().charAt(character) < 'a'
                    && s.toLowerCase().charAt(character) > 'z') {
                throw new IllegalArgumentException("Word must have at least one English letter");
            }
        }
        LetterInventory targetWord = new LetterInventory(s);
        //Only pass substrings of target word into findAnagram
        validWords.clear();
        for (Map.Entry<String, LetterInventory> entry : dictionary.entrySet()) {
            if (targetWord.subtract(entry.getValue()) != null) {
                validWords.add(entry.getKey());
            }
        }
        List<String> anagram = new ArrayList<>();
        List<List<String>> anagrams = new ArrayList<>();
        if (maxWords == UNLIMITED) {
            this.maxWords = Integer.MAX_VALUE;
        }
        this.maxWords = maxWords;
        findAnagrams(targetWord, 0, anagram, anagrams);
        Collections.sort(anagrams, new AnagramComparator());
        return anagrams;
    }

    /**
     * A private recusive helper method for getAnagrams that takes in a target word and creates a
     * list of anagrams from our dictionary
     * @param targetWord Word or phrase to find anagrams for
     * @param index keeps track of which LetterInventory in the dictionary we are on
     * @param anagram list of strings that makes an anagram
     * @param anagrams list of anagrams we have found
     *
     * Pre: none
     * @return the list of all anagrams we found for the target word
     */

    private List<List<String>> findAnagrams(LetterInventory targetWord, int index,
                                                 List<String> anagram, List<List<String>> anagrams) {
        if (targetWord.isEmpty()) {
            List<String> sortedAnagram = new ArrayList<>(anagram);
            Collections.sort(sortedAnagram);
            anagrams.add(sortedAnagram);
            return anagrams;
        }
        if (maxWords != UNLIMITED && anagram.size() == maxWords) {
            return anagrams;
        }
        //recurse
        for (int ind = index; ind < validWords.size(); ind++) {
            LetterInventory remainder = targetWord.subtract(dictionary.get(validWords.get(ind)));
            //if remainder is null, just keep going to the next word.
            if (remainder != null) {
                //chose
                anagram.add(validWords.get(ind));
                //explore
                findAnagrams(remainder, ind, anagram, anagrams);
                //backtrack: remove last element to explore other options
                anagram.remove(anagram.size() - 1);
            }
        }
        return anagrams;
    }

    private static class AnagramComparator implements Comparator<List<String>> {

        @Override
        public int compare(List<String> anagrams1, List<String> anagrams2) {
            final int SAME = 0;
            // sort by fewest words first
            int result = Integer.compare(anagrams1.size(), anagrams2.size());
            if (result != SAME) {
                return result;
            }
            // if same number of words, get the first word of the list
            // and sort that lexicographically

            for (int index = 0; index < anagrams1.size(); index++) {
                result = anagrams1.get(index).compareTo(anagrams2.get(index));
                if (result != SAME) {
                    return result;
                }
            }

            return SAME; // literally the same anagram how the fuck did we get here?
        }
    }
}