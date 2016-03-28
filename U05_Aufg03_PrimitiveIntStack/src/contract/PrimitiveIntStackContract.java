package contract;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.old;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import static de.vksi.c4j.Condition.result;
import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.PureTarget;
import de.vksi.c4j.Target;
import main.PrimitiveIntStack;

public class PrimitiveIntStackContract implements PrimitiveIntStack {

	@Target
	private PrimitiveIntStack target;

	@ClassInvariant
	public void classInvariant() {
		if (target.isEmpty()) {
			assert !target.isFull();
		}
		if (target.isFull()){
			assert !target.isEmpty();
		}
	}
		
	@Override
	public void push(int value) {
		if(preCondition()) {
			assert !target.isFull();
		}
		if (postCondition()) {
			assert !target.isEmpty();
			assert target.top() == value;
		}
	}

	@Override
	public int pop() {
		if (preCondition()) {
			assert !target.isEmpty();
		}
		if (postCondition()) {
			assert !target.isFull();
			assert result() == old(target.top());
		}
		return ignored();
		
	}

	@Override
	@PureTarget
	public int top() {
		if(preCondition()){
			assert !target.isEmpty();
		}
		return ignored();
	}

	@Override
	@PureTarget
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return ignored();
	}

	@Override
	@PureTarget
	public boolean isFull() {
		// TODO Auto-generated method stub
		return ignored();
	}

}
