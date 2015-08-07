package com.molavec.iotags.queries;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molavec.dao.EntityDaoCriteria;
import com.molavec.iotags.entities.Tag;

/**
 * Implements queries for DigitalData.
 * 
 * @author molavec
 *
 */
public class DigitalDataQuery extends EntityDaoCriteria<Tag, Integer> {
	
	public DigitalDataQuery(EntityManager em){
		super(Tag.class, em);
	}
	
	static Logger log = LoggerFactory.getLogger(DigitalDataQuery.class);
}
