package project2;
/**
 * Represents a MeteoriteLinkedList that adds and removes elements to the linkedlist and can also copy from an array list
 * @author Richard
 *@version 1.56
 *@since 1.00
 */
public class MeteoriteLinkedList {
	
	private Node head;
	//private Node tail;
	
	private int size;
	//private MeteoriteLinkedList metLinkedList;
	/**
	 * Creates a meteorite linked list without taking any parameters
	 */
	public MeteoriteLinkedList()
	{
		//metLinkedList = this;
		head = new Node(null);
		//tail = null;
		size = 0;
	}
	/**
	 * Creates a meteorite linked list that will add all of the elements from the MeteoriteList parameter
	 * 
	 * @param list takes in a MeteoriteList object that will be copied to the MeteoriteLinkedList object
	 */
	public MeteoriteLinkedList(MeteoriteList list)
	{
		//Node current = head;
		if(list == null)
			throw new IllegalArgumentException("List is not available");
		//metLinkedList = this;
		for(Meteorite m : list)
			this.add(m);
	}
	/**
	 * This methods adds a Meteorite object to the linkedList.
	 * If the linkedlist is empty it will add the parameter to the begining
	 * If the linkedlist is not empty it will add the parameter in a sorted way into the list based on the objects name and ID
	 * If the linkedlist already has the same object, the object will not be added and the method will return null
	 * 
	 * @param m takes in a Meteorite parameter that will be added
	 * @return returns true if the linked list changes and false if nothing is changed
	 * @throw IllegalArgumentException if the parameter is null and prints "input cannot be null"
	 */
	public boolean add(Meteorite m)
	{
		if(m == null) 
			throw new IllegalArgumentException("input cannot be null");
		Node current = head;
		Node insert = new Node(m);
		insert.data = new Node(m).data;
		for(int i = 0; i < size; i++)
		{
			if(current != null)
			if(current.data.equals(m))
			{
				return false;
			}
			current = current.next;
		}
		
		current = head;
		if(size == 0)
		{
			head = insert;
			//tail = insert;
			insert = head.next;
			size++;
			return true;
		}
			current = head;
			if(insert.compareTo(current) == -1)
			{
				insert.next = current;
				head = insert;
				size++;
				return true;
			}
		else
		{
			current = head;
		while(current.next != null)
		{
			if(insert.compareTo(current.next) == 1)
			{
				current = current.next;
			}
			else
				break;
		}
		insert.next = current.next;
		current.next = insert;
		size++;
		while(current.next!= null)
			current = current.next;
		//tail = current;
		return true;
		}
	}
	/**
	 * The remove method checks to see if any of the Meteorite objects in the meteoriteLinkedList have the same name and id as the 
	 * provide parameters. If they do, this object will be removed and returned.
	 * 
	 * @param name the string that will be checked to see if it is equal to the name of the Meteorite object
	 * @param id the value that will be checked to see if it is equal to the ID of a Meteorite object
	 * @return returns null if nothing was removes or returns the data of the Meteorite object that was removed
	 */
	public Meteorite remove(String name, int id)
	{
		Node current = head;
		Node temp = head;

		if(size == 0)
		{
			return null;
		}
		if(current!=null && current.data.getName() == name && current.data.getId() == id)
		{
			head = current.next;
			size--;
			return current.data;
		}
		while(current != null && (current.data.getName() != name || current.data.getId() != id))
			{
				temp = current;
				current = current.next;
			}
		if(current == null)
			return null;
		temp.next = current.next;
		size--;
		return current.data;
	}
	/**
	 * Goes through the linkedlist and adds the data of each element in the linked list to a string
	 * 
	 * @return a string of all the elements in the linkedlist 
	 * 		   Will return null if the list is empty
	 */
	public String toString()
	{
		Node current = head;
			String ret = "";
			if(current.data == null)
				return "There are no values in the list";
			while(current != null)
			{
				ret += current.data + "\n";
				current = current.next;
			}
			return ret;
	}
	
	
	private class Node implements Comparable<Node> {
		Meteorite data;
		Node next;

		Node (Meteorite data) {
			this.data = data;
		}

		public String toString() {
			return data.toString();
		}

		public boolean equals( Object o ) {
			if (this == o) return true;
			if (!(o instanceof Node)) {
				return false;
			}
			Node other = (Node) o;
			if (!this.data.equals(other.data)) {
				return false;
			}
			return true;
		}

		public int compareTo (Node n) {
			return data.compareTo(n.data);
		}
	}
}