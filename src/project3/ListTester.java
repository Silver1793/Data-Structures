package project3;

import project2.Meteorite;

public class ListTester {

	public static void main(String[] args) {
		// LIST TESTING
        System.out.println("-----------------------LIST");
        MyList<Meteorite> mList = new MyList<>();
        System.out.println(mList.size());
        System.out.println("Above should be 0, size of empty list.");
        mList.add(new Meteorite("meteorite1", 1), 0);
        System.out.println(mList);
        System.out.println("Above should be \"meteorite1\", list with 1 object inside.");
        System.out.println(mList.remove(0));
        System.out.println("Above should be \"meteorite1\", removing object at [0].");
        
        mList.add(new Meteorite("meteorite1", 1), 0);
        mList.add(new Meteorite("meteorite2", 1), 1);
        System.out.println("The list looks like: " + mList);
        System.out.println(mList.size());
        System.out.println("Above should be 2, size of list with stuff inside.");
        System.out.println(mList.get(0));
        System.out.println("Above should be meteorite1, content at [0].");
        System.out.println(mList.find(new Meteorite("meteorite1", 1)));
        System.out.println("Above should be 0, location of meteorite1.");
        System.out.println(mList.remove(new Meteorite("meteorite3", 1)));
        System.out.println("Above should be null, trying to remove something that's not there.");
        System.out.println(mList.remove(new Meteorite("meteorite1", 1)));
        System.out.println("Above should be meteorite1, trying to remove something that is there.");
        mList.clear();
        System.out.println(mList.remove(new Meteorite("meteorite1", 1)));
        System.out.println("Above should be null, trying to remove something from nothing.");
        mList.clear(); // to test clearing empty list
        System.out.println(mList);
        System.out.println("Above should be an empty line, trying to print empty list.");
        System.out.println(mList.size());
        System.out.println("Above should be 0, size of empty list.");
        
        try {
            System.out.println(mList.get(7));
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Above should be an error, trying to get from invalid pos.");
        try {
            System.out.println(mList.add(new Meteorite("meteorite1", 1), 70));
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Above should be an error, trying to add to invalid pos.");
        try {
            System.out.println(mList.remove(75));
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Above should be an error, trying to remove from invalid pos.");
        System.out.println(mList.find(new Meteorite("meteorite1", 1)));
        System.out.println("Above should be -1, testing find on empty list.");
        System.out.println(mList.find(new Meteorite("meteorite50", 1)));
        System.out.println("Above should be -1, finding item not in list.");
        mList.clear();
        mList.add(new Meteorite("meteorite5", 1), 0);
        System.out.println(mList.find(new Meteorite("meteorite5", 1)));
        System.out.println(mList.find(new Meteorite("meteorite6", 1)));
        System.out.println("Above should be 0, then -1, testing find on list size 1");
        mList.add(new Meteorite("meteorite7", 1), 0);
        mList.add(new Meteorite("meteorite13", 1), 0);
        mList.add(new Meteorite("meteorite24", 1), 0);
        System.out.println(mList.find(new Meteorite("meteorite7", 1)));
        System.out.println("Above should be 2, testing find in middle of list.");
        mList.clear();
        mList.add(new Meteorite("meteorite7", 1), 0);
        mList.add(new Meteorite("meteorite15", 1), 1);
        mList.add(new Meteorite("meteorite20", 1), 0);
        mList.add(new Meteorite("meteorite5", 1), 3);
        System.out.println("List looks like: " + mList);
        System.out.println(mList.remove(3));
        System.out.println("Above should be meteorite5, remove from pos 3 of list len 4");
        mList.add(new Meteorite("meteorite5", 1), 3);
        System.out.println(mList.remove(2));
        System.out.println("Above should be meteorite15, remove from pos 2 of list len 4");
        mList.add(new Meteorite("meteorite15", 1), 2);
        System.out.println(mList.remove(new Meteorite("meteorite15", 1)));
        System.out.println("Above should be meteorite15, remove meteorite15 from pos 2 in list len" +
                " 4");
        mList.add(new Meteorite("meteorite15", 1), 2);
        System.out.println(mList.remove(new Meteorite("meteorite5", 1)));
        System.out.println("Above should be 5, remove meteorite5 from pos 3 in list len " +
                "4");
 
        mList.clear();
        mList.add(new Meteorite("meteorite10", 1), 0);
        mList.add(new Meteorite("meteorite15", 1), 1);
        mList.add(new Meteorite("meteorite5", 1), 0);
        mList.add(new Meteorite("meteorite5", 1), 3);
        System.out.println(mList);
        System.out.println("Above should be 5, 10, 15, 5");
        System.out.println(mList.get(0));
        System.out.println("Above should be 5, get from pos 0");
        System.out.println(mList.get(2));
        System.out.println("Above should be 15, get from pos 2");

        try {
            System.out.print("What about pos 7: ");
            System.out.println(mList.add(new Meteorite("meteorite5", 1), 10));
            System.out.println(mList.get(7));
        } catch (Exception e){
            System.out.println(e);
        }
        MyList<Meteorite> mList2 = new MyList<Meteorite>();
        mList2.add(new Meteorite("meteorite10", 1), 0);
        mList2.add(new Meteorite("meteorite15", 1), 1);
        mList2.add(new Meteorite("meteorite5", 1), 0);
        mList2.add(new Meteorite("meteorite5", 1), 3);
        System.out.println("There's a new identical list: " + mList2);
        System.out.println(mList.equals(mList2));
        System.out.println("Above should be true, equals on 2 identical lists.");
        mList2.remove(0);
        System.out.println(mList.equals(mList2));
        System.out.println("Above should be false, equals on 2 different lists, a.len > b.len.");
        mList2.add(new Meteorite("meteorite5", 1), 0);
        mList2.add(new Meteorite("meteorite5", 1), 0);
        System.out.println(mList.equals(mList2));
        System.out.println("Above should be false, equals on 2 different lists, a.len < b.len.");
        mList.clear();
        mList2.clear();
        mList.add(new Meteorite("meteorite5", 1), 0);
        mList2.add(new Meteorite("meteorite5", 1), 0);
        mList.add(new Meteorite("meteorite8", 1), 0);
        mList2.add(new Meteorite("meteorite7", 1), 0);
        mList.add(new Meteorite("meteorite5", 1), 0);
        mList2.add(new Meteorite("meteorite5", 1), 0);
        System.out.println(mList.equals(mList2));
        System.out.println("Above should be false, equals on 2 different lists, a.len = b.len.");
        MyList<String> strList = new MyList<String>();
        System.out.println(mList.equals(strList));
        System.out.println("Above should be false, equals on 2 lists with diff types.");
        
        
        // STACK TESTING
        System.out.println("-----------------------STACK");
        MyStack<Meteorite> mStack = new MyStack<>();
        System.out.println(mStack);
        System.out.println("Above should be empty line");
        try {
            mStack.push(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Above should be error, pushing null");
        mStack.push(new Meteorite("meteorite4", 1));
        mStack.push(new Meteorite("meteorite5", 1));
        System.out.println(mStack);
        System.out.println("Above should be 4, 5.");
        System.out.println(mStack.top());
        System.out.println(mStack.pop());
        System.out.println("Last 2 numbers should be 5 and 5, testing top and pop.");
        mStack.push(new Meteorite("meteorite5", 1));
        mStack.push(new Meteorite("meteorite6", 1));
        while (mStack.top() != null) {
            mStack.pop();
        }
        System.out.println(mStack.pop());
        System.out.println(mStack.top());
        System.out.println("Should print null and null, testing top and pop on empty stack");
        MyStack<Meteorite> mStack2 = new MyStack<>();
        System.out.println(mStack.equals(mStack2));
        System.out.println("Should print true, testing equals on 2 empty stacks");

        mStack2.push(new Meteorite("meteorite4", 1));
        System.out.println(mStack.equals(mStack2));	
        System.out.println("Should print false, testing equals on 1 empty stack");
        mStack.push(new Meteorite("meteorite4", 1));
        mStack.push(new Meteorite("meteorite5", 1));
        mStack2.push(new Meteorite("meteorite5", 1));
        System.out.println(mStack.equals(mStack2));
        System.out.println("Should print true, testing equals on identical stacks.");
        mStack2.push(new Meteorite("meteorite6", 1));
        mStack.push(new Meteorite("meteorite6", 1));
        mStack.push(new Meteorite("meteorite7", 1));
        System.out.println(mStack.equals(mStack2));
        System.out.println("Should print false, testing equals on stack where a.len > b.len.");
        mStack2.push(new Meteorite("meteorite7", 1));
        mStack2.push(new Meteorite("meteorite8", 1));
        System.out.println(mStack.equals(mStack2));
        System.out.println("Should print false, testing equals on stack where a.len < b.len.");
        mStack.push(new Meteorite("meteorite9", 1));
        System.out.println(mStack.equals(mStack2));
        System.out.println("Should print false, testing equals on stack where a.len = b.len.");
        MyStack<String> strStack = new MyStack<>();
        strStack.push("test");
        System.out.println(mStack.equals(strStack));
        System.out.println("Should print false, testing equals on stacks with diff types.");
        
        
        // QUEUE TESTING
        System.out.println("-----------------------QUEUE");
        MyQueue<Meteorite> mQueue = new MyQueue<>();
        mQueue.enqueue(new Meteorite("meteorite1", 1));
        System.out.println(mQueue.dequeue());
        System.out.println("If printed 1, successfully added and removed.");
        mQueue.enqueue(new Meteorite("meteorite1", 1));
        mQueue.enqueue(new Meteorite("meteorite2", 1));
        mQueue.enqueue(new Meteorite("meteorite3", 1));
        System.out.print(mQueue.dequeue());
        System.out.print(mQueue.dequeue());
        System.out.println(mQueue.dequeue());
        System.out.println("If printed 123, successfully added and removed 3 objs.");
        System.out.println("If printed 1, then 123, passed normal enqueue, dequeue testcases.");
        System.out.println(mQueue.dequeue());
        System.out.println("If printed null, passed empty dequeue testcase.");
        try {
            mQueue.enqueue(null);
        } catch (Exception ignored){}
        mQueue.enqueue(new Meteorite("meteorite1", 1));
        System.out.println(mQueue.peek());
        System.out.println("If printed 1, passed normal peek testcases.");
        System.out.println(mQueue.dequeue());
        System.out.println(mQueue.peek());
        System.out.println("If printed 1 then null, passed empty peek testcase.");
        MyQueue<Meteorite> mQueue2 = new MyQueue<>();
        mQueue.enqueue(new Meteorite("meteorite1", 1));
        mQueue.enqueue(new Meteorite("meteorite2", 1));
        mQueue.enqueue(new Meteorite("meteorite3", 1));
        mQueue2.enqueue(new Meteorite("meteorite1", 1));
        mQueue2.enqueue(new Meteorite("meteorite2", 1));
        mQueue2.enqueue(new Meteorite("meteorite3", 1));
        System.out.println(mQueue.equals(mQueue2));
        System.out.println("If printed true, passed normal equals testcase.");
        mQueue.enqueue(new Meteorite("meteorite4", 1));
        System.out.println(mQueue.equals(mQueue2));
        System.out.println("If printed false, passed a.size > b.size not equals testcase.");
        mQueue2.enqueue(new Meteorite("meteorite5", 1));
        mQueue2.enqueue(new Meteorite("meteorite6", 1));
        System.out.println(mQueue.equals(mQueue2));
        System.out.println("If printed false, passed a.size < b.size not equals testcase.");
        mQueue2.dequeue();
        
        System.out.println(mQueue.equals(mQueue2));
        System.out.println("If printed false, passed a.size == b.size not equals testcase.");
        System.out.println(mQueue);
        System.out.println(mQueue2);
        System.out.println("Two queues should be 1, 2, 3, 4 and 2, 3, 5, 6.");
        for (int i = 0; i < 16; i += 1) {
            mQueue2.enqueue(new Meteorite("meteorite" + i, 1));
        }
        System.out.println(mQueue2);
        System.out.println("Queue should be  2, 3, 5, 6, 0 ... 15, testing grow function " +
                "given starting size is 10.");
        for (int i = 0; i < 16; i += 1) {
            mQueue2.dequeue();
        }
        for (int i = 0; i < 16; i += 1) {
            mQueue2.enqueue(new Meteorite("meteorite" + i, 1));
        }
        for (int i = 0; i < 16; i += 1) {
            mQueue2.dequeue();
        }

        System.out.println(mQueue2);
        System.out.println("Queue should be 12, 13, 14, 15.");

        for (int i = 0; i < 50; i += 1) {
            mQueue2.enqueue(new Meteorite("meteorite" + i, 1));
        }

        System.out.println("Testing \"wrapped\" queues");
        MyQueue<Meteorite> mQueue3 = new MyQueue<>();
        MyQueue<Meteorite> mQueue4 = new MyQueue<>();
        for (int i = 0; i < 13; i += 1) {
            mQueue3.enqueue(new Meteorite("meteorite" + i, 1));
            mQueue4.enqueue(new Meteorite("meteorite" + i, 1));
        }
        for (int i = 0; i < 7; i += 1) {
            mQueue3.dequeue();
            mQueue4.dequeue();
        }
        for (int i = 0; i < 13; i += 1) {
            mQueue3.enqueue(new Meteorite("meteorite" + (i + 13), 1));
            mQueue4.enqueue(new Meteorite("meteorite" + (i + 13), 1));
        }
        MyQueue<Meteorite> mQueue5 = new MyQueue<>();
        for (int i = 0; i < 6; i += 1) {
            mQueue5.enqueue(new Meteorite("meteorite" + i, 1));
        }
        MyQueue<Meteorite> mQueue6 = new MyQueue<>();
        for (int i = 0; i < 19; i += 1) {
            mQueue6.enqueue(new Meteorite("meteorite" + (i + 7), 1));
        }
        
        System.out.println(mQueue3.equals(mQueue4));
        System.out.println("Above should be true, two identical wrapped Queues");
        System.out.println(mQueue3.equals(mQueue5));
        System.out.println("Above should be false, a wraps, b doesnt");
        System.out.println(mQueue5.equals(mQueue4));
        System.out.println("Above should be false, b wraps, a doesnt");
        System.out.println(mQueue3.equals(mQueue6));
        System.out.println("Above should be true, a wraps, b doesnt");
        System.out.println(mQueue6.equals(mQueue4));
        System.out.println("Above should be true, b wraps, a doesnt");
}
}
//1 2 3 4 8 7 6 5