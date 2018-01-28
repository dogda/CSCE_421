/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 * 
 * TODO: 
 * 		Get name of CSP
 * 		Actually get it into a form where it can post
 * 		Get functional expression
 */

package parsing;

import dataStructures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abscon.instance.components.*;
import abscon.instance.tools.InstanceParser;

public class createCSP {

	public static void main(String args[]){
		if(args.length == 2 && args[0].equals("-f")){
			System.out.println(createCSPFromFile(args[1]).toString());
		}
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
		    	ArrayList<Pair> tuples = new ArrayList<Pair>();
		    	for(int[] t : ((PExtensionConstraint) c).getRelation().getTuples()){
		    		tuples.add(new Pair(t[0], t[1]));
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
