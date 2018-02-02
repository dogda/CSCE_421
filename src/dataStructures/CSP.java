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

import abscon.instance.components.PConstraint;
import abscon.instance.components.PExtensionConstraint;
import abscon.instance.components.PIntensionConstraint;
import abscon.instance.components.PVariable;
import abscon.instance.tools.InstanceParser;

public class CSP {

	private String name = new String();
	private List<Constraint> constraints;
	private List<Variable> variables;
	
	public CSP(String name, List<Constraint> constraints, List<Variable> variables){
		this.name			= name;
		this.constraints 	= new ArrayList<Constraint>(constraints);
		this.variables 		= new ArrayList<Variable>(variables);
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<Constraint> getConstraints() {
		return constraints;
	}



	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}



	public List<Variable> getVariables() {
		return variables;
	}



	public void setVariables(List<Variable> variables) {
		this.variables = variables;
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
	
	public Constraint getBinaryConstraint(Variable a, Variable b){
		List<Variable> scope = new ArrayList<Variable>();
		scope.add(a);
		scope.add(b);
		
		for(int i = 0; i < this.constraints.size(); i++){
			if(this.constraints.get(i).getScope().equals(scope)){
				return this.constraints.get(i);
			}
		}
		
		return null;
	}
	
	public Constraint getUnaryConstraint(Variable a){
		List<Variable> scope = new ArrayList<Variable>();
		
		scope.add(a);
		
		for(int i = 0; i < this.constraints.size(); i++){
			if(this.constraints.get(i).getScope().equals(scope)){
				return this.constraints.get(i);
			}
		}
		
		return null;
	}
	
	public static CSP createCSPFromFile(String file){
		InstanceParser ip = new InstanceParser();
		ip.loadInstance(file);
		ip.parse(false);
		
		List<Variable> variables 		= new ArrayList<Variable>();
		List<Domain> domains 			= new ArrayList<Domain>();
		List<Constraint> constraints 	= new ArrayList<Constraint>();
		
		for(int i = 0; i < ip.getVariables().length; i++){
			PVariable v = ip.getVariables()[i];
			Domain d = new Domain(v.getDomain().getName(),v.getDomain().getValues());
			domains.add(d);
			variables.add(new Variable(v.getName(), d));
		}
		
		Map<String, PConstraint> map = ip.getMapOfConstraints();
		
		for (Map.Entry<String, PConstraint> entry : map.entrySet()) {
		    PConstraint c = entry.getValue();
		    Constraint con;
		    
	    	ArrayList<Variable> scope = new ArrayList<Variable>();
	    	for(PVariable v : c.getScope()){
	    		for(Variable var : variables){
	    			if(v.getName() == var.getName()){
	    				scope.add(var);
	    			}
	    		}
	    	}
		    
		    if(c instanceof PExtensionConstraint){
		    	ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		    	for(int[] t : ((PExtensionConstraint) c).getRelation().getTuples()){
	    			tuples.add(new Tuple(t));
		    	}
		    	con = new ExtensionConstraint(c.getName(), scope, tuples, ((PExtensionConstraint) c).getRelation().getSemantics());
		    } else if (c instanceof PIntensionConstraint) {
		    	con = new IntensionConstraint(c.getName(), scope, ((PIntensionConstraint) c).getFunction().getUniversalPostfixExpression(), ((PIntensionConstraint) c).getFunction().getFormalParameters());
		    } else {
		    	con = null;
		    }
		    constraints.add(con);
		}
		
	    return new CSP(trimFileName(file), constraints, variables);
	}
	
	public static String trimFileName(String file){
		for(int i = file.length() - 1; i >= 0; i--){
			if(file.charAt(i) == '/'){
				return file.substring(i+1, file.length()-4);
			}
		}
		return file;
	}
	
}

