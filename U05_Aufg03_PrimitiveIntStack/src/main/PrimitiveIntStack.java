package main;

import contract.PrimitiveIntStackContract;
import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(PrimitiveIntStackContract.class)
public interface PrimitiveIntStack {

	public void push(int value);

	public int pop();

	@Pure
	public int top();
	
	@Pure
	public boolean isEmpty();
	
	@Pure
	public boolean isFull();
	
}
