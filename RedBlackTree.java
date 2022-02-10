// --== CS400 File Header Information ==--
// Name: Jack Abraham
// Email: jtabraham@wisc.edu
// Team: HC
// TA: Na Li
// Lecturer: Florian
import java.util.LinkedList;
import java.util.Hashtable;

/**
 * Red Black Tree implementation with a Node inner class for representing
 * the nodes within a RedBlackTree.  You can use this class' insert
 * method to build a Red Black tree, and its toString method to display
 * the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {
    /**
     * This class represents a node holding a single value within a Red Black tree
     * the parent, left, and right child references are always be maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public Node(T data) { this.data = data; }
        public boolean isBlack = false;
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
    }
    protected Node<T> root; // reference to root node of tree, null when empty
    /**
     * Performs a naive insertion into a RedBlack Tree: adding the input
     * data value to a new node in a leaf position within the tree.  After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this RedBlack tree
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(T data) throws NullPointerException,
                                      IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");
        Node<T> newNode = new Node<T>(data);
        if(root == null) { root = newNode; } // add first node to an empty tree
        else insertHelper(newNode,root); // recursively insert into subtree
        root.isBlack = true;
    }
    
    /** 
     * Recursive helper method to make sure that no red black property is violated. 
     * If the parent node is already black, then we are okay. Otherwise, we take on two
     * cases. If the uncle is black, then we need to rotate and recolor. There are double
     * rotations (left right) and single (right) for when the parent is a left child. The other
     * case is excecuted if the uncle is red. This is a simple recoloring of the grandparent and
     * two siblings. The subcases if the parent node is a right child are also included. Finally,
     * the recursive method is called on the newNodes parent to ensure the fix is properly made
     * throughout the tree.
     * @param newNode is the new node that is being added to this tree
     */
    private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
        root.isBlack = true;
    if (newNode == null) return;
    if (newNode.parent == null) return;
    if (newNode.parent.isBlack && !newNode.isBlack) return;
    if (newNode.parent.isBlack && newNode.isBlack) return;
    if (!newNode.parent.isBlack && newNode.isBlack) return;
    else if (newNode.parent.isLeftChild()) {
            if (newNode.parent.parent.rightChild == null || newNode.parent.parent.rightChild.isBlack) {
                    //if uncle is black, rotate, recolor
                    if (newNode.isLeftChild()) {            // right rotation
                            rotate(newNode.parent, newNode.parent.parent);
                            newNode.parent.isBlack = true;
                            newNode.parent.rightChild.isBlack = false;
                    }
                    else {                  // left right rotation
                            rotate(newNode, newNode.parent);
                            rotate(newNode, newNode.parent);
                            newNode.isBlack = true;
                            newNode.rightChild.isBlack = false;
                    }
                    enforceRBTreePropertiesAfterInsert(newNode.parent);
            }
            else { //uncle is red, recolor
                    newNode.parent.parent.rightChild.isBlack = true;
                    newNode.parent.parent.leftChild.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    enforceRBTreePropertiesAfterInsert(newNode.parent.parent); // Called on parents parent
                    // because we know that new parent is black
            }
    }
    else if (!newNode.parent.isLeftChild()) { //parent is a right child
            if (newNode.parent.parent.leftChild == null || newNode.parent.parent.leftChild.isBlack) {
                    //if uncle is black, rotate, recolor
                    if (!newNode.isLeftChild()) { // left rotation
                            rotate(newNode.parent, newNode.parent.parent);
                            newNode.parent.isBlack = true;
                            newNode.parent.leftChild.isBlack = false;
                    }
                    else {
                            rotate(newNode, newNode.parent); // right left rotation
                            rotate(newNode, newNode.parent);
                            newNode.isBlack = true;
                            newNode.rightChild.isBlack = false;
                    }
                    enforceRBTreePropertiesAfterInsert(newNode.parent);
            }
            else { //uncle is red, recolor
                    newNode.parent.parent.rightChild.isBlack = true;
                    newNode.parent.parent.leftChild.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    enforceRBTreePropertiesAfterInsert(newNode.parent.parent); // Called on parents parent
                    // because we know that new parent is black
            }
    }
}
    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references (as defined by Comparable.compareTo())
     */
    private void insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) throw new IllegalArgumentException(
            "This RedBlackTree already contains that value.");
        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.leftChild);
        }
        // store newNode within the right subtree of subtree
        else {
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.rightChild);
        }
    }
    
    /**
     * This method performs a level order traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { return root.toString(); }
    
    /**
     * Performs the rotation operation on the provided nodes within this RBT.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation (sometimes called a left-right 
     * rotation).  When the provided child is a rightChild of the provided 
     * parent, this method will perform a left rotation (sometimes called a 
     * right-left rotation).  When the provided nodes are not related in one 
     * of these ways, this method will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
            if (child.parent != parent) throw new
              IllegalArgumentException("the two nodes are not related as parent and child");
            if (!child.isLeftChild()) {
                    parent.rightChild = child.leftChild;
                    if (child.leftChild != null) {
                    child.leftChild.parent = parent;
                    }
                    if (parent == root) {root = child;
                    child.parent = null;}
                    else {child.parent = parent.parent;
                    if (!parent.isLeftChild()) parent.parent.rightChild = child;
                    else parent.parent.leftChild = child;}
                    child.leftChild = parent;
                    parent.parent = child;
            }
            else {
                    parent.leftChild = child.rightChild;
                    if (child.rightChild != null) {
                    child.rightChild.parent = parent;
                    }
                    if (parent == root){ root = child;
                            child.parent = null;}
                    else {child.parent = parent.parent;
                    if (!parent.isLeftChild()) parent.parent.rightChild = child;
                    else parent.parent.leftChild = child;
                    }
                    child.rightChild = parent;
                    parent.parent = child;
            }
        }
                }
    
    
    
    
    
    