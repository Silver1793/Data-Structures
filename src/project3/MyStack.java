package project3;

/**
 * A stack class that implements a Stack interface. Allows for adding to the top of the stack, removing from the top of the stack and returns 
 * the top element in the stack. Based on a linked list reference.
 * 
 * @author Richard
 * @param <E> Allows the stack to be used for all types.
 */
public class MyStack <E> implements Stack<E> {
	private Node head;
	private int size;
	
	/**
	 * The constructor for the MyStackclass. Sets the node head to null and sets size to 0.
	 */
	public MyStack(){
		head = null;
		size = 0;
	}
	/**
	 * Adds a new element to the top of the stack
	 * 
	 * @throws IllealArgumentException when the inserted element is null. Prints out an error statement saying "Inserted element cannot be null
	 * @param item takes in a type e parameter of the data the inserted element will be
	 */
	public void push(E item) {
		if(item == null) //Checks to see if the item is null, if it is throws exception
			throw new IllegalArgumentException("Inserted element cannot be null");
		Node insert = new Node(item);
		insert.next = head;
		head = insert;
		size++;
	}
	/**
	 * Removes the element from the top of the stack and returns it
	 * 
	 * @return the removed elements data or null if there are no elements in the stack
	 */
	public E pop() {
		if(size == 0) //Checks to see if stack is empty
			return null;
		Node temp = new Node(null);
		temp = head;
		head = head.next;
		size--;
		return temp.data;
	}
	/**
	 * Lets the user see what the top element of the stack is
	 * 
	 * @return the top element in the stack
	 */
	public E top() {
		if(size != 0) //Checks to see if stack is empty
		return head.data;
		else
			return null;
	}
	/**
	 * Compares two objects and checks to see if they are both MyStack objects with the same elements in the same order
	 * 
	 * @param o takes in an object that will be compared to an object of MyStack
	 * @return true if the two MyStack objects are equal or false if one of them is not a MyStack, is of different size or is not the same
	 */
	public boolean equals(Object o) {
		if(this == o) //Checks if parameter is object as the stack
			return true;
		if(! (o instanceof MyStack)) //Checks to see if the parameter is an instance of MyStack
			return false;
		MyStack<E> otherStack = (MyStack<E>)o;
		Node o1 = head;
		Node o2 = otherStack.head;
		if(size != otherStack.size) //Compares the size of the the stacks
			return false;
		while(o1!=null) {
			if(!(o1.data.equals(o2.data))) //Traverses each element in both the stacks and compares them
				return false;
			o1 = o1.next;
			o2 = o2.next;
		}
		return true;
		
	}
	/**
	 *Creates a string of all the elements in the stack and returns it
	 *
	 *@return a string of all the elements in the stack
	 */
	public String toString() {
		if(size == 0) {
			return "The list is empty";
		}
		String ret = "";
		Node current = head;
		ret = ""+head;
		for(int i = 0; i < size-1; i ++) {
			current = current.next;
			ret = current + ", " + ret;
		}
		return ret;
	}
	/**
	 * A node class that will be used for the MyStack class. Creates a node that holds a data and a reference point to the next node
	 * @author Richard
	 *
	 */
	public class Node {
		private E data;
		private Node next;
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
			if (!(o instanceof MyStack.Node)) {
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
