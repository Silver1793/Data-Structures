package project3;

public class LinkedListTester {

	public static void main(String[] args) {
		MyStack list = new MyStack();
		MyStack list2 = new MyStack();
		list.push("hi");
		list.push("pie");
		list.push("bye");
		list.push("my");
		list.push("k");
		list.push("m");
		list.push("m1");
		list.push("m2");
		list.push("m4");
		
		list.push(1);
		
		System.out.println(list);
	}

}
