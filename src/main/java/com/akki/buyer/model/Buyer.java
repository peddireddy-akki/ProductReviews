package com.akki.buyer.model;

public class Buyer {
	private Long buyerID;
	private String firstName;
	private String lastName;
	
	public Buyer(Long buyerID, String firstName, String lastName) {
		super();
		this.buyerID = buyerID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Long getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(Long buyerID) {
		this.buyerID = buyerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
