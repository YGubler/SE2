package main;


public class Main {

	public static void main(String[] args) {

		int capacity = 3;

		PrimitiveIntStack stack = new IntStackImpl(capacity);
		
		for (int i = 1; i <= capacity; i++) {
			stack.push(i);
			System.out.println("Integer " + i + " pushed on to stack.");
		}
		for (int i = capacity; i >= 1; i--) {
			System.out.println("Integer " + stack.pop() + " popped out of stack.");
		}
	}


}
