/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */

package dataStructures;

public class Tuple {

	private int values[];
	
	public int[] getValues() {
		return values;
	}

	public void setValues(int[] values) {
		this.values = values;
	}

	public Tuple(int values[]) {
		super();
		this.values = values;
	}
	
	public int getValue(int index){
		return this.values[index];
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("(");
		for(int i = 0; i < this.values.length; i++){
			result.append(this.values[i]);
			if(i != this.values.length - 1){
				result.append(",");
			}
		}
		result.append(")");
		return result.toString();
	}
	
	public boolean equals(Tuple t){
		boolean result = true;
		
		if(this.values.length == t.getValues().length){
			//System.out.print("( ");
			for(int i = 0; i < this.values.length; i++){
				if(this.values[i] != t.getValues()[i]){
					result = false;
				}
				
				//System.out.print(this.values[i] + "-" + t.getValues()[i]+",");
			}
			//System.out.print(") ");
		} else {
			result = false;
		}
		//System.out.println(result);
		return result;
	}
	
}
