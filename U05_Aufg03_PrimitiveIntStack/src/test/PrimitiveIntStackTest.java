package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.IntStackImpl;
import main.PrimitiveIntStack;

public class PrimitiveIntStackTest {

	/*
	 * under Runconfig VM dies hineinkopieren damit C4J läuft -ea -javaagent:
	 * "C:\Users\Yanick\Dropbox\Schule\HSR\Module\SE2\FS2016\00_Eigene\Workspace\U05_Aufg03_PrimitiveIntStack\libs\c4j-6.0.0-for-java-8.jar"
	 * 
	 */

	private PrimitiveIntStack stack;

	@Before
	public void setUp() {
		stack = new IntStackImpl(2);
	}

	@Test
	public void testPushPop() {
		stack.push(1);
		stack.push(2);
		assertEquals(2, stack.pop());
		assertEquals(1, stack.pop());
	}

	@Test(expected = AssertionError.class)
	public void testPushFull() {
		stack.push(1);
		stack.push(1);
		stack.push(1);
	}

	@Test(expected = AssertionError.class)
	public void testPopEmpty() {
		stack.pop();
	}

	@Test
	public void testTop() {
		stack.push(1);
		assertEquals(1, stack.top());
		assertEquals(1, stack.top());
	}

	@Test(expected = AssertionError.class)
	public void testTopEmpty() {
		stack.top();
	}

	@Test
	public void testIsFull() {
		assertFalse(stack.isFull());
		stack.push(1);
		assertFalse(stack.isFull());
		stack.push(1);
		assertTrue(stack.isFull());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(stack.isEmpty());

		stack.push(1);
		assertFalse(stack.isEmpty());

		stack.push(1);
		assertFalse(stack.isEmpty());

		stack.pop();
		assertFalse(stack.isEmpty());

		stack.pop();
		assertTrue(stack.isEmpty());
	}

	@Test(expected = AssertionError.class)
	public void testClassVariant() {
		PrimitiveIntStack dummy = new PrimitiveIntStack() {
			@Override
			public int top() {
				return stack.top();
			}

			@Override
			public void push(int value) {
				stack.push(value);
			}

			@Override
			public int pop() {
				return stack.pop();
			}

			@Override
			public boolean isFull() {
				return true;
			}

			@Override
			public boolean isEmpty() {
				return true;
			}
		};
		dummy.isFull();
	}

}
