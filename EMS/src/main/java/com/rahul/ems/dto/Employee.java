package com.rahul.ems.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@Column(name = "emp_id")
	private int employeeId;
	@Column(length = 50, name = "emp_name")
	private String employeeName;
	@Column(name = "date_of_joining")
	private LocalDate dateOfJoining;
	@Column(name = "email", length = 50)
	private String employeeEmail;
	@Column(name = "password", length = 30)
	private String employeePassword;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Address address;

	@ManyToOne
	@JoinColumn
	private Department department;

	@ManyToMany
	@JoinColumn
	private List<Project> projects;

	// getter() and setter()

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Employee Id = " + employeeId + " , Employee Name = " + employeeName + " , Date Of Joining = "
				+ dateOfJoining + "\nEmployee Email = " + employeeEmail + " , Employee Password = " + employeePassword;
	}

}
