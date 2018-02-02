package solve;

import java.util.LinkedList;
import java.util.List;

import dataStructures.Constraint;
import dataStructures.Variable;

public class ACQueue {

	private List<Variable[]> queue = new LinkedList<Variable[]>();
	
	public void addToQueue(Variable[] v){
		this.queue.add(this.queue.size(), v);
	}
	
	public Variable[] pop(){
		Variable[] v = this.queue.get(0);
		this.queue.remove(0);
		return v;
	}
	
	public Variable[] getIndex(int index){
		return this.queue.get(index);
	}
	
	public int getLength(){
		return this.queue.size();
	}
	
}
