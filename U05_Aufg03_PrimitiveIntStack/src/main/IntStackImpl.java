package main;


public class IntStackImpl implements PrimitiveIntStack {
		private int[] values;
		private int topIndex = -1;

		public IntStackImpl(int size) {
			values = new int[size];
		}

		public void push(int value) {
			values[++topIndex] = value;
		}

		public int pop() {
			return values[topIndex--];
		}

		public int top() {
			return values[topIndex];
		}

		public boolean isEmpty() {
			return topIndex == -1;
		}

		public boolean isFull() {
			return topIndex == (values.length - 1);
		}
}
