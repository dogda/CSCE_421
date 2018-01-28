/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */

package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class ExtensionConstraint extends Constraint {
	
	private List<Pair> tuples = new ArrayList<Pair>();
	private String type;
	
	public ExtensionConstraint(String name, List<Variable> scope, List<Pair> tuples, String type) {
		super(name, scope);
		this.tuples = tuples;
		this.type = type;
	}

	public String getStringDefinition(){
		StringBuilder result = new StringBuilder();

		result.append("[");
		for(Pair p : this.tuples){
			result.append(p.toString()+",");
		}
		
		result.deleteCharAt(result.length()-1);
		result.append("]");
		
		return result.toString();
	}
	
	public String toString(){
		return "Name: " + this.name + ", variables: " + this.getScopeString() + ", definition: " + this.type + " " + this.tuples.toString();
	}

}
