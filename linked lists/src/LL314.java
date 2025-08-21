/*
 * Student information for assignment:
 * On my honor, Vicky Chen, this programming assignment is my own work
 * and I have not provided this code to any other student.
 * UTEID: vc23777
 * email address: vickyxrc@utexas.edu
 * TA name: Gracelyn
 * Number of slip days I am using: 1
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LL314<E> implements IList<E>{
    //Constants
    private static final int ITEM_NOT_FOUND = -1; //indexOf(E item) returns -1 if item not found
    private static final double SIZE_MIDPOINT_RATIO = 0.5;
    private static final int EMPTY = 0;
    private static final int FIRST_ELEMENT = 0;

    //Instance variables
    DoubleListNode<E> first;
    DoubleListNode<E> last;
    int size;


    /**
     * pre: none
     * post: constructor initializes a LL314 object and set instance variables to their zero
     * equivalences
     */
    public LL314() {
        first = null;
        last = null;
        size = 0;
    }



    /**
     * Helper method that gets node at a specific index
     * Big O: O(N)
     * pre: 0 <= pos < size
     * post: returns node at specified index
     * @param pos index of the node to retrieve
     * @return node at the index pos
     */
    private DoubleListNode<E> getNodeAtPos(int pos) {
        final int LAST_ELEMENT = size - 1;
        DoubleListNode<E> tempNode = null;
        Boolean closerToFirst = pos < (size * SIZE_MIDPOINT_RATIO);
        if (closerToFirst) {
            tempNode = first;
            for (int index = FIRST_ELEMENT; index < pos; index++) {
                tempNode = tempNode.next;
            }
        }
        else {
            tempNode = last;
            for (int index = LAST_ELEMENT; index > pos; index--) {
                tempNode = tempNode.prev;
            }
        }
        return tempNode;
    }

    /**
     * Add an item to the end of this list.
     * Big O: O(1)
     * <br>pre: item != null
     * <br>post: size = old size + 1, get(size - 1) = item
     * @param item the data to be added to the end of this list, item != null
     */
    @Override
    public void add(E item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        DoubleListNode<E> newNode = new DoubleListNode<>(null, item, null);
        if (size == EMPTY) {
            first = newNode;
        }
        else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;

    }

    /**
     * Insert an item at a specified position in the list.
     * Big O: O(N)
     * <br>pre: 0 <= pos <= size, item != null
     * <br>post: size = old size + 1, get(pos) = item,
     * all elements in the list with a position >= pos have a
     * position = old position + 1
     *
     * @param pos the position to insert the data at in the list
     * @param item the data to add to the list, item != null
     */
    @Override
    public void insert(int pos, E item) {
        final int INDEX = pos - 1;
        if (pos < FIRST_ELEMENT || pos > size) {
            throw new IndexOutOfBoundsException("Position cannot be greater than the size");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (pos == FIRST_ELEMENT) {
            addFirst(item);
        }
        else if (pos == size) {
            add(item);
        }
        else {
            DoubleListNode<E> tempNode = getNodeAtPos(INDEX);
            DoubleListNode<E> newNode = new DoubleListNode(tempNode, item, tempNode.next);
            tempNode.next = newNode;
            newNode.next.prev = newNode;
            size++;
        }
    }

    /**
     * Change the data at the specified position in the list.
     * The old data at that position is returned.
     * Big O: O(N)
     * <br>pre: 0 <= pos < size, item != null
     * <br>post: get(pos) = item, return the old get(pos)
     *
     * @param pos the position in the list to overwrite
     * @param item the new item that will overwrite the old item, item != null
     * @return the old data at the specified position
     */
    @Override
    public E set(int pos, E item) {
        final int LAST_ELEMENT = size - 1;
        if (pos < FIRST_ELEMENT || pos >= size) {
            throw new IndexOutOfBoundsException("Position must be within 0 and " + (LAST_ELEMENT));
        }
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        DoubleListNode<E> node = getNodeAtPos(pos);
        E oldData = node.data;
        node.data = item;
        return oldData;
    }

    /**
     * Get an element from the list.
     * Big O: O(N)
     * <br>pre: 0 <= pos < size
     * <br>post: return the item at pos
     *
     * @param pos specifies which element to get
     * @return the element at the specified position in the list
     */
    @Override
    public E get(int pos) {
        if (pos < FIRST_ELEMENT || pos >= size) {
            throw new IllegalArgumentException("Postition must be within size.");
        }
        return getNodeAtPos(pos).data;
    }

    /**
     * Remove an element in the list based on position.
     * Big O: O(N)
     * <br>pre: 0 <= pos < size
     * <br>post: size = old size - 1, all elements of list with a
     * position > pos have a position = old position - 1
     *
     * @param pos the position of the element to remove from the list
     * @return the data at position pos
     */
    @Override
    public E remove(int pos) {
        final int LAST_ELEMENT = size - 1;
        E oldData;
        if (pos < FIRST_ELEMENT || pos >= size) {
            throw new IllegalArgumentException("Postition must be within list size.");
        }
        if (pos == FIRST_ELEMENT) {
            oldData = removeFirst();
        }
        else if (pos == LAST_ELEMENT) {
            oldData = removeLast();
        }
        else {
            DoubleListNode<E> node = getNodeAtPos(pos);
            oldData = node.data;
            //get nodes before and after the node being removed and set pointers to each other
            DoubleListNode<E> prevNode = node.prev;
            DoubleListNode<E> nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
        return oldData;
    }

    /**
     * Remove the first occurrence of obj in this list.
     * Big O: O(N)
     * Return <tt>true</tt> if this list changed as a result of this call,
     * <tt>false</tt> otherwise.
     * <br>pre: obj != null
     * <br>post: if obj is in this list the first occurrence has been removed
     * and size = old size - 1.
     * If obj is not present the list is not altered in any way.
     *
     * @param obj The item to remove from this list. obj != null
     * @return Return <tt>true</tt> if this list changed as a result of this
     * call, <tt>false</tt> otherwise.
     */
    @Override
    public boolean remove(E obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        int objIndex = indexOf(obj);
        if (objIndex != ITEM_NOT_FOUND) {
            remove(objIndex);
            return true;
        }
        return false;
    }

    /**
     * Return a sublist of elements in this list from <tt>start</tt> inclusive
     * Big-O: O(N)
     * to <tt>stop</tt> exclusive.
     * This list is not changed as a result of this call.
     * <br>pre: <tt>0 <= start <= size, start <= stop <= size</tt>
     * <br>post: return a list whose size is stop - start and contains the
     * elements at positions start through stop - 1 in this list.
     *
     * @param start index of the first element of the sublist.
     * @param stop stop - 1 is the index of the last element of the sublist.
     * @return a list with <tt>stop - start</tt> elements, the elements are
     * from positions <tt>start</tt> inclusive to <tt>stop</tt> exclusive in
     * this list. If start == stop an empty list is returned.
     */
    @Override
    public IList<E> getSubList(int start, int stop) {
        final int LAST_ELEMENT = size - 1;
        if (!(FIRST_ELEMENT <= start && start <= size && stop <= size)) {
            throw new IndexOutOfBoundsException("Range must be within size: " + (LAST_ELEMENT));
        }
        if (!(start <= stop)) {
            throw new IllegalArgumentException("Stop must be greater than Start");
        }
        LL314<E> newList = new LL314<>();
        DoubleListNode<E> currNode = getNodeAtPos(start);
        for (int index = start; index < stop; index++) {
            newList.add(currNode.data);
            currNode = currNode.next;
        }
        return newList;
    }


    /**
     * Return the size of this list (the number of elements).
     * Big O: O(1)
     * <br>pre: none
     * <br>post: return the number of items in this list
     *
     * @return the number of items in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Find the position of an element in the list.
     * Big O: O(N)
     * <br>pre: item != null
     * <br>post: return the index of the first element equal to item or -1 if
     * item is not present
     *
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item or a -1 if
     * item is not present
     */
    @Override
    public int indexOf(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        DoubleListNode<E> tempNode = first;
        for (int index = FIRST_ELEMENT; index < size; index++) {
            if (tempNode.data == item) {
                return index;
            }
            tempNode = tempNode.next;
        }
        return ITEM_NOT_FOUND;
    }

    /**
     * Find the position of an element in the list starting at a specified
     * position.
     * Big O: O(N)
     * <br>pre: 0 <= pos < size, item != null
     * <br>post: return the index of the first element equal to item starting
     * at pos or -1 if item is not present from position pos onward.
     *
     * @param item the element to search for in the list. Item != null
     * @param pos the position in the list to start searching from
     * @return starting from the specified position return the index of the
     * first element equal to item or a -1 if item is not present between pos
     * and the end of the list.
     */
    @Override
    public int indexOf(E item, int pos) {
        final int LAST_ELEMENT = size - 1;
        if (pos < FIRST_ELEMENT || pos >= size) {
            throw new IndexOutOfBoundsException("Position must be within 0 and " + (LAST_ELEMENT));
        }
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        IList<E> searchNodes = getSubList(pos, size);
        int index = searchNodes.indexOf(item);
        if (index != ITEM_NOT_FOUND) {
            index += pos; //readjust index to reflect the original index
        }
        return index;

    }

    /**
     * Transform the list to an empty state.
     * Big O: O(1)
     * <br>pre: none
     * <br>post: size = 0
     */
    @Override
    public void makeEmpty() {
        size = EMPTY;
        first = null;
        last = null;
    }

    /**
     * Return an Iterator for this list.
     * Big O: O(1)
     * <br>pre: none
     * <br>post: return an Iterator object for this List
     */
    @Override
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    /**
     * Remove all elements in this list from <tt>start</tt> inclusive to
     * Big O: O(N)
     * <tt>stop</tt> exclusive.
     * <br>pre: <tt>0 <= start <= size, start <= stop <= size</tt>
     * <br>post: <tt>size = old size - (stop - start)</tt>
     *
     * @param start position at beginning of range of elements to be removed
     * @param stop stop - 1 is the position at the end of the range of elements
     *            to be removed
     */
    @Override
    public void removeRange(int start, int stop) {
        final int LAST_ELEMENT = size - 1;
        if (!(FIRST_ELEMENT <= start && start <= size && stop <= size)) {
            throw new IndexOutOfBoundsException("Range must be within size: " + (LAST_ELEMENT));
        }
        if (!(start <= stop)) {
            throw new IllegalArgumentException("Stop must be greater than Start");
        }
        DoubleListNode<E> nodeBeforeRange = getNodeAtPos(start).prev;
        DoubleListNode<E> nodeAfterRange = getNodeAtPos(stop);
        if (nodeBeforeRange != null) {
            nodeBeforeRange.next = nodeAfterRange;
        }
        else {
            first = nodeAfterRange;
        }
        if (nodeAfterRange != null) {
            nodeAfterRange.prev = nodeBeforeRange;
        }
        else {
            last = nodeBeforeRange;
        }
        size -= (stop - start);
    }

    /**
     * Return a String version of this list enclosed in square brackets, [].
     * Elements are in order based on position in the list with the first
     * element first. Adjacent elements are separated by comma's.
     * Big-O: O(N)
     * pre: none
     * @return a String representation of this IList
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (first != null) {
            sb.append(first.data);
            DoubleListNode<E> currNode = first.next;
            while (currNode != null) {
                sb.append(", " + currNode.data);
                currNode = currNode.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Determine if this IList is equal to other. Two ILists are equal if they
     * contain the same elements in the same order.
     * Reference for checking instance of an object:
     * https://www.geeksforgeeks.org/instanceof-keyword-in-java/ for check instance of
     * Big O: O(N)
     *
     * @param other The other object to compare this IList to
     * @return true if this IList is equal to other, false otherwise
     *
     */
    public boolean equals(Object other) {
        if (other == null || !(other instanceof IList)) {
            return false;
        }
        IList<?> otherList = (IList<?>) other;

        if (size != otherList.size()) {
            return false;
        }

        //empty lists always returns true;
        if (size == 0) {
            return true;
        }

        Iterator<?> thisIt = this.iterator();
        Iterator<?> otherIt = otherList.iterator();
        while(thisIt.hasNext()) {
            Object thisElement = thisIt.next();
            Object otherElement = otherIt.next();
            if (!thisElement.equals(otherElement)) {
                return false;
            }
        }
        return true;

    }


    /**
     * Add item to the front of the list.
     * Big O: O(N)
     * <br>pre: item != null
     * <br>post: size = old size + 1, get(0) = item
     *
     * @param item the data to add to the front of this list
     */
    public void addFirst(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (size == EMPTY) {
            add(item);
        }
        else {
            DoubleListNode newNode = new DoubleListNode(null, item, first);
            first.prev = newNode;
            first = newNode;
            size++;
        }
    }

    /**
     * Add item to the end of the list.
     * Big O: O(1)
     * <br>pre: item != null
     * <br>post: size = old size + 1, get(size - 1) = item
     *
     * @param item the data to add to the end of this list
     */
    public void addLast(E item) {
        add(item);
    }

    /**
     * Remove and return the first element of this list.
     * Big O: O(1)
     * <br>pre: size > 0
     * <br>post: size = old size - 1
     *
     * @return the old first element of this list
     */
    public E removeFirst() {
        if (size <= EMPTY) {
            throw new IllegalArgumentException("List cannot be empty");
        }
        E oldData = first.data;
        first = first.next;
        size--;
        if (size == EMPTY) {
            last = null;
        }
        else {
            first.prev = null;
        }
        return oldData;
    }

    /**
     * remove and return the last element of this list.
     * Big O: O(1)
     * <br>pre: size > 0
     * <br>post: size = old size - 1
     *
     * @return the old last element of this list
     */
    public E removeLast() {
        if (size <= EMPTY) {
            throw new IllegalArgumentException("List cannot be empty");
        }
        E oldData = last.data;
        last = last.prev;
        size --;
        if (size == EMPTY) {
            first = null;
        }
        else {
            last.next = null;
        }
        return oldData;
    }

    /**
     * Implementation of the Iterator interface for our LL314 doubly linked list
     */

    private class DLLIterator implements Iterator<E> {
        private DoubleListNode<E> nextNode;
        private int removeIndex;
        private boolean removable;

        /**
         * Instantiate a DLLIterator object
         * Big O: O(1)
         * pre: none
         * post: nextNode is set to the first node of the IList
         */
        public DLLIterator() {
            nextNode = first;
            removeIndex = -1;
        }

        /**
         * checks if the iterator has another node
         * Big O: O(1)
         *
         * pre: none
         * @return true if there is a next node (if nextNode != null) and false otherwise
         */
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Moves the iterator over and get the next node
         * pre: hasnext() == true
         * @return the next node iterated over
         */

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E nodeData = nextNode.data;
            nextNode = nextNode.next;
            removable = true;
            removeIndex++;
            return nodeData;
        }

        /**
         * Remove the most recent element iterated over.
         * Big O: O(1)
         * pre: removable == true (can only remove once after each time iterator goes to next.
         * post: none
         */
        public void remove() {
            if (!removable) {
                throw new IllegalStateException("Node already removed");
            }
            //ensure that remove cannot be called again until it is set to true by next()
            removable = false;
            LL314.this.remove(removeIndex);
            removeIndex--;
        }
    }

    /**
     * A class that represents a node to be used in a linked list.
     * These nodes are singly linked.

     }

    /**
     * A class that represents a node to be used in a linked list.
     * These nodes are doubly linked.
     *
     * @author Mike Scott
     * @version July 25, 2005
     */
    private static class DoubleListNode<E> {

        // the data to store in this node
        private E data;

        // the link to the next node (presumably in a list)
        private DoubleListNode<E> next;

        // the reference to the previous node (presumably in a list)
        private DoubleListNode<E> prev;

        /**
         * default constructor.
         * <br>pre: none
         * <br>post: data = null, next = null, prev = null
         * <br>O(1)
         */
        public DoubleListNode() {
            this(null, null, null);
        }

        /**
         * Create a DoubleListNode that holds the specified data
         * and refers to the specified next and previous elements.
         * <br>pre: none
         * <br>post: this.data = data, this.next = next, this.prev = prev
         * <br>O(1)
         *
         * @param prev the previous node
         * @param data the  data this DoubleListNode should hold
         * @param next the next node
         */
        public DoubleListNode(DoubleListNode<E> prev, E data, DoubleListNode<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}