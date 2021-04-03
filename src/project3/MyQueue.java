package project3;

/**
 * A queue that implements a queue interface. Allows a user to  enqueue, dequeue and peek at the top element. Uses an array as reference
 * @author Richard
 *
 * @param <E> Allows the queue to be used for all types.
 */
public class MyQueue<E> implements Queue<E> {

	private int front; 
	private int rear;
	private int size;
	private int capacity;
	private Object[] arr;
	/**
	 * The constructor for the MyQueue class. Sets the front and rear to -1, the size to = and the maximum capacity to 10. 
	 * Also creates an array object call arr with the capacity of 10
	 */
	public MyQueue() {
		front = -1;
		rear = -1;
		size = 0;
		capacity = 10;
		arr = new Object[capacity];
	}
	/**
	 * Adds an element to the rear of the queue. Rear will circle to the beginning of the queue if it reaches the capacity of the queue and 
	 * there is space in the beginning
	 * If the queue is full, a new array will be created with twice the capacity, all the elements starting from the front to the rear will be 
	 * added to the queue and then the old queue will be set equal to the new queue
	 * 
	 * @param item is the data of the element that will be added to the queue
	 */
	@Override
	public void enqueue(E item){
		if(item == null)
			throw new IllegalArgumentException("Item cannot be null");
		if(size == 0) { //Checks to see if the queue is empty, if it is it adds the element to the start of the queue
			front = 0; 
			rear = 1;
			arr[front] = item;
			size++;
			return;
		}
		else if (size == capacity) { //Checks to see if the queue is full
			capacity = capacity*2; //Creates a new capacity of twice the amount
			Object[] temp = new Object[capacity]; //Creates a new array with twice the amount of space than the first one
			int count = 0;
			for(int i = front; i < size; i ++) { //Traverses the original queue starting at the front until it reaches the end and adds it to the new queue
				temp[count] = arr[i];
				count++;
			}
			for(int i = 0; i < front; i++) { 
				temp[count] = arr[i];
				count++;
			}

			front = 0; 
			rear = size;
			temp[size] = item;
			arr = temp;
			rear++;
			size++;
			return;
		}
		else 
			arr[rear] = item; //If the list is not empty or not full, adds the element to the rear
			rear = (rear+1)%capacity; //Rear updates
			size++;			
		}
	/**
	 * Removes and returns the element that is at the front of the queue
	 * @return the data of the element that was removed or null is the queue is empty
	 */
	@Override
	public E dequeue() {
		if(size == 0) //Checks to see if the queue is empty
			return null; //If the queue is empty, no elements are dequeued
		if(size == 1) { //Checks to see if the queue has only 1 element in it, if it does, removes the element and sets rear to 1 size to 0 and front to 0
			E temp = (E)arr[front]; 
			front = 0;
			rear = 1;
			size--;
			return temp; //Returns the removed element
		}
		E temp = (E) arr[front]; //If the queue has more than 1 element in it, sets the front element to null and updates front
		arr[front] = null;
		front = (front+1)%arr.length;
		size--; //Size decreases by one
		return temp; //Returns the removed element
	}
	/**
	 * Allows the user to see the element that is at the front of the queue by returning the data of the element that is in front
	 * @return the data of the element that is at the front of the queue
	 */
	@Override
	public E peek() {
		if(size==0)
			return null;
		//System.out.println("reached");
		return (E)arr[front];
	}
	/**
	 * Compares two objects and checks to see if they are both MyQueue objects with the same elements in the same order
	 * 
	 * @param o takes in an object that will be compared to an object of MyQueue
	 * @return true if the two MyQueue objects are equal or false if one of them is not a MyQueue, is of different size or is not the same
	 */
	public boolean equals(Object o) {
		if(o == this) {//Checks to see if the two queues being compared are the same object
			return true;
		}
		if(!(o instanceof MyQueue)) {//Checks to see if the object being compared is of type MyQueue
			return false;
		}
		MyQueue<E> otherQueue = (MyQueue<E>)o;
		
		if(!(otherQueue.size == size))//Compares the sizes of the two queues
			return false;
		if(!(peek().equals(otherQueue.peek()))) { //Compares the front elements of the two queues
			return false;
		}
		
		int num1 = front;
		int num2 = otherQueue.front;
		
		for(int i =0; i < size-1; i++) { //Traverses both queues and compares the elements of both to each other
			if(!(arr[num1].equals(otherQueue.arr[num2]))) {
				return false;
			}
			num1 = (num1+1)%capacity;
			num2 = (num2+1)%otherQueue.capacity;
		}
		return true;
	}
	/**
	 * Creates a string of all the elements in the queue and returns them
	 * @return a string of all of the elements in the queue or "the queue is empty" if the queue is empty
	 */
	public String toString() {
		String ret = "";
		int num = front;
		if(size == 0)
			return "The queue is empty";
		ret = ""+arr[num];
		num = (num+1)%capacity;
		for(int i =0; i< size-1; i++) {
			ret += ", " + arr[num];
			num = (num+1)%capacity;
		}
		return ret;
	}
}
