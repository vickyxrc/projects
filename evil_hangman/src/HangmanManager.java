/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:
 *  email address:
 *  UTEID:
 *  Section 5 digit ID:
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

// add imports as necessary

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */

public class HangmanManager {
    // instance variables / fields
    private Set<String> ogDictionary;
    private Set<String> currDictionary;
    private ArrayList<Character> guessedLett;
    private int wordLen;
    private int numWrong; //number of wrong guesses left
    private String pattern;
    private HangmanDifficulty level;
    private boolean secondHardest;
    private int round;
    public boolean debugOn;


    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     *
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     * https://www.geeksforgeeks.org/hashset-vs-treeset-in-java/
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Word set cannot be empty or null");
        }
        this.debugOn = debugOn;
        ogDictionary =  new HashSet<>(words);
        currDictionary = new HashSet<>();
        guessedLett = new ArrayList<>();
        if (debugOn) {
            System.out.println("New Hangmanager initialized");
        }
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     *
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Word set cannot be empty or null");
        }
        ogDictionary =  new HashSet<>(words);
        currDictionary = new HashSet<>();
        guessedLett = new ArrayList<>();

    }

    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     *
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given
     *         length
     */
    public int numWords(int length) {
        return ogDictionary.size();
    }

    /**
     * Get for a new round of Hangman. Think of a round as a complete game of
     * Hangman.
     *
     * @param wordLen the length of the word to pick this time.
     *                numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the player loses
     *                   the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
        if (wordLen < 1 || numGuesses < 1) {
            throw new IllegalArgumentException
                    ("The word length and number of guesses must be at least 1");
        }
        this.wordLen = wordLen;
        this.numWrong = numGuesses;
        currDictionary.clear();
        guessedLett.clear();
        for (String word: ogDictionary ) {
            if (word.length() == wordLen) {
                currDictionary.add(word);
            }
        }
        pattern = "";
        for (int len = 0; len < wordLen; len++) {
            pattern += "-";
        }
        level = diff;
        secondHardest = false;
        round = 0;
        if (debugOn) {
            boolean wordLenMatch = this.wordLen == wordLen;
            boolean numWrongMatch = numWrong == numGuesses;
            boolean levelMatch = level == diff;
            if (wordLenMatch && numWrongMatch && levelMatch) {
                System.out.println("\nA new round has been set up with the instance " +
                        "variables matching respective parameters");
            }
        }
    }

    /**
     * The number of words still possible (live) based on the guesses so far.
     * Guesses will eliminate possible words.
     *
     * @return the number of words that are still possibilities based on the
     * original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        if (debugOn) {
            System.out.println("The current number of words in the dictionary is "
                    + currDictionary.size());
        }
        return currDictionary.size();
    }

    /**
     * Get the number of wrong guesses the user has left in this round (game)
     * of Hangman.
     *
     * @return the number of wrong guesses the user has left in this round
     *         (game) of Hangman.
     */
    public int getGuessesLeft() {
        return numWrong;
    }

    /**
     * Return a String that contains the letters the user has guessed so far
     * during this round. The characters in the String are in alphabetical
     * order. The String is in the form [let1, let2, let3, ... letN].
     * For example: [a, c, e, s, t, z]
     *
     * @return a String that contains the letters the user has guessed so far
     *         during this round.
     */
    public String getGuessesMade() {
        Collections.sort(guessedLett);
        return guessedLett.toString();
    }

    /**
     * Check the status of a character.
     *
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman,
     *         false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        return guessedLett.contains(guess);
    }

    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or
     * guessed) characters and the actual character for "correctly guessed"
     * characters.
     *
     * @return the current pattern.
     */
    public String getPattern() {
        if (debugOn) {
            System.out.println("\nThe current pattern is " + pattern);
        }
        return pattern;
    }

    /**
     * Update the game status (pattern, wrong guesses, word list), based on
     * the given guess.
     *
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     *         words in each of the new patterns. The return value is for
     *         testing and debugging purposes.
     * https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        guess = Character.toLowerCase(guess);
        if (alreadyGuessed(guess)) {
            throw new IllegalArgumentException("This character has already been guessed");
        }
        guessedLett.add(guess);
        Map<String, ArrayList<String>> patternFreq = new HashMap<>();
        for (String word: currDictionary) {
            String newPattern = getPatternofWord(word, guess);
            if (patternFreq.containsKey(newPattern)) {
                patternFreq.get(newPattern).add(word);
            }
            else {
                ArrayList<String> wordList = new ArrayList<>();
                wordList.add(word);
                patternFreq.put(newPattern, wordList);
            }
        }
        round ++;
        updateDictionary(patternFreq);
        TreeMap<String, Integer> retFreq = new TreeMap<>();
        for (String pattern: patternFreq.keySet()) {
            retFreq.put(pattern, patternFreq.get(pattern).size());
        }
        if (debugOn) {
            System.out.println("The resulting patterns and number of words in each pattern:"
                + "\n" + retFreq);
        }
        return retFreq;
    }

    /**
     * @param word current word in the current dictionary to be analyzed for its pattern
     * @param guess the letter guessed by the player
     * @return String of the pattern of the word.
     */
    public String getPatternofWord(String word, char guess) {
        StringBuilder newPattern = new StringBuilder();
        for (int i = 0; i < wordLen; i++) {
            if (word.charAt(i) == guess) {
                newPattern.append(guess);
            }
            else {
                newPattern.append(pattern.charAt(i));
            }
        }
        if (debugOn) {
            System.out.println("\nThe pattern of this word is " + newPattern);
        }
        return newPattern.toString();
    }

    /**
     * Determines if the second hardest pattern should be used based on difficulty level and round
     * number.
     * Only called by the method updateDictionary().
     * For EASY difficulty: uses second hardest pattern every 2 rounds
     * For MEDIUM difficulty: uses second hardest pattern every 4 rounds
     * For HARD difficulty: always uses hardest pattern
     * pre: none
     * @return true if the game difficulty is MEDIUM and the round number is a multiple of 4
     * OR if the game difficulty is EASY and the round number is a multiple of 2.
     * False otherwise.
     */
    private void setSecondHardest() {
        boolean everyTwoRound = round % 2 == 0;
        boolean everyFourRound = round % 4 == 0;
        if ((level == HangmanDifficulty.EASY) && everyTwoRound) {
            secondHardest = true;
            if (debugOn) {
                System.out.println
                        ("\nEasy difficulty will get the second hardest pattern this round");
            }
        }
        else if ((level == HangmanDifficulty.MEDIUM) && everyFourRound) {
            secondHardest = true;
            System.out.println
                    ("\nMedium difficulty will get the second hardest pattern this round");
        }
        else {
            secondHardest = false;
        }
    }

    /**
     * Reference for map iteration: Reference for map iteration:
     * https://www.geeksforgeeks.org/iterate-map-java/
     * @param patternFreq The map of patterns and their corresponding wordlist created in
     * makeGuess().
     * pre: none
     * post: the pattern is updated to the hardest or second hardest pattern chosen based on game
     * difficulty,
     * round number, the number of words in the pattern, number of letters revealed, and its
     * lexigraphic value.
     *
     */
    private void updateDictionary(Map<String, ArrayList<String>> patternFreq) {
        String oldPat = pattern;
        ArrayList <String> hardestList = new ArrayList<>(); String hardestPattern = "";
        ArrayList <String> secHardestLst = new ArrayList<>(); String secHardestPat = "";
        for (String currPattern: patternFreq.keySet()) {
            ArrayList<String> currList = patternFreq.get(currPattern);
            //Update hardest if current pattern has more words,
            //or if tie, current pattern returns true in tieBreaker
            if (currList.size() > hardestList.size() || (currList.size() == hardestList.size()
                    && tieBreaker(currPattern, hardestPattern))) {
                secHardestLst = new ArrayList<> (hardestList); secHardestPat = hardestPattern;
                hardestList = new ArrayList<>(currList); hardestPattern = currPattern;
            }
            //Update secondHardest if current pattern has more words,
            //or if tie, current pattern returns true in tieBreaker
            else if (currList.size() > secHardestLst.size() ||
                    (currList.size() == secHardestLst.size()
                    && tieBreaker(currPattern, secHardestPat))) {
                secHardestLst = currList; secHardestPat = currPattern;
            }
        }
        setSecondHardest();
        //if only one pattern in patternFreq, secHardestLst and secHardestPat will be empty,
        //so always update with hardestList and hardestPattern
        currDictionary = secondHardest && !secHardestLst.isEmpty()?
                new HashSet<>(secHardestLst): new HashSet<>(hardestList);
        pattern = secondHardest && !secHardestPat.isEmpty()? secHardestPat: hardestPattern;
        if (pattern.equals(oldPat)) {
            numWrong--;
        }
    }

    /**
     * Breaks tie between two patterns with the same number of words
     * @param currPattern The current pattern that is being analyzed for if it is the hardest
     * pattern.
     * @param hardestPattern The current hardest pattern
     * @return true if currPattern is the harder pattern (less letter revealed, or lexigraphically
     * less if letter revealed is tie). Otherwise, return false.
     */
    private boolean tieBreaker(String currPattern, String hardestPattern) {
        int currRevealed = 0; //number of dashes (-) revealed of current pattern in map entry loop
        int hardestRevealed = 0; //number of dashes revealed of the pattern of the hardest word list
        for (int lett = 0; lett < wordLen; lett++) {
            if (currPattern.charAt(lett) != '-') {
                currRevealed++;
            }
            if (hardestPattern.charAt(lett) != '-') {
                hardestRevealed++;
            }
        }
        //compare lexigraphically if letter revealed is tie
        if (currRevealed != hardestRevealed) {
            return currRevealed < hardestRevealed;
        }
        return currPattern.compareTo(hardestPattern) < 0;
    }

    /**
     * Return the secret word this HangmanManager finally ended up picking for
     * this round. If there are multiple possible words left one is selected
     * at random.
     * pre: numWordsCurrent() > 0
     *
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        if (numWordsCurrent() < 1) {
            throw new IllegalStateException("Number of current words cannot be 0");
        }
        ArrayList<String> words = new ArrayList<>(currDictionary);
        return words.get((int)(Math.random() * words.size()));
    }
}