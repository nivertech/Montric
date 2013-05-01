package org.eurekaj.api.datatypes.basic;

import org.eurekaj.api.datatypes.User;

public class BasicUser implements User {
	private String userName;
	private String accountName;
	private String userRole;
	private String firstName;
	private String lastName;
	private String company;
	private String country;
	private String usage;
	private String id;
	
	public BasicUser() {
	
	}
	
	public BasicUser(User user) {
		this(
				user.getUserName(), 
				user.getAccountName(), 
				user.getUserRole(),
				user.getFirstname(),
				user.getLastname(),
				user.getCompany(),
				user.getCountry(),
				user.getUsage(),
				user.getUserName());
	}
	
	public BasicUser(String userName, String accountName, String userRole,
			String firstName, String lastName, String company, String country,
			String usage, String id) {
		super();
		this.userName = userName;
		this.accountName = accountName;
		this.userRole = userRole;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.country = country;
		this.usage = usage;
		this.id = id;
	}

	@Override
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String username) {
		this.userName = username;
	}

	@Override
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Override
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getCompany() {
		return this.company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Override
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String getFirstname() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getLastname() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String getUsage() {
		return usage;
	}
	
	public void setUsage(String usage) {
		this.usage = usage;
	}
}
