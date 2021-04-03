package project3;
import java.util.NoSuchElementException;

/**
 * A linked list that uses a node class to add element to a specific position. Also removes elements based on location
 * or based on their data. Can also be cleared, find an element and compare two MyList objects
 * 
 * @author Richard
 *
 * @param <E> Allows the list to be used for all types.
 */
public class MyList <E> implements List<E>{
	
	private Node head;
	private Node tail;
	private int size;
	
	/**
	 * The constructor for the MyList class. Sets the nodes head and tail to null and sets size to 0.
	 */
	public MyList() {
		head = new Node(null);
		tail = new Node(null);
		size = 0;
	}
	/**
	 * Adds an element at a specific position in the list using index 0 and increases size.
	 * @param item is the element that will be added to the list
	 * @param pos is the position the element will be added to in the list starting at index 0
	 * @return true if the position is valid and the element was added to the list returns false if the element trying to be added is already in the list
	 * @throws NoSuchElementException when the position the element is trying to be added at is greater than the size of the list or less than 0
	 */
	public boolean add(E item, int pos) throws NoSuchElementException {
		if(pos < 0 || pos > size) //Checks to see if the position is in the linkedlist, if it is not prints out an exception
			throw new NoSuchElementException("Please select a postion in the list to add");
		
		Node insert = new Node(item);
		Node current = head;
		current = head;
		if(size == 0) { //Checks to see if the linked list is empty
			head = insert;
			tail = insert;
			size++;
			return true;
		}
		else if(size == 1) {
			if(pos == 0) {
			tail = head;
			insert.next = head;
			head = insert;
			tail.prev = insert;
			size++;
			return true;
			}
			else if(pos == 1) {
				tail = insert;
				head.next = tail;
				tail.prev = head;
				size++;
				return true;
			}
		}
		else if(pos == 0) {//Checks to see if the position being added is the first position
				insert.next = head; //1 0
				head = insert;
				size++;
				return true;
		}
		else if(pos == size) { //Checks to see if the position being added is the last position
			tail.next = insert;
			insert.prev = tail;
			tail = insert;
			size++;
			return true;
		}
			for(int i = 0; i < pos-1; i++) { //Traverses the list until it reaches the node before the wanted position
				current = current.next; 
			}
			insert.next = current.next; 
			current.next = insert;
			insert.prev = current;
			size++;
			return true;
	}
	/**
	 * Removes and returns the element at a specific position in the list and decreases size
	 * @param pos is the position of the element that will be removed in the list starting at index 0
	 * @throws NoSuchElementException when the position of the element to be removed is greater than or equal to the size of the list or less than 0
	 * @return the data of the element that was removed
	 */
	public E remove(int pos) throws NoSuchElementException {
		if(pos < 0 || pos >= size) //Checks to see if the position is in the linked list, if not throws an exception
			throw new NoSuchElementException("Please enter a position in the list");
		Node current = head;
		Node temp = new Node(null);
		
		if(size == 1) {//Checks to see if there is one element in the list
			temp = head;
			head = null;
			tail = null;
			size--;
			return temp.data;
		}
		else if(pos+1 == size) { //Checks to see if the position is the last element in the list
			temp = tail;
			tail = tail.prev;
			tail.next = null;
			size--;
			return temp.data;
		}
		else if(pos == 0) { //Checks to see if the position is the first in the list
			temp = head;
			head = head.next;
			head.prev = null;
			size--;
			return temp.data;
		}
		else {
			for(int i = 0; i < pos-1; i++) { //Traverses the list until it reaches the node before the one to be removed
				current = current.next;
			}
			temp = current.next;
			current.next = current.next.next;
			size--;
			return temp.data;
		}
	}
	/**
	 * Removes a specific element in the list and decreases size
	 * @param item the data of the element that will be removed in the list
	 * @return the data of the element that was removed or returns null if no elements have the same data as specified
	 */
	public E remove(E item) {
		Node current = head;
		Node temp = new Node(null);
		if(head == null) //Checks to see if the linked list is empty
			return null;
		else if(head.data.equals(item)) {//Checks to see if the first element is the item to be removed
			temp = head;
			if(size == 1) {
				head = null;
				tail = null;
				size--;
				return temp.data;
			}
			head = head.next;
			head.prev = null;
			size--;
			return temp.data;
		}
		else if(size == 1 && !(head.data.equals(item))) {
			return null;
		}
		while(!(current.next.data.equals(item))) {//Traverses the linked list until the node before the node to be removed
			current = current.next;
			if(current.next == null) { //If the linked list traverses to the end it means the element was not in the linked list, returns null
				return null;
			}
		}
			temp = current.next;
		current.next = current.next.next;
		size--;
		return temp.data;
	}
	/**
	 * Searches through the list and tries to find the position of the element that has the same data as the parameter
	 * @param item is the data of the element that is being searched for
	 * @return the position of the specific element or -1 if the no elements have the same data as the one being searched for
	 */
	public int find(E item) {
		Node current = head;
		int count = 0;
		if(item == null) {
			return -1;
		}
		if(size == 0)
			return -1;
		while(current != null && (current.data != item)) {
			if(!(current.data.equals(item))) { 
			current = current.next;
			count++;
			}
			else {
				break;
			}
		}
		if(current != null && current.data.equals(item)) {
			return count;
		}
		return -1;
	}
	/**
	 * Gets the element at the specific position and returns that element
	 * @param pos the position of the element that will be gotten
	 * @throws NoSuchElementException when the position of the element that is trying to be gotten is less than 0 or greater than or equal to the size of the list
	 * @return the data of the element at the specific position
	 */
	public E get(int pos) {
		if(pos < 0 || pos >= size) { //Checks to see if the position is in the linked list
			throw new NoSuchElementException("Please enter a position that is in the list");	
		}
		Node temp = new Node(null);
		Node current = head;
		for(int i = 0; i < pos; i++) {
			current = current.next;
		}
		return current.data;
	}
	/**
	 * Returns the size of the list based on the number of elements in the list
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	/**
	 * Clears the list by setting both head and tail to null and size to 0
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	/**
	 * Compares two objects and checks to see if they are both MyList objects with the same elements in the same order
	 * 
	 * @param o takes in an object that will be compared to an object of MyList
	 * @return true if the two MyList objects are equal or false if one of them is not a MyList, is of different size or is not the same
	 */
	public boolean equals(Object o) {
		if(o == this) //Checks to see if the parameter is the same object as the one being compared
			return true;
		if(!(o instanceof MyList)) //Checks to see if the parameter is of instanceOf MyList
			return false;
		MyList<E> otherList = (MyList<E>)o;
		if(otherList.size() != size()) //Compares the sizes of the two linked lists
			return false;
		Node o1 = this.head;
		Node o2 = otherList.head;
		for(int i = 0; i < size; i++) {//Traverses the linked lists and compares the elements to each other
			if(!(o1.data.equals(o2.data))) {
				return false;
			}
			o1 = o1.next;
			o2 = o2.next; 
		}
		return true;
	}
	/**
	 * Returns the tail in the linked list
	 * @return The data of the tail
	 */
	public E getTail() {
		return tail.data;
	}
	/**
	 * Creates and returns a string of all of the elements in the list
	 * @return "this list is empty" if there are no elements in the list or returns a string of all the elements in the list.
	 */
	public String toString() {
		Node current = head;
		String ret = "";
		ret += ""+head;
		if(size==0)
			return "The list is empty";
		while(current.next != null) {
			current = current.next;
			ret = ret + ", "+ current;
		}
		return ret;
	}
	
	/**
	 * A node class that will be used in the MyList class. Creates a node that holds a data and reference points to the next and previous nodes.
	 * @author Richard
	 *
	 */
	public class Node {
		private E data;
		private Node next;
		private Node prev;

		/**
		 * Constructor for the Node class that sets data to the parameter
		 * @param data will set data to the input
		 */
		Node (E data) {
			this.data = data;
		}
		/**
		 * Returns a string of the data of the node
		 * @return a string of the data of the node
		 */
		public String toString () {
			return data.toString();
		}
		/**
		 * Compares two nodes. Checks to see if the node being compared is the same node or is of an instance of Node.
		 * @return True if the two Nodes are equal to one another, false if they are no equal or if one is not an instanceOf Node
		 * @param o takes in an object of type node that will be compared to another node
		 */
		public boolean equals( Object o ) {
			if (this == o) return true;
			if (!(o instanceof MyList.Node)) {
				return false;
			}
			Node other = (Node) o;
			if (!this.data.equals(other.data)) {
				return false;
			}
			return true;
		}

	}
}
