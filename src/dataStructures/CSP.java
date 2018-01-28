/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */

package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSP {

	private String name = new String();
	private List<Constraint> constraints;
	private List<Variable> variables;
	
	public CSP(String name, List<Constraint> constraints, List<Variable> variables){
		this.name			= name;
		this.constraints 	= new ArrayList<Constraint>(constraints);
		this.variables 		= new ArrayList<Variable>(variables);
	}
	
	public Variable findVariable(String variableName){
		
		for(Variable v : variables){
			if(v.getName() == variableName){
				return v;
			}
		}
		
		return null;
	}
	
	public List<Variable> findNeighbors(Variable v){
		
		List<Variable> neighbors = new ArrayList<Variable>();
		
		for(Constraint c : constraints){
			if( c.getScope().contains(v)){
				neighbors.addAll(c.getScope());
				neighbors.remove(v);
			}
		}
			
		
		
		return neighbors;
	}
	
	public List<Constraint> findRelatedConstraints(Variable v){
		List<Constraint> relatedConstraints = new ArrayList<Constraint>();
		
		for(Constraint c : constraints){
			if(c.getScope().contains(v)){
				relatedConstraints.add(c);
			}
		}
		
		return relatedConstraints;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("Instance name: " + name + "\n");
		sb.append("Variables:\n");
		for(Variable v : this.variables){
			sb.append(v.toString()+", constraints: "+this.rConToString(v)+", Neighbors: " + this.neighborsToString(v)+ "\n");
		}
		sb.append("Constraints:\n");
		for(Constraint c : this.constraints){
			sb.append(c.toString()+ "\n");
		}
		
		return sb.toString();
	}
		
	public String neighborsToString(Variable v){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		
		for(Variable n : this.findNeighbors(v)){
			sb.append(n.getName()+",");
		}
		
		sb.deleteCharAt(sb.length() -1);
		sb.append("}");
		
		return sb.toString();
	}
	
	public String rConToString(Variable v){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		
		for(Constraint c : this.findRelatedConstraints(v)){
			sb.append(c.getName()+",");
		}
		
		sb.deleteCharAt(sb.length() -1);
		sb.append("}");
		
		return sb.toString();
	}
	
}

