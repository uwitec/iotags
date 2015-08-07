package com.molavec.iotags.queries;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molavec.dao.EntityDaoCriteria;
import com.molavec.iotags.entities.User;

/**
 * Implements queries for UserData.
 * 
 * @author molavec
 *
 */
public class UserQuery extends EntityDaoCriteria<User, Integer> {
	
	public UserQuery(EntityManager em){
		super(User.class, em);
	}
	
	static Logger log = LoggerFactory.getLogger(UserQuery.class);
}
