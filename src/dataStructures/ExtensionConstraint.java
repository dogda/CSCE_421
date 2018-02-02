/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */

package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class ExtensionConstraint extends Constraint {
	
	private List<Tuple> tuples = new ArrayList<Tuple>();
	private String type;
	
	public ExtensionConstraint(String name, List<Variable> scope, List<Tuple> tuples, String type) {
		super(name, scope);
		this.tuples = tuples;
		this.type = type;
	}

	public String getStringDefinition(){
		StringBuilder result = new StringBuilder();

		result.append("[");
		for(Tuple p : this.tuples){
			result.append(p.toString()+",");
		}
		
		result.deleteCharAt(result.length()-1);
		result.append("]");
		
		return result.toString();
	}
	
	public String toString(){
		return "Name: " + this.name + ", variables: " + this.getScopeString() + ", definition: " + this.type + " " + this.tuples.toString();
	}
	
	public boolean allowed(Tuple t){
		boolean supports = (this.type.equals("supports"));
		for(int i = 0; i < this.tuples.size(); i++){
			//System.out.println(supports);

			if(this.tuples.get(i).equals(t)){
				return supports;
			}
		}
		return !supports;
	}
	
	public void reverseEdge(){
		
	}

}
