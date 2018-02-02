package solve;

import dataStructures.CSP;

public class Main {

	public static void main(String args[]){
		CSP c = null;
		
		Analyzer a = new Analyzer();
		ACManager acm = new ACManager(a);
		
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-f") && i < args.length-1){
				c = CSP.createCSPFromFile(args[i+1]);
			}
		}
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-a") && i < args.length-1){
				if(args[i+1].equals("ac1")){
					//Analyze code here?
					a.start();
					acm.ac_1(c);
					a.end();
					System.out.println(a.results());
				}
			}
		}	
	}
}
