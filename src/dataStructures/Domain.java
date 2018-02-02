/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */


package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Domain {

	private String name = new String();
	private int[] values;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getValues() {
		return values;
	}
	public void setValues(int[] values) {
		this.values = values;
	}
	public Domain returnClone(){
		int[] copyValues = new int[values.length];
		
		for(int i = 0; i < values.length; i++){
			copyValues[i] = this.values[i];
		}
		
		return new Domain(this.name, copyValues);
	}
	
	public Domain(String name, int[] values) {
		super();
		this.name = name;
		this.values = values;
	} 
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("{");
		for(int i = 0; i < this.values.length; i++){
			result.append(this.values[i]);
			if(i != this.values.length - 1){
				result.append(",");
			}
		}
		result.append("}");
		return result.toString();
	}
	
	public void deleteValue(int value){
		int[] newValues = new int[this.values.length-1];
		int counter = 0;
		
		for(int i = 0; i < this.values.length; i++){
			if(this.values[i] != value){
				int a = this.values[i];
				newValues[counter] = a;
				counter++;
			}
		}
		
		this.values = newValues;
	}
}
