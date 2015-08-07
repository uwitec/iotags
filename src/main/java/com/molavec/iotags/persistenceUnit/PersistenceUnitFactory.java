package com.molavec.iotags.persistenceUnit;

import javax.persistence.EntityManagerFactory;

/**
 * This class containts structure methods to aviod repetitive code.
 * Use IOtagsPersistenceUnitFactory class instead, create another similar
 * or modify it. 
 * 
 * @author angel
 *
 */
public abstract class PersistenceUnitFactory {
		
	protected static EntityManagerFactory entityManagerFactory;
	
	static protected PersistenceUnitFactory _instance;
	
	/**
	 * Obtain an instance of PersistenceUnitFactory
	 * @return
	 */
	static public PersistenceUnitFactory getInstance(){
		return _instance;
	}
	
	public EntityManagerFactory getEntityManagerFactory(){
		return entityManagerFactory;
	}
	
}
