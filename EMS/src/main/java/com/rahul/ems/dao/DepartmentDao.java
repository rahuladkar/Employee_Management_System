package com.rahul.ems.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.rahul.ems.dto.Department;
import com.rahul.ems.factory.Factory;

public class DepartmentDao {

	static EntityManager entityManager = Factory.entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static boolean SaveDepartment(Department department) {

		if (department != null) {

			entityTransaction.begin();
			entityManager.persist(department);
			entityTransaction.commit();

			return true;
		} else {
			return false;
		}

	}

	public static Department findDepartment(int id) {

		Department department = entityManager.find(Department.class, id);

		if (department != null) {
			return department;
		} else {
			return null;
		}

	}

	public static boolean deleteDepartment(int id) {

		entityTransaction.begin();
		entityManager.remove(findDepartment(id));
		entityTransaction.commit();

		if (findDepartment(id) == null) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean updateDepartment(int id, Department dept) {

		Department department = findDepartment(id);
		department.setDepartmentName(dept.getDepartmentName());
		department.setDepartmentPhoneNumber(dept.getDepartmentPhoneNumber());
		department.setDepartmentLocation(dept.getDepartmentLocation());

		entityTransaction.begin();
		entityManager.merge(department);
		entityTransaction.commit();

		if (department != null && dept != null) {
			return true;
		} else {
			return false;
		}

	}

	public static void closeConnection() {

		entityManager.close();

	}

}
