package com.rahul.ems.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {

	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rahul");
	
}
