/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */

package dataStructures;

import java.util.List;

public class IntensionConstraint extends Constraint {

	private String[] function;
	private String[] parameters;

	public IntensionConstraint(String name, List<Variable> scope, String[] function, String[] parameters) {
		super(name, scope);
		this.function = function;
		this.parameters = parameters;
	}
	
	public String getStringDefinition(){
		StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0; i < function.length; i++){
			sb.append(this.function[i] + " ");
		}
		
		return sb.toString();
	}
	
	public String getStringParameters(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		
		for(String p : this.parameters){
			sb.append(p+",");
		}
		
		sb.deleteCharAt(sb.length() -1);
		sb.append("}");
		
		return sb.toString();
	}
	
	public String toString(){
		return "Name: " + this.name + ", variables: " + this.getScopeString() + ", definition: intension, post-fix [ " + this.getStringDefinition()+"], params: " + this.getStringParameters();
	}
	
	
}
