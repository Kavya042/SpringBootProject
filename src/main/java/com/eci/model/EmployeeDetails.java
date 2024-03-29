package com.eci.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeDetails")
public class EmployeeDetails {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "first_name", nullable=false)
	private String firstName;

	@Column(name = "last_name", nullable=false)
	private String lastName;

	@Column(name = "email", nullable=false)
	private String email;

	public EmployeeDetails() {

	}
	public EmployeeDetails(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
