/*  Student information for assignment:
 *
 *  On my honor, Vicky Chen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Vicky Chen
 *  email address: vickyxrc@utexas.edu
 *  UTEID: vc23777
 *  Number of slip days used on this assignment: 1
 */

/* Place your experiment results here:
 *
 * Adding at end: ArrayList is slightly faster
 * ArrayList Big O: O(N): The time approximately doubles as N doubles. No shifting required,
 * so total time grows linearly with N.
 * N = 30000, total time:  0.0266
 * N = 60000, total time:  0.0439
 * N = 120000, total time:  0.0895
 * N = 240000, total time:  0.1678
 * N = 480000, total time:  0.3491
 * LinkedList Big O: O(N): The time approximately doubles as N doubles. No shifting, so
 * time grows linearly.
 * N = 30000, total time:  0.0286
 * N = 60000, total time:  0.0507
 * N = 120000, total time:  0.1038
 * N = 240000, total time:  0.2261
 * N = 480000, total time:  0.4613
 *
 *
 * Adding at front: LinkedList faster
 * ArrayList Big O: O(N^2): The time multiplies by 4 as time multiplies by two. (2^2=4).
 * Each addition the has time complexity of N because it requires the shifting of every
 * element back. Therefore for N additions, the big O is O(N^2)
 * N = 2000, total time:  0.0125
 * N = 4000, total time:  0.0431
 * N = 8000, total time:  0.1795
 * N = 16000, total time:  0.7795
 * N = 32000, total time:  3.4609
 * LinkedList Big O: O(N): Time approximately doubles when N doubles. No shifting required
 * for addition. Therefore, big O is linear with number of elements added
 * N = 10000, total time:  0.0080
 * N = 20000, total time:  0.0137
 * N = 40000, total time:  0.0292
 * N = 80000, total time:  0.0562
 * N = 160000, total time:  0.1185
 *
 *
 * Removing from front: LinkedList faster
 * ArrayList Big O: O(N^2): Time multiplies by 4 as time doubles: 2^2 is four times 1^2.
 * Removing element requires shifting N elements forward.
 * N = 2000, total time:  0.0123
 * N = 4000, total time:  0.0398
 * N = 8000, total time:  0.1592
 * N = 16000, total time:  0.7480
 * N = 32000, total time:  3.3692
 * LinkedList Big O: O(N): grows approximately linearsly with N. No shifting is
 * neccessary.
 * N = 5000, total time:  0.0016
 * N = 10000, total time:  0.0028
 * N = 20000, total time:  0.0083
 * N = 40000, total time:  0.0252
 * N = 80000, total time:  0.0621
 *
 *
 * Getting random: ArrayList is faster
 * ArrayList Big O: O(N): Time grows by a multiple of around 2 to 3 as N doubles.
 * This shows a linear growth. ArrayList can get element from list without traversal,
 * so getting one random element takes O(1) time complexity. Therefore, time complexity
 * grows linearly with N.
 * N = 10000, total time:  0.0118
 * N = 20000, total time:  0.0259
 * N = 40000, total time:  0.0605
 * N = 80000, total time:  0.1800
 * N = 160000, total time:  0.4974
 * LinkedList Big O: O(N^2): time grows quadratically as N grows. LinkedList
 * need traversal to get element from list, therefore the average case scenerio is
 * O(N) for getting one element, making the big O, O(N^2).
 * N = 1000, total time:  0.0249
 * N = 2000, total time:  0.1148
 * N = 4000, total time:  0.5101
 * N = 8000, total time:  2.1680
 * N = 16000, total time:  8.5777
 *
 *
 * Getting all using iterator: ArrayList faster
 * ArrayList Big O: O(N): time on average, doubles as N doubles.
 * N = 50000, total time:  0.0080
 * N = 100000, total time:  0.0112
 * N = 200000, total time:  0.0240
 * N = 400000, total time:  0.0544
 * N = 800000, total time:  0.1120
 * LinkedList Big O: O(N): time grows linearly proportional to N
 * N = 50000, total time:  0.0149
 * N = 100000, total time:  0.0320
 * N = 200000, total time:  0.0704
 * N = 400000, total time:  0.1427
 * N = 800000, total time:  0.2843
 *
 *
 * Getting all using get method:  ArrayList much faster
 * ArrayList Big O: O(N): Roughly doubles as time doubles. List can get element without
 * traversal.
 * N = 100000, total time:  0.0091
 * N = 200000, total time:  0.0192
 * N = 400000, total time:  0.0456
 * N = 800000, total time:  0.0959
 * N = 1600000, total time:  0.1782
 * LinkedList Big O: O(N^2): Grows quadratically as N grows. Requires traversal to get
 * specific element.
 * N = 1000, total time:  0.0270
 * N = 2000, total time:  0.1204
 * N = 4000, total time:  0.5866
 * N = 8000, total time:  2.5630
 * N = 16000, total time: 10.6550
 *
 */


import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;

public class LinkedListTester {

    public static void main(String[] args) {
        basicTests();
    }

    /*
     * Runs very basic tests on the LinkedList class for
     * CS314 assignment 4.
     */
    private static void basicTests() {
        System.out.println("****** BASIC TESTS *******\n");
        LL314<String> list = new LL314<>();

        // ADD TEST 1
        System.out.println("\nADD TEST 1");
        list.add("B");
        list.add("E");
        list.add("D");
        System.out.println("Expected result: [B, E, D]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[B, E, D]")) {
            System.out.println("PASSED ADD TEST 1");
        } else {
            System.out.println("FAILED ADD TEST 1");
        }

        // ADD TEST 2
        System.out.println("\nADD TEST 2");
        list.add("S");
        System.out.println("Expected result: [B, E, D, S]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[B, E, D, S]")) {
            System.out.println("PASSED ADD TEST 2");
        } else {
            System.out.println("FAILED ADD TEST 2");
        }

        // INSERT TEST 1
        System.out.println("\nINSERT TEST 1");
        list.insert(0, "U");
        System.out.println("Expected result: [U, B, E, D, S]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[U, B, E, D, S]")) {
            System.out.println("PASSED INSERT TEST 1");
        } else {
            System.out.println("FAILED INSERT TEST 1");
        }

        // INSERT TEST 2
        System.out.println("\nINSERT TEST 2");
        list.insert(2, "Y");
        System.out.println("Expected result: [U, B, Y, E, D, S]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[U, B, Y, E, D, S]")) {
            System.out.println("PASSED INSERT TEST 2");
        } else {
            System.out.println("FAILED INSERT TEST 2");
        }

        // SET TEST 1
        System.out.println("\nSET TEST 1");
        String oldVal = list.set(0, "R");
        System.out.println("Expected result: [R, B, Y, E, D, S]");
        System.out.println("Expected old value: U");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual old value: " + oldVal);
        if (oldVal.equals("U") && list.toString().equals("[R, B, Y, E, D, S]")) {
            System.out.println("PASSED SET TEST 1");
        } else {
            System.out.println("FAILED SET TEST 1");
        }

        // SET TEST 2
        System.out.println("\nSET TEST 2");
        oldVal = list.set(list.size()-1, "K");
        System.out.println("Expected result: [R, B, Y, E, D, K]");
        System.out.println("Expected old value: S");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual old value: " + oldVal);
        if (oldVal.equals("S") && list.toString().equals("[R, B, Y, E, D, K]")) {
            System.out.println("PASSED SET TEST 2");
        } else {
            System.out.println("FAILED SET TEST 2");
        }

        // GET TEST 1
        System.out.println("\nGET TEST 1");
        String val = list.get(0);
        System.out.println("Expected result: R");
        System.out.println("Actual result: " + val);
        if (val.equals("R")) {
            System.out.println("PASSED GET TEST 1");
        } else {
            System.out.println("FAILED GET TEST 1");
        }

        // GET TEST 2
        System.out.println("\nGET TEST 2");
        val = list.get(list.size()-1);
        System.out.println("Expected result: K");
        System.out.println("Actual result: " + val);
        if (val.equals("K")) {
            System.out.println("PASSED GET TEST 2");
        } else {
            System.out.println("FAILED GET TEST 2");
        }

        // REMOVE(POS) TEST 1
        System.out.println("\nREMOVE(POS) TEST 1");
        String removed = list.remove(0);
        System.out.println("Expected result: [R, B, Y, E, D, K]");
        System.out.println("Expected removed value: R");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual removed value: " + removed);
        if (removed.equals("R") && list.toString().equals("[B, Y, E, D, K]")) {
            System.out.println("PASSED REMOVE TEST 1");
        } else {
            System.out.println("FAILED REMOVE TEST 1");
        }

        // REMOVE(POS) TEST 2
        System.out.println("\nREMOVE(POS) TEST 2");
        removed = list.remove(list.size()-1);
        System.out.println("Expected result: [B, Y, E, D]");
        System.out.println("Expected removed value: K");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual removed value: " + removed);
        if (removed.equals("K") && list.toString().equals("[B, Y, E, D]")) {
            System.out.println("PASSED REMOVE TEST 2");
        } else {
            System.out.println("FAILED REMOVE TEST 2");
        }

        // REMOVE(OBJ) TEST 1
        System.out.println("\nREMOVE(OBJ) TEST 1");
        boolean wasRemoved = list.remove("Y");
        System.out.println("Expected result: [B, E, D]");
        System.out.println("Expected wasRemoved: true");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual wasRemoved: " + wasRemoved);
        if (wasRemoved && list.toString().equals("[B, E, D]")) {
            System.out.println("PASSED REMOVE(OBJ) TEST 1");
        } else {
            System.out.println("FAILED REMOVE(OBJ) TEST 1");
        }

        // REMOVE(OBJ) TEST 2
        System.out.println("\nREMOVE(OBJ) TEST 2");
        wasRemoved = list.remove("V");
        System.out.println("Expected result: [B, E, D]");
        System.out.println("Expected wasRemoved: false");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual wasRemoved: " + wasRemoved);
        if (!wasRemoved && list.toString().equals("[B, E, D]")) {
            System.out.println("PASSED REMOVEOBJ TEST 2");
        } else {
            System.out.println("FAILED REMOVEOBJ TEST 2");
        }

        // GETSUBLIST TEST 1
        System.out.println("\nGETSUBLIST TEST 1");
        list.add("O");
        IList<String> subList = list.getSubList(1, 3);
        System.out.println("Expected result: [E, D]");
        System.out.println("Actual result: " + subList.toString());
        if (subList.toString().equals("[E, D]")) {
            System.out.println("PASSED GETSUBLIST TEST 1");
        } else {
            System.out.println("FAILED GETSUBLIST TEST 1");
        }

        // GETSUBLIST TEST 2
        System.out.println("\nGETSUBLIST TEST 2");
        subList = list.getSubList(0, 4);
        System.out.println("Expected result: [B, E, D, O]");
        System.out.println("Actual result: " + subList.toString());
        if (subList.toString().equals("[B, E, D, O]")) {
            System.out.println("PASSED GETSUBLIST TEST 2");
        } else {
            System.out.println("FAILED GETSUBLIST TEST 2");
        }

        // SIZE TEST 1
        System.out.println("\nSIZE TEST 1");
        System.out.println("Expected result: 4");
        System.out.println("Actual result: " + list.size());
        if (list.size() == 4) {
            System.out.println("PASSED SIZE TEST 1");
        } else {
            System.out.println("FAILED SIZE TEST 1");
        }

        // SIZE TEST 2
        System.out.println("\nSIZE TEST 2");
        list.makeEmpty();
        System.out.println("Expected result: 0");
        System.out.println("Actual result: " + list.size());
        if (list.size() == 0) {
            System.out.println("PASSED SIZE TEST 2");
        } else {
            System.out.println("FAILED SIZE TEST 2");
        }

        // INDEXOF TEST 1
        System.out.println("\nINDEXOF TEST 1");
        list.add("L");
        list.add("S");
        list.add("N");
        System.out.println("Expected result: 0");  // First occurrence
        System.out.println("Actual result: " + list.indexOf("L"));
        if (list.indexOf("L") == 0) {
            System.out.println("PASSED INDEXOF TEST 1");
        } else {
            System.out.println("FAILED INDEXOF TEST 1");
        }

        // INDEXOF TEST 2
        System.out.println("\nINDEXOF TEST 2");
        System.out.println("Expected result: -1");
        System.out.println("Actual result: " + list.indexOf("E"));
        if (list.indexOf("E") == -1) {
            System.out.println("PASSED INDEXOF TEST 2");
        } else {
            System.out.println("FAILED INDEXOF TEST 2");
        }

        // INDEXOF(ITEM, POS) TEST 1
        System.out.println("\nINDEXOF(ITEM, POS) TEST 1");
        System.out.println("Expected result: 2");
        System.out.println("Actual result: " + list.indexOf("N", 1));
        if (list.indexOf("N", 1) == 2) {
            System.out.println("PASSED INDEXOF(ITEM, POS) TEST 1");
        } else {
            System.out.println("FAILED INDEXOF(ITEM, POS) TEST 1");
        }

        // INDEXOF(ITEM, POS) TEST 2
        System.out.println("\nINDEXOF(ITEM, POS) TEST 2");
        System.out.println("Expected result: -1");
        System.out.println("Actual result: " + list.indexOf("B", 2));
        if (list.indexOf("B", 2) == -1) {
            System.out.println("PASSED INDEXOF(ITEM, POS) TEST 2");
        } else {
            System.out.println("FAILED INDEXOF(ITEM, POS) TEST 2");
        }

        // MAKEEMPTY TEST 1
        System.out.println("\nMAKEEMPTY TEST 1");
        list.makeEmpty();
        System.out.println("Expected result: []");
        System.out.println("Actual result: " + list.toString());
        if (list.size() == 0 && list.toString().equals("[]")) {
            System.out.println("PASSED MAKEEMPTY TEST 1");
        } else {
            System.out.println("FAILED MAKEEMPTY TEST 1");
        }

        // MAKEEMPTY TEST 2
        System.out.println("\nMAKEEMPTY TEST 2");
        list.makeEmpty();
        System.out.println("Expected result: []");
        System.out.println("Actual result: " + list.toString());
        if (list.size() == 0 && list.toString().equals("[]")) {
            System.out.println("PASSED MAKEEMPTY TEST 2");
        } else {
            System.out.println("FAILED MAKEEMPTY TEST 2");
        }

        // ITERATOR TEST 1
        System.out.println("\nITERATOR TEST 1");
        list.add("A");
        list.add("B");
        list.add("C");
        Iterator<String> it = list.iterator();
        StringBuilder result = new StringBuilder();
        while (it.hasNext()) {
            result.append(it.next());
        }
        System.out.println("Expected result: ABC");
        System.out.println("Actual result: " + result.toString());
        if (result.toString().equals("ABC")) {
            System.out.println("PASSED ITERATOR TEST 1");
        } else {
            System.out.println("FAILED ITERATOR TEST 1");
        }

        // ITERATOR TEST 2
        System.out.println("\nITERATOR TEST 2");
        list.makeEmpty();
        it = list.iterator();
        System.out.println("Expected result: empty iterator");
        boolean notEmpty = it.hasNext();
        String itResult = "not empty";
        if (!notEmpty) {
            itResult = "empty iterator";
        }
        System.out.println("Actual result: " + itResult);
        if (itResult.equals("empty iterator")) {
            System.out.println("PASSED ITERATOR TEST 2");
        } else {
            System.out.println("FAILED ITERATOR TEST 2");
        }

        // ITERATORHASNEXT TEST 1
        System.out.println("\nITERATORHASNEXT TEST 1");
        list.add("A");
        it = list.iterator();
        System.out.println("Expected result: true");  // Has next element
        System.out.println("Actual result: " + it.hasNext());
        if (it.hasNext()) {
            System.out.println("PASSED ITERATORHASNEXT TEST 1");
        } else {
            System.out.println("FAILED ITERATORHASNEXT TEST 1");
        }

        // ITERATORHASNEXT TEST 2
        System.out.println("\nITERATORHASNEXT TEST 2");
        it.next();
        System.out.println("Expected result: false");  // No more elements
        System.out.println("Actual result: " + it.hasNext());
        if (!it.hasNext()) {
            System.out.println("PASSED ITERATORHASNEXT TEST 2");
        } else {
            System.out.println("FAILED ITERATORHASNEXT TEST 2");
        }

        // ITERATORNEXT TEST 1
        System.out.println("\nITERATORNEXT TEST 1");
        list.makeEmpty();
        list.add("O");
        list.add("R");
        it = list.iterator();
        String nextVal = it.next();
        System.out.println("Expected result: O");  // First element
        System.out.println("Actual result: " + nextVal);
        if (nextVal.equals("O")) {
            System.out.println("PASSED ITERATORNEXT TEST 1");
        } else {
            System.out.println("FAILED ITERATORNEXT TEST 1");
        }

        // ITERATORNEXT TEST 2
        System.out.println("\nITERATORNEXT TEST 2");
        nextVal = it.next();
        System.out.println("Expected result: R");  // First element
        System.out.println("Actual result: " + nextVal);
        if (nextVal.equals("R")) {
            System.out.println("PASSED ITERATORNEXT TEST 2");
        } else {
            System.out.println("FAILED ITERATORNEXT TEST 2");
        }

        // ITERATORREMOVE TEST 1
        System.out.println("\nITERATORREMOVE TEST 1");
        list.makeEmpty();
        list.add("D");
        list.add("B");
        list.add("W");
        it = list.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println("Expected result: [D, W]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[D, W]")) {
            System.out.println("PASSED ITERATORREMOVE TEST 1");
        } else {
            System.out.println("FAILED ITERATORREMOVE TEST 1");
        }

        // ITERATORREMOVE TEST 2
        System.out.println("\nITERATORREMOVE TEST 2");
        it.next();
        it.remove();
        System.out.println("Expected result: [D]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[D]")) {
            System.out.println("PASSED ITERATORREMOVE TEST 2");
        } else {
            System.out.println("FAILED ITERATORREMOVE TEST 2");
        }

        // REMOVERANGE TEST 1
        System.out.println("\nREMOVERANGE TEST 1");
        list.makeEmpty();
        list.add("R");
        list.add("A");
        list.add("I");
        list.add("N");
        list.removeRange(1, 3);
        System.out.println("Expected result: [R, N]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[R, N]")) {
            System.out.println("PASSED REMOVERANGE TEST 1");
        } else {
            System.out.println("FAILED REMOVERANGE TEST 1");
        }

        // REMOVERANGE TEST 2
        System.out.println("\nREMOVERANGE TEST 2");
        list.removeRange(1, 1);
        System.out.println("Expected result: [R, N]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[R, N]")) {
            System.out.println("PASSED REMOVERANGE TEST 2");
        } else {
            System.out.println("FAILED REMOVERANGE TEST 2");
        }

        // EQUALS TEST 1
        System.out.println("\nEQUALS TEST 1");
        LL314<String> list2 = new LL314<>();
        list2.add("R");
        list2.add("N");
        System.out.println(list2);
        System.out.println("Expected result: true");
        System.out.println("Actual result: " + list.equals(list2));
        if (list.equals(list2)) {
            System.out.println("PASSED EQUALS TEST 1");
        } else {
            System.out.println("FAILED EQUALS TEST 1");
        }

        // EQUALS TEST 2
        System.out.println("\nEQUALS TEST 2");
        list2.add("E");
        System.out.println("Expected result: false");
        System.out.println("Actual result: " + list.equals(list2));
        if (!list.equals(list2)) {
            System.out.println("PASSED EQUALS TEST 2");
        } else {
            System.out.println("FAILED EQUALS TEST 2");
        }

        // ADDFIRST TEST 1
        System.out.println("\nADDFIRST TEST 1");
        list.makeEmpty();
        list.addFirst("Q");
        System.out.println("Expected result: [Q]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[Q]")) {
            System.out.println("PASSED ADDFIRST TEST 1");
        } else {
            System.out.println("FAILED ADDFIRST TEST 1");
        }

        // ADDFIRST TEST 2
        System.out.println("\nADDFIRST TEST 2");
        list.addFirst("B");
        System.out.println("Expected result: [B, Q]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[B, Q]")) {
            System.out.println("PASSED ADDFIRST TEST 2");
        } else {
            System.out.println("FAILED ADDFIRST TEST 2");
        }

        // ADDLAST TEST 1
        System.out.println("\nADDLAST TEST 1");
        list.makeEmpty();
        list.addLast("H");
        System.out.println("Expected result: [H]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[H]")) {
            System.out.println("PASSED ADDLAST TEST 1");
        } else {
            System.out.println("FAILED ADDLAST TEST 1");
        }

        // ADDLAST TEST 2
        System.out.println("\nADDLAST TEST 2");
        list.addLast("I");
        System.out.println("Expected result: [H, I]");
        System.out.println("Actual result: " + list.toString());
        if (list.toString().equals("[H, I]")) {
            System.out.println("PASSED ADDLAST TEST 2");
        } else {
            System.out.println("FAILED ADDLAST TEST 2");
        }

        // REMOVEFIRST TEST 1
        System.out.println("\nREMOVEFIRST TEST 1");
        String firstRemoved = list.removeFirst();
        System.out.println("Expected result: [I]");
        System.out.println("Expected removed: H");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual removed: " + firstRemoved);
        if (firstRemoved.equals("H") && list.toString().equals("[I]")) {
            System.out.println("PASSED REMOVEFIRST TEST 1");
        } else {
            System.out.println("FAILED REMOVEFIRST TEST 1");
        }

        // REMOVEFIRST TEST 2
        System.out.println("\nREMOVEFIRST TEST 2");
        firstRemoved = list.removeFirst();
        System.out.println("Expected result: []");
        System.out.println("Expected removed: I");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual removed: " + firstRemoved);
        if (firstRemoved.equals("I") && list.toString().equals("[]")) {
            System.out.println("PASSED REMOVEFIRST TEST 2");
        } else {
            System.out.println("FAILED REMOVEFIRST TEST 2");
        }

        // REMOVELAST TEST 1
        System.out.println("\nREMOVELAST TEST 1");
        list.add("P");
        list.add("U");
        String lastRemoved = list.removeLast();
        System.out.println("Expected result: [P]");
        System.out.println("Expected removed: U");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual removed: " + lastRemoved);
        if (lastRemoved.equals("U") && list.toString().equals("[P]")) {
            System.out.println("PASSED REMOVELAST TEST 1");
        } else {
            System.out.println("FAILED REMOVELAST TEST 1");
        }

        // REMOVELAST TEST 2
        System.out.println("\nREMOVELAST TEST 2");
        lastRemoved = list.removeLast();
        System.out.println("Expected result: []");
        System.out.println("Expected removed: P");
        System.out.println("Actual result: " + list.toString());
        System.out.println("Actual removed: " + lastRemoved);
        if (lastRemoved.equals("P") && list.toString().equals("[]")) {
            System.out.println("PASSED REMOVELAST TEST 2");
        } else {
            System.out.println("FAILED REMOVELAST TEST 2");
        }
    }
}