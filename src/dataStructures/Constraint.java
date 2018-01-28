/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */


package dataStructures;

import java.util.ArrayList;
import java.util.List;

public abstract class Constraint {

	protected String name = new String();
	protected List<Variable> scope = new ArrayList<Variable>();
	
	public Constraint(String name, List<Variable> scope) {
		super();
		this.name = name;
		this.scope = scope;
	}
	
	public String getScopeString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		
		for(int i = 0; i < this.scope.size(); i++){
			sb.append(this.scope.get(i).getName());
			if(i != this.scope.size()-1){
				sb.append(",");
			}
		}
		
		sb.append("}");
		
		return sb.toString();
	}

	public String getStringDefinition(){
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Variable> getScope() {
		return scope;
	}

	public void setScope(List<Variable> scope) {
		this.scope = scope;
	}
}
