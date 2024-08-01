package com.rahul.ems.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@Column(name = "dept_id")
	private int departmentId;
	@Column(name = "phone_num")
	private long departmentPhoneNumber;
	@Column(length = 30, name = "dept_name")
	private String departmentName;
	@Column(length = 30, name = "location")
	private String departmentLocation;

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	// getter() and setter()

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public long getDepartmentPhoneNumber() {
		return departmentPhoneNumber;
	}

	public void setDepartmentPhoneNumber(long departmentPhoneNumber) {
		this.departmentPhoneNumber = departmentPhoneNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department Id = " + departmentId + " , Department Phone Number = " + departmentPhoneNumber
				+ "\nDepartment Name = " + departmentName + " , Department Location = " + departmentLocation;
	}

}
