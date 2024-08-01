package com.rahul.ems.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.rahul.ems.dto.Project;
import com.rahul.ems.factory.Factory;

public class ProjectDao {

	static EntityManager entityManager = Factory.entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static boolean SaveProject(Project project) {

		if (project != null) {

			entityTransaction.begin();
			entityManager.persist(project);
			entityTransaction.commit();

			return true;
		} else {
			return false;
		}

	}

	public static Project findProject(int id) {

		Project project = entityManager.find(Project.class, id);

		if (project != null) {
			return project;
		} else {
			return null;
		}

	}

	public static boolean deleteProject(int id) {

		entityTransaction.begin();
		entityManager.remove(findProject(id));
		entityTransaction.commit();

		if (findProject(id) == null) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean updateProject(int id, Project proj) {

		Project project = findProject(id);
		project.setProjectName(proj.getProjectName());

		entityTransaction.begin();
		entityManager.merge(project);
		entityTransaction.commit();

		if (project != null && proj != null) {
			return true;
		} else {
			return false;
		}

	}
}
