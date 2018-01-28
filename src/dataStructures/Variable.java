/*
 * Kaleb Gaar
 * 1/25/2018
 * CSCE 421
 */

package dataStructures;

public class Variable {

	private String name = new String();
	private Domain currentDomain;
	private Domain initialDomain;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Domain getCurrentDomain() {
		return currentDomain;
	}
	public void setCurrentDomain(Domain currentDomain) {
		this.currentDomain = currentDomain;
	}
	public Variable(String name, Domain initialDomain) {
		this.name = name;
		this.initialDomain = initialDomain;
		this.currentDomain = initialDomain.returnClone();
	}
	
	public String toString(){
		return "Name: " + this.name + ", initial domain: " + this.initialDomain.toString();
	}

}
