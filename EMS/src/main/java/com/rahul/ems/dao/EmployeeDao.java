package com.rahul.ems.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.rahul.ems.dto.Employee;
import com.rahul.ems.factory.Factory;

public class EmployeeDao {

	static EntityManager entityManager = Factory.entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static boolean SaveEmployee(Employee employee) {

		if (employee != null) {

			entityTransaction.begin();
			entityManager.persist(employee);
			entityTransaction.commit();

			return true;
		} else {
			return false;
		}

	}

	public static Employee findEmployee(int id) {

		Employee employee = entityManager.find(Employee.class, id);

		if (employee != null) {
			return employee;
		} else {
			return null;
		}

	}

	public static boolean deleteEmployee(int id) {

		entityTransaction.begin();
		entityManager.remove(findEmployee(id));
		entityTransaction.commit();

		if (findEmployee(id) == null) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean updateEmployee(int id, Employee emp) {

		Employee employee = findEmployee(id);
		employee.setEmployeeName(emp.getEmployeeName());
		employee.setEmployeeEmail(emp.getEmployeeEmail());

		entityTransaction.begin();
		entityManager.merge(employee);
		entityTransaction.commit();

		if (employee != null && emp != null) {
			return true;
		} else {
			return false;
		}

	}

}
