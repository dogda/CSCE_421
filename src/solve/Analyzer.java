package solve;

public class Analyzer {

	
	private long startTime;

	private long endTime;
	
	private int constraintComparisions = 0;
	
	public void start(){
		this.startTime = System.nanoTime();
	}
	
	public void end(){
		this.endTime = System.nanoTime();
	}
	
	public String results(){
		return "Time Taken: "+ (this.endTime - this.startTime)+ "\nConstraints Compared: "+this.constraintComparisions;
	}
	
	public void incrementCC(){
		this.constraintComparisions++;
	}
	

	/*• problem:
	• cpu-time: 
	• cc: 
	• fval: 
	• iSize: 
	• fSize:
	• fEffect:*/
	
}
