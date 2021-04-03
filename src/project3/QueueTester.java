package project3;


public class QueueTester<E> {

	public static void main(String[] args) {
		MyQueue q =	new MyQueue();
		MyQueue a = new MyQueue();
		
		a.enqueue(1);
		a.dequeue();
		a.enqueue(1);
		a.enqueue(2);
		a.enqueue(3);
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.enqueue(1);
		System.out.println(a);
		
	}

}
