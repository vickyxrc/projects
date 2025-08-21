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

// imports
import java.util.Arrays;

public class LetterInventory {

    // instance variables
    private int size;
    private int[] charCounts;
    private String originalWord;

    // class constants
    private static final int ALPHABET_SIZE = 26; // characters 'a' through 'z' (all lowercase)
    private static final int EMPTY = 0;

    /*
     * Constructor that creates a new LetterInventory object that accepts a non-null String
     * representing a word. The constructor will store the counts of each letter (case-sensitive)
     * in the word
     *
     * pre: word != null
     * post: a LetterInventory object is created with the counts of each letter in word
     *       stored in the object
     * @param word: String of a word to create a LetterInventory object
     */
    public LetterInventory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null.");
        }

        word = word.toLowerCase();
        charCounts = new int[ALPHABET_SIZE];
        StringBuilder tempWord = new StringBuilder();

        for (int index = 0; index < word.length(); index++) {
            char letter = word.charAt(index);
            if (('a' <= letter && letter <= 'z')) {
                charCounts[letter - 'a']++; // increment the count of the letter at the zero-based index
                size++;
                tempWord.append(letter);
            }
        }
        originalWord = tempWord.toString();
    }

    /*
     * Returns the frequency of a given letter in the LetterInventory object
     *
     * pre: char needs to be an English letter (upper or lowercase)
     * post: returns the frequency of the given letter in the LetterInventory object
     * @param letter: character of the letter to find the frequency of
     * @return: the frequency of the given letter in the LetterInventory object
     */
    public int get(char letter) {
        if (('a' <= letter && letter <= 'z') && ('A' <= letter && letter <= 'Z')) {
            throw new IllegalArgumentException("Letter must be an English letter.");
        }
        letter = Character.toLowerCase(letter);
        return charCounts[letter - 'a'];
    }

    /*
     * Returns the size of the total number of letters in this LetterInventory object
     *
     * pre: none
     * post: returns total number of letters in this LetterInventory object
     * @return: the total number of letters in this LetterInventory object
     *
     */
    public int size() {
        return size;
    }

    /*
     * Determines if the LetterInventory object is empty
     *
     * pre: none
     * post: returns boolean value of whether the LetterInventory object is empty
     * @return: returns true if the LetterInventory object is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == EMPTY;
    }

    /*
     * Returns a String representation of this LetterInventory object. All letters are listed
     * in alphabetical order and lowercase. For example, if the LetterInventory object is "Tall",
     * the method returns "allt". If the LetterIventory object is "Isabelle M. Scott!!", the
     * method returns "abceeillmssott"
     *
     * pre: none
     * post: a String representation of this LetterInventory object
     * @return: returns a String representation of this LetterInventory object
     */
    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        int index = 0;
        makeWordString(charCounts, index, word);
        return word.toString();
    }

    /*
     * Adds the frequencies of the letters in this LetterInventory object to the frequencies of
     * another LetterInventory. Neither the calling object nor the explicit parameter
     *       are altered as a result of this call.
     *
     * pre: other != null
     * post: a new LetterInventory created after adding the frequencies of the other
     *       LetterInventory object.
     * @param other: LetterInventory object to add to the calling LetterInventory object
     * @return: returns a new LetterInventory created by adding the frequencies from the
     *          calling LetterInventory object to the letters in the explicit parameter
     */
    public LetterInventory add(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("Other LetterInventory object cannot be null.");
        }
        return new LetterInventory(this.originalWord + other.originalWord);
    }

    /*
     * Subtracts the letter frequencies of this LetterInventory from another LetterInventory object.
     * Neither the calling object nor the explicit parameter are altered as a result of this call.
     * If the subtraction results in a negative frequency, the method returns null.
     * word1.subtract(word2) is analogous to word1 - word2.
     *
     * pre: other != null
     * post: a new LetterInventory created after subtracting the frequencies of the other
     *       LetterInventory object.
     * @param other: LetterInventory object to subtract from the calling LetterInventory object
     * @return: returns a new LetterInventory created by subtracting the frequencies from the
     *          calling LetterInventory object to the letters in the explicit parameter. If the
     *          the subtraction results in a negative frequency, the method returns null
     */
    public LetterInventory subtract(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("Other LetterInventory object cannot be null.");
        } if (this.size < other.size) {
            return null;
        }

        int[] thisCharCounts = Arrays.copyOf(this.charCounts, this.charCounts.length);
        int[] otherCharCounts = Arrays.copyOf(other.charCounts, other.charCounts.length);
        int[] newCharCounts = new int[ALPHABET_SIZE];

        for (int index = 0; index < ALPHABET_SIZE; index++) {
            newCharCounts[index] = thisCharCounts[index] - otherCharCounts[index];
            if (newCharCounts[index] < EMPTY) {
                return null;
            }
        }

        StringBuilder word = new StringBuilder();
        int index = 0;
        makeWordString(newCharCounts, index, word);
        return new LetterInventory(word.toString());
    }

    /*
     * Determines if two LetterInventory objects are equal if the letter frequency for each letter
     * in the alphabet is the same
     *
     * pre: other != null
     * post: boolean value of the equality of the two LetterInventory objects
     * @param other: LetterInventory object to compare to the calling LetterInventory object
     * @return:
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            throw new IllegalArgumentException("Other LetterInventory object cannot be null.");
        } if (this.getClass() != other.getClass()) {
            return false;
        }
        return Arrays.equals(this.charCounts, ((LetterInventory) other).charCounts);
    }


    /*
     * Helper method that creates a String representation based on the counts of each letter
     * in LetterInventory's charCounts array.
     *
     * pre: counts[index] > 0 (can be zero, but no character will be printed out)
     * post: a String of the letters in the charCounts array in alphabetical order
     * @param counts: array of the character counts for each letter/character in
     *                the LetterInventory object
     * @param index: index of the counts array
     * @param word: StringBuilder object to append the letters to
     * @return: recursively returnes itself until the index is greater than the alphabet size,
     *          then returns the String representation of the letters in the charCounts array
     */
    private String makeWordString(int[] counts, int index, StringBuilder word) {
        final int INCREASE_INDEX = 1;

        if (index >= ALPHABET_SIZE) {
            return word.toString();
        } if (index < ALPHABET_SIZE && counts[index] > EMPTY) {
            int count = counts[index];
            char letter = (char) (index + 'a');
            appendLetters(word, count, letter);
        }
        return makeWordString(counts, index + INCREASE_INDEX, word);
    }

    /*
     * Appends the given letter to the word N number of times
     *
     * @param word: StringBuilder object to append the letters to
     * @param count: times the method will recurse to append the letter to the word
     * @param letter: character (letter) to be appended
     * @return: returns a StringBuilder object until the letter has been appended N times
     */
    private StringBuilder appendLetters(StringBuilder word, int count, char letter) {
        final int DECREASE_COUNT = 1;

        if (count == EMPTY) {
            return word;
        } else {
            word.append(letter);
            return appendLetters(word, count - DECREASE_COUNT, letter);
        }
    }
}