package com.molavec.jpa.dao.server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class PersistenceUnitFactory {
	static Logger log = LoggerFactory.getLogger(PersistenceUnitFactory.class);
	private static final String PERSISTENCE_UNIT = "punto-user";
	private EntityManagerFactory entityManagerFactory;
	
	static public PersistenceUnitFactory _instance;
	static {
		_instance = new PersistenceUnitFactory();
	}
	
	static public PersistenceUnitFactory getInstance(){
		return _instance;
	}
	
	private PersistenceUnitFactory(){
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}

	public EntityManagerFactory getEntityManagerFactory(){
		return entityManagerFactory;
	}
}
