package com.sample.rest.user;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private String emailAddress;

	public User() {
	}

	public void updateSelf(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.emailAddress = user.getEmailAddress();
	}

	public User(Long id, String firstName, String lastName, String emailAddress) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + "]";
	}
	
}
