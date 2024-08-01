package com.rahul.ems.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {

	@Id
	@Column(name = "proj_id")
	private int projectId;
	@Column(name = "name", length = 50)
	private String projectName;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "deadline")
	private LocalDate deadLine;

	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees;

	// getter() and setter()

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Project Id = " + projectId + " , Project Name = " + projectName + "\nStart Date = " + startDate
				+ " , Dead Line = " + deadLine;
	}

}
