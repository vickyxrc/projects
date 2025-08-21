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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
    private static final int EMPTY_TREE = -1;
    private static final int EMPTY = 0;
    public static final int LESS_THAN = -1;
    public static final int GREATER_THAN = 1;


    private BSTNode<E> root;
    private int size;


    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     *  Reference: code from lecture
     */
    public boolean add(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }
        int oldSize = size;
        root = addHelper(value, root);
        return size != oldSize;
    }

    /**
     * recursive helper method for add()
     * pre: none. checked by add()
     * post: Add value to this tree if not already present. returns the changed/unchanged
     * Binary Search Tree
     * @param value element to add
     * @param node root of the tree
     */
    private BSTNode<E> addHelper(E value, BSTNode<E> node) {
        //fell off the tree. add new node
        if (node == null) {
            size++;
            return new BSTNode<E>(value);
        }
        if (value.compareTo(node.getData()) == LESS_THAN) {
            node.left = addHelper(value, node.left);
        } else if (value.compareTo(node.getData()) == GREATER_THAN) {
            node.right = addHelper(value, node.right);
        }
        return node;
    }


    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     *  reference: code from lecture
     */
    public boolean remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        int oldSize = size;
        root = removeHelper(value, root);
        return size != oldSize;
    }

    /**
     * recursive helper method for remove()
     * pre: none. checked by remove()
     * post: remove value from the tree if present. returns the changed/unchanged
     * Binary Search Tree
     * @param value element to remove
     * @param node root of the tree
     */
    private BSTNode<E> removeHelper(E value, BSTNode<E> node) {
        //fell off tree / did not find value
        if (node == null) {
            return null;
        }
        //find our node to remove
        if (value.compareTo(node.data) == LESS_THAN) {
            node.left = removeHelper(value, node.left);
        }
        else if (value.compareTo(node.data) == GREATER_THAN) {
            node.right = removeHelper(value, node.right);
        }
        //node found. rearrange children
        else {
            size--;
            //if node is a leaf node
            if (node.left == null && node.right == null) {
                return null;
            }
            //if node only has left child
            else if (node.right == null) {
                return node.left;
            }
            //if node only has right child
            else if (node.left == null) {
                return node.right;
            }
            //if node has two children
            else {
                //randomly pick left or right descendant to replace the node with
                BSTNode<E> childNode = new Random().nextBoolean() ? node.left : node.right;
                if (childNode.equals(node.left)) {
                    //replaces node with the largest value among its left descendants
                    node.data = maxHelper(childNode);
                    node.left = removeHelper(node.data, node.left);
                }
                else {
                    //replaces node with the smallest value among its right descendants
                    node.data = minHelper(childNode);
                    node.right = removeHelper(node.data, node.right);

                }
                size ++; //make up for size decremental from minHelper(childNode)
            }
        }
        return node;
    }

    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        return isPresentHelper(value, root);
    }

    /**
     * recursive helper method of the isPresent()
     * pre: none. checked by isPresent()
     * post: returns true if the value from parameter is found in the tree
     * @param value element to look for
     * @param node root of the tree
     */
    private boolean isPresentHelper(E value, BSTNode<E> node) {
        if (node == null) {
            return false;
        }
        if (value.compareTo(node.data) == LESS_THAN) {
            return isPresentHelper(value, node.left);
        }
        if (value.compareTo(node.data) == GREATER_THAN) {
            return isPresentHelper(value, node.right);
        }
        return true;
    }


    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * recursive helper method for height()
     * pre: none. checked by height()
     * post: return the height of the tree or -1 if the tree is empty
     * @param node root of the tree
     */
    private int heightHelper(BSTNode<E> node) {
        final int INCREMENT_HEIGHT = 1;
        if (node == null) {
            return EMPTY_TREE;
        }
        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);
        return Math.max(leftHeight, rightHeight) + INCREMENT_HEIGHT;
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order.
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        List<E> elements = new ArrayList<>();
        getAllHelper(root, elements);
        return elements;
    }

    /**
     * recursive helper method for getAll()
     * pre: none. checked by getAll()
     * post: add data to List<E> elements passed from getAll() in sorted order
     * @param node root of the tree
     * @param elements list of data
     */
    private void getAllHelper(BSTNode<E> node, List<E> elements) {
        if (node != null) {
            //go down left side (in-order traversal)
            getAllHelper(node.left, elements);
            //add element
            elements.add(node.data);
            //go down right side
            getAllHelper(node.right, elements);
        }
    }

    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max() {
        if (size == EMPTY) {
            throw new IllegalStateException("The BinarySearchTree cannot be empty");
        }
        return maxHelper(root);
    }

    /**
     * helper method for max()
     * pre: none. checked by max()
     * post: Return the largest value of the tree/subtree that passed its root as parameter
     * @param node root of the tree
     */
    private E maxHelper(BSTNode<E> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
        if (size == EMPTY) {
            throw new IllegalStateException("The BinarySearchTree cannot be empty");
        }
        return minHelper(root);
    }

    /**
     * helper method for min()
     * pre: none. checked by min()
     * post: returns the smallest value of the tree/subtree that passed its root as parameter
     * @param node root of the tree
     */
    private E minHelper(BSTNode<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    /**
     * An add method that implements the add algorithm iteratively 
     * instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree, 
     * otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add, 
     * false otherwise.
     */
    public boolean iterativeAdd(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to add cannot be null");
        }
        if (root == null) {
            size++;
            root = new BSTNode<>(data);
            return true;
        }
        BSTNode<E> node = root;
        //Find the place on the tree to add
        while (node.left != null || node.right != null) {
            if (data.compareTo(node.data) == LESS_THAN) {
                node = node.left;
            }
            else if (data.compareTo(node.data) == GREATER_THAN){
                node = node.right;
            }
            else {
                return false;
            }
        }
        if (data.compareTo(node.data) == LESS_THAN) {
            size++;
            node.left = new BSTNode<>(data);
            return true;
        }
        if (data.compareTo(node.data) == GREATER_THAN) {
            size++;
            node.right = new BSTNode<>(data);
            return true;
        }
        //node with data already exist
        return false;
    }

    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the
     * smallest value (minimum) is returned.
     * If kth = 1 the second smallest value is returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        final int FIRST_ELEMENT = 0;
        if (kth < FIRST_ELEMENT || kth >= size) {
            throw new IllegalArgumentException("The kth element to be returned must be within the"
                    + "size of the BinarySearchTree.");
        }
        BSTNode<E> tgtNode = getHelper(root, new int[]{kth});
        return tgtNode.data;
    }

    /**
     * recursive helper method for get()
     * pre: none. checked by get()
     * post: returns the kth node of the Binary Search Tree
     * @param node the root node of the tree
     * @param counter array of 1 index storing the count from the kth element
     */
    private BSTNode getHelper(BSTNode<E> node, int[] counter) {
        final int TARGET_NODE = 0;
        if (node == null) {
            return null;
        }
        BSTNode<E> leftNode = getHelper(node.left, counter);
        if (leftNode != null) { //this means that our counter reached 0
            return leftNode;
        }
        if (counter[0] == TARGET_NODE) {
            //we've reached our desired node!
            return node;
        }
        counter[0]--;
        return getHelper(node.right, counter);
    }

    /**
     * Return a List with all values in this Binary Search Tree 
     * that are less than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than 
     * the parameter value. If there are no values in this tree less 
     * than value return an empty list. The elements of the list are 
     * in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        List<E> elements = new ArrayList<>();
        getAllLessThanHelper(root, value, elements);
        return elements;
    }

    /**
     * recursive helper method for getAllLessThan
     * pre: none. checked by getAllLessThan()
     * post: adds element that are less than the parameter value to List<E> elements passed from
     * getAllLessThan()
     * @param node root of the tree
     * @param value the value for which are finding elements that are less than
     */
    private void getAllLessThanHelper(BSTNode<E> node, E value, List<E> elements) {
        if (node != null) {
            getAllLessThanHelper(node.left, value, elements);
            if (value.compareTo(node.data) == GREATER_THAN) {
                elements.add(node.data);
            }
            getAllLessThanHelper(node.right,value, elements);
        }
    }

    /**
     * Return a List with all values in this Binary Search Tree 
     * that are greater than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater
     *  than the parameter value. If there are no values in this tree
     * greater than value return an empty list. 
     * The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        List<E> elements = new ArrayList<>();
        getAllMoreThanHelper(root, value, elements);
        return elements;
    }

    /**
     * recursive helper method for getAllMoreThan()
     * pre: none. checked by getAllMoreThan()
     * post: adds element that are less than the parameter value to List<E> elements passed from
     * getAllLessThan()
     * @param node root of tree
     * @param value the value for which we are finding elements that are greater than
     */
    private void getAllMoreThanHelper(BSTNode<E> node, E value, List<E> elements) {
        if (node != null) {
            //go down left
            getAllMoreThanHelper(node.left, value, elements);
            if (value.compareTo(node.data) == LESS_THAN) {
                elements.add(node.data);
            }
            getAllMoreThanHelper(node.right,value, elements);
        }
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return numNodesAtDepthHelper(root, d);
    }

    /**
     * recursive helper method for numNodesAtDepth()
     * pre: none. checked by numNodesAtDepth()
     * post: returns the number of node of the Binary Search Tree at the depth in the parameter
     * @param node root of tree
     * @param depth the target depth that we are counting the number of nodes
     */
    private int numNodesAtDepthHelper(BSTNode<E> node, int depth) {
        final int NO_NODE = 0;
        final int ADD_NODE = 1;
        final int TARGET_DEPTH = 0;

        if (node == null) {
            return NO_NODE;
        }
        if (depth == TARGET_DEPTH) {
            return ADD_NODE;
        }
        int nextLayer = depth - 1;
        int numNodes = numNodesAtDepthHelper(node.left, nextLayer);
        numNodes += numNodesAtDepthHelper(node.right, nextLayer);
        return numNodes;
    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    /**
     * helper method for printTree()
     * pre: none
     * post: prints a vertical representation of the Binary Search Tree
     * @param n root of the tree
     * @param spaces string representing a space
     *
     */
    private void printTree(BSTNode<E> n, String spaces) {
        if(n != null){
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null);
        }

        public BSTNode(BSTNode<E> initLeft,
                       E initValue,
                       BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getData() {
            return data;
        }

        public BSTNode<E> getLeft() {
            return left;
        }

        public BSTNode<E> getRight() {
            return right;
        }

        public void setData(E theNewValue) {
            data = theNewValue;
        }

        public void setLeft(BSTNode<E> theNewLeft) {
            left = theNewLeft;
        }

        public void setRight(BSTNode<E> theNewRight) {
            right = theNewRight;
        }
    }
}