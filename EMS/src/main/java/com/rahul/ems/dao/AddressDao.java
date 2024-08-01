package com.rahul.ems.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.rahul.ems.dto.Address;
import com.rahul.ems.factory.Factory;

public class AddressDao {

	static EntityManager entityManager = Factory.entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static boolean SaveAddress(Address address) {

		if (address != null) {

			entityManager.persist(address);

			return true;
		} else {
			return false;
		}

	}

	public static Address findAddress(int id) {

		Address address = entityManager.find(Address.class, id);

		if (address != null) {
			return address;
		} else {
			return null;
		}

	}

	public static boolean deleteAddress(int id) {

		entityManager.remove(findAddress(id));

		if (findAddress(id) == null) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean updateAddress(int id, Address add) {

		Address address = findAddress(id);
		address.setStreet(add.getStreet());
		address.setCity(add.getCity());
		address.setState(add.getState());
		address.setCountry(add.getCountry());
		address.setPincode(add.getPincode());

		entityManager.merge(address);

		if (address != null && add != null) {
			return true;
		} else {
			return false;
		}

	}

}
