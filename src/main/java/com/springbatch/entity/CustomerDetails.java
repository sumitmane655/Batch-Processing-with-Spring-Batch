package com.springbatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_details")

public class CustomerDetails {
	@Id
	private int id;
	private String customerId;
	private String firstName;
	private String lastName;
	private String company;
	private String city;
	private String country;
	private String phone1;
	private String phone2;
	private String subscriptionDetails;
	
	
	public CustomerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CustomerDetails(int id, String customerId, String firstName, String lastName, String company, String city,
			String country, String phone1, String phone2, String subscriptionDetails) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.city = city;
		this.country = country;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.subscriptionDetails = subscriptionDetails;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPhone1() {
		return phone1;
	}


	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}


	public String getPhone2() {
		return phone2;
	}


	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}


	public String getSubscriptionDetails() {
		return subscriptionDetails;
	}


	public void setSubscriptionDetails(String subscriptionDetails) {
		this.subscriptionDetails = subscriptionDetails;
	}


	@Override
	public String toString() {
		return "CustomerDetails [id=" + id + ", customerId=" + customerId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", company=" + company + ", city=" + city + ", country=" + country + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", subscriptionDetails=" + subscriptionDetails + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
