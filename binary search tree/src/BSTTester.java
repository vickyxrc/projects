/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Vicky Chen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: vc23777
 *  email address: vickyxrc@utexas.edu
 *  TA name: Gracelyn Ray
 *  Number of slip days I am using: 2
 */

/*
Experiments 1:
Results for BST:
    Average time for 1000 insertions: 0.0010861199999999999
    Average height for 1000 insertions: 20
    Average size for 1000 insertions: 1000

    Average time for 2000 insertions: 0.0015144300000000002
    Average height for 2000 insertions: 23
    Average size for 2000 insertions: 1999

    Average time for 4000 insertions: 0.00161427
    Average height for 4000 insertions: 26
    Average size for 4000 insertions: 4000

    Average time for 8000 insertions: 0.0019082599999999997
    Average height for 8000 insertions: 28
    Average size for 8000 insertions: 8000

    Average time for 16000 insertions: 0.0049562
    Average height for 16000 insertions: 32
    Average size for 16000 insertions: 16000

    Average time for 32000 insertions: 0.011662660000000002
    Average height for 32000 insertions: 35
    Average size for 32000 insertions: 32000

    Average time for 64000 insertions: 0.02238308
    Average height for 64000 insertions: 37
    Average size for 64000 insertions: 63999

    Average time for 128000 insertions: 0.05572216000000001
    Average height for 128000 insertions: 40
    Average size for 128000 insertions: 127997

    Average time for 256000 insertions: 0.12494868
    Average height for 256000 insertions: 43
    Average size for 256000 insertions: 255993

    Average time for 512000 insertions: 0.32203638
    Average height for 512000 insertions: 47
    Average size for 512000 insertions: 511969

    Average time for 1024000 insertions: 1.0513713499999997
    Average height for 1024000 insertions: 49
    Average size for 1024000 insertions: 1023872

Results for TreeSet:
    Average time for 1000 insertions: 8.4904E-4

    Average time for 2000 insertions: 6.136900000000001E-4

    Average time for 4000 insertions: 8.5523E-4

    Average time for 8000 insertions: 0.00147957

    Average time for 16000 insertions: 0.0034156

    Average time for 32000 insertions: 0.007041169999999999

    Average time for 64000 insertions: 0.01771601

    Average time for 128000 insertions: 0.04309826

    Average time for 256000 insertions: 0.09909747

    Average time for 512000 insertions: 0.31086523

    Average time for 1024000 insertions: 0.7401637899999999

Answers:
  5. For each value of N, the minimum possible tree height is that of a completely balanced
  BST with a height of log(2)(N) - 1 (-1 because root does not count toward height). Thus,
  N Items    |Height
  1000        9
  2000        10
  4000        11
  8000        12
  16000       13
  32000       14
  64000       15
  128000      16
  256000      17
  512000      18
  1024000     19

  6. The average times for insertion for the TreeSet is slightly faster the average time for
  insertion for BST.
 */

/*
Experiement 2:
Results for BST:
    Average time for 1000 insertions: 0.0028425200000000003
    Average height for 1000 insertions: 999
    Average size for 1000 insertions: 1000

    Average time for 2000 insertions: 0.004827420000000001
    Average height for 2000 insertions: 1999
    Average size for 2000 insertions: 2000

    Average time for 4000 insertions: 0.02071552
    Average height for 4000 insertions: 3999
    Average size for 4000 insertions: 4000

    Average time for 8000 insertions: 0.0874604
    Average height for 8000 insertions: 7999
    Average size for 8000 insertions: 8000

    Average time for 16000 insertions: 0.342849
    Average height for 16000 insertions: 15999
    Average size for 16000 insertions: 16000

    Average time for 32000 insertions: 1.43613734
    Average height for 32000 insertions: 31999
    Average size for 32000 insertions: 32000

    Average time for 64000 insertions: 8.41753139
    Average height for 64000 insertions: 63999
    Average size for 64000 insertions: 64000

Results for TreeSet:
    Average time for 1000 insertions: 0.00154247

    Average time for 2000 insertions: 3.3446E-4

    Average time for 4000 insertions: 7.152199999999999E-4

    Average time for 8000 insertions: 9.3788E-4

    Average time for 16000 insertions: 0.00205389

    Average time for 32000 insertions: 0.0036574500000000005

    Average time for 64000 insertions: 0.01036547

Questions:
    5. The time complexity of my BST insertions appears to be O(N^2). Based on this, I predict that
    for n = 128,000, it will take approximately 34 seconds, for n = 256,000, it will be appriximately
    135 seconds, and for n = 512,000, it will take aprroximately 539 seconds.

    6. The time complexity of my TreeSet insertions appears to be O(NlogN). Based on this, I predict
    that for n = 128,000, it will take approximately 0.028 seconds, for n = 256,000, it will be appriximately
    0.0725 seconds, and for n = 512,000, it will take aprroximately 0.18 seconds.

    7. The times for the TreeSet is enormously faster than the times for BST. The reason for this may
    be due to the fact that since we are inserting integers of increasing value to the BST, for every
    insertion, the new integer has to be compared to every existing integer in the tree, making the
    tree extremely inefficient. TreeSets on the other hand, are self-balancing and thus able to
    maintain a balanced tree structure after each assertion, thus maintains a time compleixty of log(n)
    per assertion.
    Reference for TreeSet info: https://rameshfadatare.medium.com/java-treeset-2fe189d44e04#
 */


import java.util.Arrays;
import java.util.List;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * @param args Not used
     */
    public static void main(String[] args) {
//        for (int i = 0; i < 11; i++) {
//            int n = (int)Math.pow(2, i) * 1000;
//            Random r = new Random();
//            double time = 0;
//            int height = 0;
//            int size = 0;
//            for (int rep = 0; rep < 10; rep++) {
//                BinarySearchTree b = new BinarySearchTree();
//                Stopwatch s = new Stopwatch();
//                s.start();
//                for (int k = 0; k < n; k++) {
//                    b.add(new Integer(r.nextInt()));
//                }
//                s.stop();
//                time += s.time();
//                height += b.height();
//                size += b.size();
//            }
//            System.out.println("Average time for " + n + " insertions: " + (time / 10));
//            System.out.println("Average height for " + n + " insertions: " + (height / 10));
//            System.out.println("Average size for " + n + " insertions: " + (size / 10));
//            System.out.println();
//        }

//        for (int i = 0; i < 11; i++) {
//            int n = (int)Math.pow(2, i) * 1000;
//            Random r = new Random();
//            double time = 0;
//            for (int rep = 0; rep < 10; rep++) {
//                TreeSet<Integer> t = new TreeSet();
//                Stopwatch s = new Stopwatch();
//                s.start();
//                for (int k = 0; k < n; k++) {
//                    t.add(r.nextInt());
//                }
//                s.stop();
//                time += s.time();
//            }
//            System.out.println("Average time for " + n + " insertions: " + (time / 10));
//            System.out.println();
//        }

//        for (int i = 0; i < 7; i++) {
//            int n = (int) Math.pow(2, i) * 1000;
//            ;
//            double time = 0;
//            for (int rep = 0; rep < 10; rep++) {
//                BinarySearchTree b2 = new BinarySearchTree();
//                Stopwatch s = new Stopwatch();
//                s.start();
//                for (int k = 0; k < n; k++) {
//                    b2.iterativeAdd(k);
//                }
//                s.stop();
//                time += s.time();
//            }
//            System.out.println("Average time for " + n + " insertions: " + (time / 10));
//            System.out.println("Average height for " + n + " insertions: " + (n - 1));
//            System.out.println("Average size for " + n + " insertions: " + n);
//            System.out.println();
//        }

//        for (int i = 0; i < 7; i++) {
//            int n = (int) Math.pow(2, i) * 1000;
//            ;
//            double time = 0;
//            for (int rep = 0; rep < 10; rep++) {
//                TreeSet<Integer> t2 = new TreeSet<>();
//                Stopwatch s = new Stopwatch();
//                s.start();
//                for (int k = 0; k < n; k++) {
//                    t2.add(k);
//                }
//                s.stop();
//                time += s.time();
//            }
//            System.out.println("Average time for " + n + " insertions: " + (time / 10));
//            System.out.println();
//        }


        BinarySearchTree<Integer> bs = new BinarySearchTree<>();

        //add() Tests
        System.out.println("\nadd() Tests:");
        boolean result = bs.add(27);
        System.out.println("Expected: true, Actual: " + result);
        System.out.println("add() test 1 " + (result == true ? "passed" : "failed"));

        result = bs.add(27);
        System.out.println("Expected: false, Actual: " + result);
        System.out.println("add() test 2 " + (result == false ? "passed" : "failed"));

        //remove() Tests
        System.out.println("\nremove() Tests:");
        bs.add(65);
        bs.add(9);
        result = bs.remove(9);
        System.out.println("Expected: true, Actual: " + result);
        System.out.println("remove() test 1 " + (result == true ? "passed" : "failed"));

        result = bs.remove(49);
        System.out.println("Expected: false, Actual: " + result);
        System.out.println("remove() test 2 " + (result == false ? "passed" : "failed"));

        //isPresent() Tests
        System.out.println("\nisPresent() Tests:");
        result = bs.isPresent(27);
        System.out.println("Expected: true, Actual: " + result);
        System.out.println("isPresent() test 1 " + (result == true ? "passed" : "failed"));

        result = bs.isPresent(321);
        System.out.println("Expected: false, Actual: " + result);
        System.out.println("isPresent() test 2 " + (result == false ? "passed" : "failed"));

        //size() Tests
        System.out.println("\nsize() Tests:");
        int initialSize = bs.size();
        bs.add(33);
        int newSize = bs.size();
        System.out.println("Expected: " + (initialSize + 1) + ", Actual: " + newSize);
        System.out.println("size() test 1 " + (newSize == initialSize + 1 ? "passed" : "failed"));

        bs.remove(33);
        int finalSize = bs.size();
        System.out.println("Expected: " + initialSize + ", Actual: " + finalSize);
        System.out.println("size() test 2 " + (finalSize == initialSize ? "passed" : "failed"));

        //height() Tests
        System.out.println("\nheight() Tests:");
        BinarySearchTree<Integer> emptyTree = new BinarySearchTree<>();
        int height = emptyTree.height();
        System.out.println("Expected: -1, Actual: " + height);
        System.out.println("height() test 1 " + (height == -1 ? "passed" : "failed"));

        bs.add(7);
        bs.add(8);
        height = bs.height();
        System.out.println("Expected: >= 1, Actual: " + height);
        System.out.println("height() test 2 " + (height >= 1 ? "passed" : "failed"));

        //getAll() Tests
        System.out.println("\ngetAll() Tests:");
        List<Integer> actualList = bs.getAll();
        List<Integer> expectedList = Arrays.asList(7, 8, 27, 65);
        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        System.out.println("getAll() test 1 " + (actualList.equals(expectedList) ?
                "passed" : "failed"));

        BinarySearchTree<Integer> oneNode = new BinarySearchTree<>();
        oneNode.add(0);
        expectedList = Arrays.asList(0);
        actualList = oneNode.getAll();
        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        System.out.println("getAll() test 2 " + (actualList.equals(expectedList) ?
                "passed" : "failed"));

        //max() Tests
        System.out.println("\nmax() Tests:");
        int max = bs.max();
        System.out.println("Expected: 65, Actual: " + max);
        System.out.println("max() test 1 " + (max == 65 ? "passed" : "failed"));

        bs.add(834);
        max = bs.max();
        System.out.println("Expected: 834, Actual: " + max);
        System.out.println("max() test 2 " + (max == 834 ? "passed" : "failed"));

        //min() Tests
        System.out.println("\nmin() Tests:");
        int min = bs.min();
        System.out.println("Expected: 7, Actual: " + min);
        System.out.println("min() test 1 " + (min == 7? "passed" : "failed"));

        bs.add(2);
        min = bs.min();
        System.out.println("Expected: 2, Actual: " + min);
        System.out.println("min() test 2 " + (min == 2 ? "passed" : "failed"));

        //iterativeAdd() Tests
        System.out.println("\niterativeAdd() Tests:");
        BinarySearchTree<Integer> iTree = new BinarySearchTree<>();
        result = iTree.iterativeAdd(82);
        System.out.println("Expected: true, Actual: " + result);
        System.out.println("iterativeAdd() test 1 " + (result == true ? "passed" : "failed"));

        result = iTree.iterativeAdd(82);
        System.out.println("Expected: false, Actual: " + result);
        System.out.println("iterativeAdd() test 2 " + (result == false ? "passed" : "failed"));

        //get(k) Tests
        System.out.println("\nget() Tests:");
        int value = bs.get(0);
        System.out.println("Expected: 2, Actual: " + value);
        System.out.println("get() test 1 " + (value == 2 ? "passed" : "failed"));

        value = bs.get(2);
        System.out.println("Expected: 8, Actual: " + value);
        System.out.println("get() test 2 " + (value == 8 ? "passed" : "failed"));

        //getAllLessThan() Tests
        System.out.println("\ngetAllLessThan() Tests:");
        actualList = bs.getAllLessThan(15);
        expectedList = Arrays.asList(2, 7, 8);
        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        System.out.println("getAllLessThan() test 1 " + (actualList.equals(expectedList) ?
                "passed" : "failed"));

        actualList = bs.getAllLessThan(2);
        expectedList = Arrays.asList();
        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        System.out.println("getAllLessThan() test 2 " + (actualList.equals(expectedList) ?
                "passed" : "failed"));

        //getAllGreaterThan() Tests
        System.out.println("\ngetAllGreaterThan() Tests:");
        actualList = bs.getAllGreaterThan(15);
        expectedList = Arrays.asList(27, 65, 834);
        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        System.out.println("getAllGreaterThan() test 1 " + (actualList.equals(expectedList) ?
                "passed" : "failed"));

        actualList = bs.getAllGreaterThan(999);
        expectedList = Arrays.asList();
        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        System.out.println("getAllGreaterThan() test 2 " + (actualList.equals(expectedList) ?
                "passed" : "failed"));

        //numNodesAtDepth() Tests
        System.out.println("\nnumNodesAtDepth() Tests:");
        int count = bs.numNodesAtDepth(0);
        System.out.println("Expected: 1, Actual: " + count);
        System.out.println("numNodesAtDepth() test 1 " + (count == 1 ? "passed" : "failed"));

        count = bs.numNodesAtDepth(1);
        System.out.println("Expected: >= 1, Actual: " + count);
        System.out.println("numNodesAtDepth() test 2 " + (count >= 1 ? "passed" : "failed"));

    }
}