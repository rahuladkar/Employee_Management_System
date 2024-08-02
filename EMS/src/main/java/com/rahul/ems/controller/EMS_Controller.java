package com.rahul.ems.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rahul.ems.dao.AddressDao;
import com.rahul.ems.dao.DepartmentDao;
import com.rahul.ems.dao.EmployeeDao;
import com.rahul.ems.dao.ProjectDao;
import com.rahul.ems.dto.Address;
import com.rahul.ems.dto.Department;
import com.rahul.ems.dto.Employee;
import com.rahul.ems.dto.Project;

public class EMS_Controller {

	static Scanner scanner = new Scanner(System.in);

	public static Employee addEmployee() {

		Employee employee = new Employee();

		System.out.print("\nEnter The Employee ID : ");
		employee.setEmployeeId(scanner.nextInt());
		scanner.nextLine();

		System.out.print("Enter The Employee Name : ");
		employee.setEmployeeName(scanner.nextLine());

		System.out.print("Enter The Employee Date Of Join (yyyy-mm-dd): ");
		employee.setDateOfJoining(LocalDate.parse(scanner.nextLine()));

		System.out.print("Enter The Employee Email : ");
		employee.setEmployeeEmail(scanner.nextLine());

		System.out.print("Enter The Employee Password : ");
		employee.setEmployeePassword(scanner.nextLine());

		return employee;
	}

	public static Department addDepartment() {

		Department department = new Department();

		System.out.print("\nEnter The Department ID : ");
		department.setDepartmentId(scanner.nextInt());

		System.out.print("Enter The Department Phone Number : ");
		department.setDepartmentPhoneNumber(scanner.nextLong());
		scanner.nextLine();

		System.out.print("Enter The Department Name : ");
		department.setDepartmentName(scanner.nextLine());

		System.out.print("Enter The Department Location : ");
		department.setDepartmentLocation(scanner.nextLine());

		if (DepartmentDao.SaveDepartment(department)) {
			System.out.println("\nData Successfully Inserted...!");
			return department;
		} else {
			System.out.println("\nData Not Inserted...!");
			return null;
		}

	}

	public static List<Project> addProjects() {

		ArrayList<Project> projects = new ArrayList<Project>();

		Project project = null;

		System.out.print("\nHow many Project Do you Want To Add : ");
		int count = scanner.nextInt();
		for (int i = 0; i < count; i++) {

			project = new Project();

			System.out.print("\nEnter The Project ID : ");
			project.setProjectId(scanner.nextInt());
			scanner.nextLine();

			System.out.print("Enter The Project Name: ");
			project.setProjectName(scanner.nextLine());

			System.out.print("Enter The Project Start Date (yyyy-mm-dd) : ");
			project.setStartDate(LocalDate.parse(scanner.nextLine()));

			System.out.print("Enter The Project Dead Line (yyyy-mm-dd) : ");
			project.setDeadLine(LocalDate.parse(scanner.nextLine()));

		}
		if (ProjectDao.SaveProject(project)) {
			System.out.println("\nData Successfully Inserted...!");
			projects.add(project);
		}

		return projects;
	}

	public static Address addAddress() {

		Address address = new Address();

		System.out.print("\nEnter The Address ID : ");
		address.setAddressId(scanner.nextInt());
		scanner.nextLine();

		System.out.print("Enter The Street : ");
		address.setStreet(scanner.nextLine());

		System.out.print("Enter The City : ");
		address.setCity(scanner.nextLine());

		System.out.print("Enter The State : ");
		address.setState(scanner.nextLine());

		System.out.print("Enter The Country : ");
		address.setCountry(scanner.nextLine());

		System.out.print("Enter The Pincode : ");
		address.setPincode(scanner.nextInt());

		if ((AddressDao.SaveAddress(address))) {
			System.out.println("\nData Successfully Inserted...!");
			return address;
		} else {
			System.out.println("\nData Not Inserted...!");
			return null;
		}

	}

	public static void getEmployee() {

		boolean flag = true;

		while (flag) {
			System.out.println("\n1. Add Employee");
			System.out.println("2. Find Employee (ID)");
			System.out.println("3. Delete Employee (ID)");
			System.out.println("4. Update Employee");
			System.out.println("0. Exit");

			System.out.print("\nEnter choice : ");

			switch (scanner.nextInt()) {
			case 1: {

				Employee employee = addEmployee();
				Address address = addAddress();
				employee.setAddress(address);
				address.setEmployee(employee);

				ArrayList<Employee> employees = new ArrayList<Employee>();
				employees.add(employee);

				System.out.println("\nAdd the Department for Employee " + employee.getEmployeeName());

				System.out.println("\nTo Assign Department to Employeee....");
				System.out.print("\nEnter the Department ID : ");

				Department department = DepartmentDao.findDepartment(scanner.nextInt());

				if (department != null) {
					employee.setDepartment(department);
				} else {
					System.out.println("\nDepartment Not Present");
					continue;
				}

				System.out.println("\nAdd the Projects for Employee " + employee.getEmployeeName());

				ArrayList<Project> projects = new ArrayList<Project>();
				Project project = null;

				System.out.print("\nHow many Project Do you wnat to assign : ");
				
				int count = scanner.nextInt();

				for (int i = 0; i < count; i++) {

					System.out.println("\nTo Assign Projects to Employeee....");

					System.out.print("Enter the Project ID : ");

					project = ProjectDao.findProject(scanner.nextInt());
					project.setEmployees(employees);

					projects.add(project);

				}

				employee.setProjects(projects);
				department.setEmployees(employees);

				if (EmployeeDao.SaveEmployee(employee)) {
					System.out.println("\nData Successfully Inserted...!");
				} else {
					System.out.println("\nData Not Inserted...!");
				}

			}
				break;
			case 2: {

				System.out.print("\nEnter The Employee Id : ");
				System.out.println(EmployeeDao.findEmployee(scanner.nextInt()));

			}
				break;
			case 3: {

				System.out.print("\nEnter The Employee Id : ");
				if (EmployeeDao.deleteEmployee(scanner.nextInt())) {
					System.out.println("\nRecord Deleted Successfully...!");
				} else {
					System.out.println("\nRecord Not Deleted...!");
				}

			}
				break;
			case 4: {

				Employee employee = new Employee();

				System.out.println("\nUpdate Employee Name...");

				System.out.print("Enter The Employee ID : ");
				int id = scanner.nextInt();
				scanner.nextLine();

				System.out.print("Enter new Employee Name : ");
				employee.setEmployeeName(scanner.nextLine());

				System.out.print("Enter new Employee Email : ");
				employee.setEmployeeEmail(scanner.nextLine());

				if (EmployeeDao.updateEmployee(id, employee)) {
					System.out.println("\nRecord Updated...!");
				} else {
					System.out.println("\nRecord Not Updated...!");
				}

			}
				break;
			case 0: {
				System.out.println("\nThank You...!");
				flag = false;

			}
				break;
			default:
				System.out.println("\nWrong Input...!");
				break;
			}
		}

	}

	public static void getAddress() {

		boolean flag = true;

		while (flag) {
			System.out.println("\n1. Add Employee");
			System.out.println("2. Find Address (ID)");
			System.out.println("3. Delete Address (ID)");
			System.out.println("4. Update Address");
			System.out.println("0. Exit");

			System.out.print("\nEnter choice : ");

			switch (scanner.nextInt()) {
			case 1: {

				addAddress();

			}
				break;
			case 2: {

				System.out.print("\nEnter The Address Id : ");
				System.out.println(AddressDao.findAddress(scanner.nextInt()));

			}
				break;
			case 3: {

				System.out.print("\nEnter The Address Id : ");
				if (AddressDao.deleteAddress(scanner.nextInt())) {
					System.out.println("\nRecord Deleted Successfully...!");
				} else {
					System.out.println("\nRecord Not Deleted...!");
				}

			}
				break;
			case 4: {

				Address address = new Address();

				System.out.println("\nUpdate Address...");
				System.out.print("\nEnter Address Id : ");
				int id = scanner.nextInt();

				System.out.print("Enter new Street : ");
				scanner.nextLine();
				address.setStreet(scanner.nextLine());
				System.out.print("Enter New City : ");
				address.setCity(scanner.nextLine());
				System.out.print("Enter New State : ");
				address.setState(scanner.nextLine());
				System.out.print("Enter New Country : ");
				address.setCountry(scanner.nextLine());
				System.out.print("Enter New Pincode : ");
				address.setPincode(scanner.nextInt());

				if (AddressDao.updateAddress(id, address)) {
					System.out.println("\nRecord Updated...!");
				} else {
					System.out.println("\nRecord Not Updated...!");
				}

			}
				break;
			case 0: {

				System.out.println("\nThank You...!");
				flag = false;
			}
				break;

			default:
				System.out.println("\nWrong Input...!");
				break;
			}
		}

	}

	public static void getDepartment() {

		boolean flag = true;

		while (flag) {
			System.out.println("\n1. Add Employee");
			System.out.println("2. Find Department (ID)");
			System.out.println("3. Delete Department (ID)");
			System.out.println("4. Update Department");
			System.out.println("0. Exit");

			System.out.print("\nEnter choice : ");

			switch (scanner.nextInt()) {
			case 1: {

				addDepartment();

			}
				break;
			case 2: {

				System.out.print("\nEnter The Department Id : ");
				System.out.println(DepartmentDao.findDepartment(scanner.nextInt()));

			}
				break;
			case 3: {

				System.out.print("\nEnter The Department Id : ");
				if (DepartmentDao.deleteDepartment(scanner.nextInt())) {
					System.out.println("\nRecord Deleted Successfully...!");
				} else {
					System.out.println("\nRecord Not Deleted...!");
				}

			}
				break;
			case 4: {

				Department department = new Department();

				System.out.println("\nUpdate Department Name...");
				System.out.print("\nEnter Department ID : ");
				int id = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Enter new Department Name : ");
				department.setDepartmentName(scanner.nextLine());

				System.out.println("Enter new Department Phone Number : ");
				department.setDepartmentPhoneNumber(scanner.nextLong());
				scanner.nextLine();

				System.out.println("Enter new Department Location : ");
				department.setDepartmentLocation(scanner.nextLine());

				if (DepartmentDao.updateDepartment(id, department)) {
					System.out.println("\nRecord Updated...!");
				} else {
					System.out.println("\nRecord Not Updated...!");
				}
			}
				break;
			case 0: {
				System.out.println("\nThank You...!");
				flag = false;
			}
				break;

			default:
				System.out.println("\nWrong Input...!");
				break;
			}
		}

	}

	public static void getProject() {

		boolean flag = true;

		while (flag) {
			System.out.println("\n1. Add Project");
			System.out.println("2. Find Project (ID)");
			System.out.println("3. Delete Project (ID)");
			System.out.println("4. Update Project");
			System.out.println("0. Exit");

			System.out.print("\nEnter choice : ");

			switch (scanner.nextInt()) {
			case 1: {

				addProjects();

			}
				break;
			case 2: {

				System.out.print("\nEnter The Project Id : ");
				System.out.println(ProjectDao.findProject(scanner.nextInt()));
			}
				break;
			case 3: {

				System.out.print("\nEnter The Project Id : ");
				if (ProjectDao.deleteProject(scanner.nextInt())) {
					System.out.println("\nRecord Deleted Successfully...!");
				} else {
					System.out.println("\nRecord Not Deleted...!");
				}

			}
				break;
			case 4: {

				Project project = new Project();

				System.out.println("\nUpdate Project Name...");
				System.out.println("\nEnter Project Id : ");
				int id = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Enter new Project Name : ");
				project.setProjectName(scanner.nextLine());

				if (ProjectDao.updateProject(id, project)) {
					System.out.println("\nRecord Updated...!");
				} else {
					System.out.println("\nRecord Not Updated...!");
				}

			}
				break;
			case 0: {
				System.out.println("\nThank You...!");
				flag = false;
			}
				break;

			default:
				System.out.println("\nWrong Input...!");
				break;
			}
		}

	}

	public static void displayEmployee(int id) {

		Employee employee = EmployeeDao.findEmployee(id);

		System.out.println("\n**************************************Employee*************************************\n");

		if (employee != null) {
			System.out.println(employee);

			System.out
					.println("\n***************************************Address************************************\n");
			System.out.println(employee.getAddress());

			System.out.println(
					"\n***************************************Department************************************\n");
			System.out.println(employee.getDepartment());

			System.out
					.println("\n***************************************Projects************************************\n");
			employee.getProjects().forEach((n) -> System.out.println(n));

			System.out.println("\n---------------------------------------------------------------------------------\n");
			
		} else {
			System.out.println("\nData Not Present...!");
		}

	}

	public static void main(String... args) {

		System.out.println(
				"\n************************************ Employee Management System ************************************");

		while (true) {

			System.out.println("\n1. Employee Details");
			System.out.println("2. Project Details");
			System.out.println("3. Department Details");
			System.out.println("4. Show All Employee Data");
			System.out.println("0. Exit");

			System.out.print("\nEnter choice : ");

			switch (scanner.nextInt()) {
			case 1: {
				getEmployee();
			}
				break;
			case 2: {
				getProject();
			}
				break;
			case 3: {
				getDepartment();
			}
				break;
			case 4: {
				System.out.print("\nEnter The Employee ID : ");
				displayEmployee(scanner.nextInt());
			}
				break;
			case 0: {

				System.out.println("\nThank You...!");
				scanner.close();
				EmployeeDao.closeConnection();
				AddressDao.closeConnection();
				DepartmentDao.closeConnection();
				ProjectDao.closeConnection();
				System.exit(1);
			}
				break;

			default:
				System.out.println("\nWrong Input...!");
				break;
			}

		}

	}

}