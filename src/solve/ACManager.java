package solve;

import java.util.List;

import dataStructures.*;

public class ACManager {
	
	Analyzer analyzer;
	
	public ACManager(Analyzer analyzer) {
		super();
		this.analyzer = analyzer;
	}

	public void makeNodesConsistent(CSP csp){
		for(Variable v: csp.getVariables()){
			
			Constraint unary = csp.getUnaryConstraint(v);
			this.analyzer.incrementCC();
			
			if(unary != null){
				for(int i: v.getCurrentDomain().getValues()){
					if( !unary.allowed(new Tuple(new int[]{i}))){
						v.getCurrentDomain().deleteValue(i);
					}
				}
			}
			
			if(v.getCurrentDomain().getValues().length == 0){
				break;
			}
		}
	}
	
	public boolean revise(CSP csp, Variable a, Variable b){

		Constraint c_1 = csp.getBinaryConstraint(a, b);
		Constraint c_2 = csp.getBinaryConstraint(b, a);
		Constraint c;
		
		boolean revised = false;
		boolean reversed = false;
		int domain[];
		
		if(c_1 == null && c_2 == null){
			return false;
		} else if (c_1 != null){
			domain = a.getCurrentDomain().getValues();
			c = c_1;
		} else {
			domain = b.getCurrentDomain().getValues();
			reversed = true;
			c = c_2;
		}
			
		for(int i = 0; i < domain.length; i++){
			if(!supported(c, domain[i], reversed)){
				a.getCurrentDomain().deleteValue(domain[i]);
				revised = true;
			}
			//System.out.println(a.toString());
		}

		return revised;
	}
	
	public boolean supported(Constraint c, int v, boolean reversed){
		
		int domain_s[];
		if(reversed){
			domain_s = c.getScope().get(0).getCurrentDomain().getValues();
		} else {
			domain_s = c.getScope().get(1).getCurrentDomain().getValues();	
		}
		this.analyzer.incrementCC();
		
		for(int i = 0; i < domain_s.length; i++){
			if(check(c, v, domain_s[i], reversed)){
				return true;
			}
		}
		return false;
	}
	
	public boolean check(Constraint c, int a_v, int b_v, boolean reversed){
		if(!reversed){
			return c.allowed(new Tuple(new int[]{a_v, b_v}));
		} else {
			return c.allowed(new Tuple(new int[]{b_v, a_v}));
		}
		
	}
	
	public boolean ac_1(CSP csp){
		makeNodesConsistent(csp);
		List<Variable> v = csp.getVariables();
		ACQueue a = createQueue(v);
		boolean changed = false;
		
		do{
			changed = false;
			for(int i = 0; i < a.getLength(); i++){
				Variable[] varPair = a.getIndex(i);
				boolean updated = revise(csp, varPair[0], varPair[1]);
				if(varPair[0].getCurrentDomain().getValues().length == 0){
					return false;
				} else {
					changed = updated || changed;
				}
			}
		} while(!changed);
		
		return true;
	}
	
	public ACQueue createQueue(List<Variable> v){
		ACQueue a = new ACQueue();
		
		for(Variable i : v){
			for(Variable j : v){
				if(i != j){
					a.addToQueue(new Variable[]{i, j});
				}
			}	
		}
		
		return a;
	}
	
}
