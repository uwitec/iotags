package com.molavec.iotags.persistenceUnit;


import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Generate and obtain an EntityManagerFactory of "iotags" persistence-unit.
 * 
 * How to use:
 * <pre>
  EntityManagerFactory entityManagerFactory = IOTagsPersistenceUnitFactory
														.getInstance()
														.getEntityManagerFactory();
 * </pre>
 * 
 * @author angel
 *
 */
public class IOTagsPersistenceUnitFactory extends PersistenceUnitFactory{
	
	static Logger log = LoggerFactory.getLogger(IOTagsPersistenceUnitFactory.class);
	
	private static final String PERSISTENCE_UNIT = "jpa_test";
	
	private IOTagsPersistenceUnitFactory(){
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
	
	static {
		_instance = new IOTagsPersistenceUnitFactory();
	}

}
