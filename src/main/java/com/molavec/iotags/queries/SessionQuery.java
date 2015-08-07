package com.molavec.iotags.queries;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molavec.dao.EntityDaoCriteria;
import com.molavec.iotags.entities.Session;

/**
 * Implements queries for Session.
 * 
 * @author molavec
 *
 */
public class SessionQuery extends EntityDaoCriteria<Session, Integer> {
	
	public SessionQuery(EntityManager em){
		super(Session.class, em);
	}
	
	static Logger log = LoggerFactory.getLogger(SessionQuery.class);
}
