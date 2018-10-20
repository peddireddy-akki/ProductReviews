package com.akki.buyer.model;

import java.util.ArrayList;
import java.util.List;

public class Buyer {
	private long buyerID;
	private String firstName;
	private String lastName;
	private List<Address> addresses;
	
	public Buyer(Long buyerID, String firstName, String lastName) {
		super();
		this.buyerID = buyerID;
		this.firstName = firstName;
		this.lastName = lastName;
		addresses = new ArrayList();
	}
	public long getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(long buyerID) {
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
	public void addAddress(Address address) {
		addresses.add(address);
		
	}
	
	public List<Address> getAddresses()
	{
		return addresses;
	}


}
