/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */

package dataStructures;

public class Pair {

	private int a1;
	private int a2;
	
	public Pair(int a1, int a2) {
		super();
		this.a1 = a1;
		this.a2 = a2;
	}
	
	public int getFirstValue() {
		return a1;
	}
	
	public void setFirstValue(int a1) {
		this.a1 = a1;
	}
	
	public int getSecondValue() {
		return a2;
	}
	
	public void setSecondValue(int a2) {
		this.a2 = a2;
	}
	
	public String toString(){
		return "("+a1+","+a2+")";
	}
	
}
