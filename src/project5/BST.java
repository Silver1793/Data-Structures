package project5;

import static org.junit.Assert.assertNotNull;
import java.util.*;

/**
 * This is a BST class that takes in elements and adds them. It is possible to add and remove elements, get the first element, the last element
 * get a range of elements and see if the BST contains a certain element. 
 * Uses comparable to determine how to add the the elements in the BST
 * @author Richard, Joanna Klukowska
 *
 * @param <T> Takes in a type T to be used in the comparable classes.
 */
public class BST < T extends Comparable <T> > {
    public BSTNode root;   //reference to the root node of the tree 
    public int size;       //number of values stored in this tree 
    private Comparator<T> comparator;   //comparator object to overwrite the 
                                //natural ordering of the elements 
	private boolean found;  //helper variable used by the remove methods
    private boolean added ; //helper variable used by the add method 
    private ArrayList<T> list = new ArrayList<T>();
	private Iterator<T> itr = this.iterator();
    
    /**
	 * Constructs a new, empty tree, sorted according to the natural ordering of its elements.
	 */
    public BST () {
        root = null; 
        size = 0; 
        comparator = null; 
    }
    /**
	 * Constructs a new, empty tree, sorted according to the specified comparator.
	 */
    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

	/**
	 * Adds the specified element to this tree if it is not already present. 
	 * If this tree already contains the element, the call leaves the 
     * tree unchanged and returns false.
	 * @param data element to be added to this tree 
     * @return true if this tree did not already contain the specified element 
     * @throws NullPointerException if the specified element is null  
	 */
    public boolean add ( T data ) { 
         added = false; 
         if (data == null) return added; 
         //replace root with the reference to the tree after the new 
         //value is added
         root = add (data, root);
         //update the size and return the status accordingly 
         if (added) size++; 
         return added; 
    }
    /*
	 * Actual recursive implementation of add. 
     *
     * This function returns a reference to the subtree in which 
     * the new value was added. 
	 *
     * @param data element to be added to this tree 
     * @param node node at which the recursive call is made 
	 */
    private BSTNode add (T data, BSTNode node ) {
        if (node == null) {
            added = true; 
            return new BSTNode(data); 
        }
        //decide how comparisons should be done 
        int comp = 0 ;
        if (comparator == null ) //use natural ordering of the elements 
            comp = node.data.compareTo(data); 
        else                     //use the comparator 
            comp = comparator.compare(node.data, data ) ;
        
        //find the location to add the new value 
        if (comp > 0 ) { //add to the left subtree 
            node.left = add(data, node.left); 
        }
        else if (comp < 0 ) { //add to the right subtree
            node.right = add(data, node.right); 
        }
        else { //duplicate found, do not add 
            added = false; 
            return node; 
        }
        return node; 
    }
    /**
	 * Removes the specified element from this tree if it is present. 
	 * Returns true if this tree contained the element (or equivalently, 
     * if this tree changed as a result of the call). 
     * (This tree will not contain the element once the call returns.)
	 * @param target object to be removed from this tree, if present
     * @return true if this set contained the specified element 
     * @throws NullPointerException if the specified element is null  
	 */
	public boolean remove(T target)
	{
        //replace root with a reference to the tree after target was removed 
		root = recRemove(target, root);
        //update the size and return the status accordingly 
		if (found) size--; 
		return found;
	}
	/*
	 * Actual recursive implementation of remove method: find the node to remove.
     *
	 * This function recursively finds and eventually removes the node with the target element 
     * and returns the reference to the modified tree to the caller. 
     * 
	 * @param target object to be removed from this tree, if present
     * @param node node at which the recursive call is made 
	 */
	private BSTNode recRemove(T target, BSTNode node)
	{
		if (node == null)  { //value not found 
			found = false;
            return node; 
        }
        
        //decide how comparisons should be done 
        int comp = 0 ;
        if (comparator == null ) //use natural ordering of the elements 
            comp = target.compareTo(node.data); 
        else                     //use the comparator 
            comp = comparator.compare(target, node.data ) ;

        
		if (comp < 0)       // target might be in a left subtree 
			node.left = recRemove(target, node.left);
		else if (comp > 0)  // target might be in a right subtree 
			node.right = recRemove(target, node.right );
		else {          // target found, now remove it 
			node = removeNode(node);
			found = true;
		}
		return node;
	}

	/*
	 * Actual recursive implementation of remove method: perform the removal.
	 *
	 * @param target the item to be removed from this tree
	 * @return a reference to the node itself, or to the modified subtree
	 */
	private BSTNode removeNode(BSTNode node)
	{
		T data;
		if (node.left == null)   //handle the leaf and one child node with right subtree 
			return node.right ; 
		else if (node.right  == null)  //handle one child node with left subtree 
			return node.left;
		else {                   //handle nodes with two children 
			data = getPredecessor(node.left);
			node.data = data;
			node.left = recRemove(data, node.left);
			return node;
		}
	}

	/*
	 * Returns the information held in the rightmost node of subtree
	 *
	 * @param subtree root of the subtree within which to search for the rightmost node
	 * @return returns data stored in the rightmost node of subtree
	 */
	private T getPredecessor(BSTNode subtree)
	{
		if (subtree==null) //this should not happen 
            throw new NullPointerException("getPredecessor called with an empty subtree");
		BSTNode temp = subtree;
		while (temp.right  != null)
			temp = temp.right ;
		return temp.data;
	}
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
    public String toStringTree( ) {
        StringBuffer sb = new StringBuffer(); 
        toStringTree(sb, root, 0);
        return sb.toString();
    }
    //uses preorder traversal to display the tree 
    //WARNING: will not work if the data.toString returns more than one line 
    private void toStringTree( StringBuffer sb, BSTNode node, int level ) {
        //display the node 
        if (level > 0 ) {
            for (int i = 0; i < level-1; i++) {
                sb.append("   ");
            }
            sb.append("|--");
        }
        if (node == null) {
            sb.append( "->\n"); 
            return;
        }
        else {
            sb.append( node.data + "\n"); 
        }

        //display the left subtree 
        toStringTree(sb, node.left, level+1); 
        //display the right subtree 
        toStringTree(sb, node.right, level+1); 
    }
    /**
     * Gets the element that is the first(lowest) element in the tree
     * @throws NoSuchElementException if the tree is empty
     * @return the element that is the left most node of the left subtree
     */
	public T first() {
		if(size == 0)
			throw new NoSuchElementException("The BST is empty");
		BSTNode current = root;
		while(current.left != null)//Goes through the left subtree and finds the most left node
			current = current.left;
		return current.data;
	}
    /**
     * Gets the element that is the last(highest) element in the tree
     * @throws NoSuchElementException if the tree is empty
     * @return the element that is the right most node of the right subtree
     */
	public T last() {
		if(size == 0)
			throw new NoSuchElementException("The BST is empty");
		BSTNode current = root;
		while(current.right != null) {  //Goes through the right subtree and finds the most right node
			current = current.right;
		}
		return current.data;
		
	}
	/**
	 * Checks to see if two trees are equal by checking if both trees have the same elements in the same order
	 * @param obj of type object that will be converted to a BST and will be compared with another BST
	 * @return true if the two BST have the same elements in the same order. False if the parameter is null, if the parameter is not of BST,
	 * or false if the size of the two BST are not the same or if they don't have the same elements in the same order.
	 */
	public boolean equals (Object obj) {
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(!(obj instanceof BST))
			return false;
		BST other = (BST) obj;
		if(this.size != other.size())
			return false;
		while(itr.hasNext()) { //Goes through all the elements in the BST
			if(!(itr.next().equals(other.itr.next()))) //Determines if the next element in this BST is the same element as the element in the other BST
				return false;
		}
		return true;		
	}
	/**
	 * Checks the BST to see if it contains an element that matches the parameter
	 * @param o will be used to check if the BST has an element that matches it
	 * @throws NullPointerException if the parameter is null.
	 * @throws ClassCastException if the parameter trying to be found is of different type than the elements in the tree
	 * @return true if the parameter is found in the BST or false if it is not found
	 */
	public boolean contains(Object o) {
		if(o == null)
			throw new NullPointerException("Input is null");
		T other = (T) o;
		try {
			other.compareTo(root.data); //Tries to compare the object with the root and sees if they are of the same type
		} catch (Exception e) {
			throw new ClassCastException("The type you are finding is not a valid type");
		}
			return contains(other, root); // Goes to the wrapper with the root and a type T as the parameters
	}
	/*
	 * Wrapper method for the contains method. 
	 * Takes in a type element called x and a BSTNode called node. 
	 * Recursively goes through the tree and checks if any element in the tree matches the element of x
	 */
	private boolean contains(T x, BSTNode node) {
		if(node != null) {
		if(x.equals(node.data))
			return true;
		if(contains(x, node.right))
			return true;
		else if(contains(x, node.left))
			return true;
		}
			return false;
	}
	/**
	 * Determines if the tree is empty or not.
	 * @return true if the size of the tree is 0. False if there are elements in the tree.
	 */
	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}
	/**
	 * Returns an iterator
	 * @return a new iterator called Itr
	 */
	public Iterator<T> iterator(){ //CHECK
		return new Itr();
	}
	/*
	 * The iterators that implements the next, has next and inOrder methods. Will be used to be returned in the iterator() method.
	 */
	private class Itr implements Iterator<T>{
	private ArrayList<T> list = new ArrayList<T>();
	private int beg = 0;
	/*
	 * Constructor for the Itr class that calls the inOrder method with a root and an arraylist as parameters.
	 */
	private Itr(){
		inOrder(root, list);
	}
	/*
	 * Takes in the parameters of the starting node and an arraylist. Adds all elements into an arraylist in an inorder fashion. 
	 * Goes through the left subtree, adds all elements to an arraylist and then adds the root and then adds all nodes from right subtree.
	 */
	private void inOrder(BSTNode node, ArrayList tempList) {
		if(node == null)
			return;
		if(node != null) {
			inOrder(node.left, tempList); //Goes to the left node in the left subtree first 
			tempList.add(node.data);
			inOrder(node.right, tempList); //goes to the right subtree after finishing adding all element in the left subtree and root.
		}
	}
	/**
	 * Determines if there is another element in the iterator
	 * @return true if there is another element, false if there is no more elements
	 */
	@Override
	public boolean hasNext() {
		if(beg < size())
			return true;
		else {
			return false;
		}
	}
	/**
	 * Gives the current element and then goes to the next element
	 * @return The current element and then moves onto the next element in the iterator
	 */
	@Override
	public T next() {
		T temp = list.get(beg); //Gets the element based on the index of the array
		beg++; //Increases the index in the array
		return temp;
	}
	}
	/**
	 * Goes through the tree using an iterator and adds all of the elements to a string.
	 * @return a string with all of the elements in the tree. Returns "[]" if the tree is empty.
	 */
    public String toString() {
    	Iterator<T> itr = this.iterator();
    	if(size == 0)
    		return"[]";
    		//return "The BST is empty";
    	String ret = "[";
    	while(itr.hasNext()) {
    		ret += itr.next() + ", ";
    	}
    	ret = ret.replaceAll(", $", "");
    	return ret + "]";
    }
    /**
     * Goes through all the elements in an iterator and adds them all to an array.
     * @return and array of type object that has all the elements in the iterator.
     */
    public Object[] toArray() {
    	Iterator<T> itr = this.iterator();
    	Object[] arr = new Object[size];
    	int count = 0; 
    	while(itr.hasNext()) {
    		arr[count] = itr.next();
    		count++;
    	}
    	return arr;
    }
    /**
     * Gets all the elements between a certain range and adds them to an arraylist
     * @param fromElement of type T that will be used to determine the lowerbound of the getRange method
     * @param toElement of type T that will be used to determine the upperbounf of the getRange method
     * @return an arrayList that holds all the elements that fit in the range of the two elements./
     */
    public ArrayList<T> getRange(T fromElement, T toElement){ //Check
    	ArrayList<T> tmp = new ArrayList<>();
    	if(fromElement == null || toElement == null)
    		throw new NullPointerException("Parameters cannot be null");
    	if(toElement.compareTo(fromElement) < 0)
    		throw new IllegalArgumentException("Not a valid range");
    	if(size == 0) {
    		return tmp;
    	}
    	return wrapper2(root, fromElement, toElement, tmp);
    }
    /*
     * Wrapper method for the getRange method. Takes in  node called node and two elements that determine the high and low for the range.
     * Will go thought the BST and add all valid nodes into an array list.
     */
    private ArrayList<T> wrapper2(BSTNode node, T low, T hi, ArrayList temp){
    	if(node == null)
    		return null;
    	if(comparator == null) {
        	if(node.data.compareTo(low) > 0) {
        		wrapper2(node.left, low, hi, temp);
        	}
        	if(node.data.compareTo(low) >= 0 && node.data.compareTo(hi) <= 0) {
        		temp.add(node.data);
        	}
        	if(node.data.compareTo(hi) < 0)
    			wrapper2(node.right, low, hi, temp);
    	}
    	else if(comparator != null){
    	if(comparator.compare(node.data,low) > 0) {
    		wrapper2(node.left, low, hi, temp);
    	}
    	if(comparator.compare(node.data,low) >= 0 && comparator.compare(node.data,hi) <= 0) {
    		temp.add(node.data);
    	}
    	if(comparator.compare(node.data,hi) < 0)
			wrapper2(node.right, low, hi, temp);
    	}
    	return temp;
    	
    }

    /* 
     * Node class for this BST 
     */ 
    public class BSTNode implements Comparable < BSTNode > {

        T data;
        BSTNode  left;
        BSTNode  right;

        public BSTNode ( T data ) {
            this.data = data;
        }

        public BSTNode (T data, BSTNode left, BSTNode right ) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int compareTo ( BSTNode other ) {
            if (BST.this.comparator == null )
                return this.data.compareTo ( other.data );
            else 
                return comparator.compare(this.data, other.data); 
        }
        public String toString() {
        	return this.data+"";
        }
    } 	
}

