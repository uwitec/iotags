package com.molavec.iotags.queries;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molavec.dao.EntityDaoCriteria;
import com.molavec.iotags.entities.Tag;

/**
 * Implements queries for Tag.
 * 
 * @author molavec
 *
 */
public class TagQuery extends EntityDaoCriteria<Tag, Integer> {
	
	public TagQuery(EntityManager em){
		super(Tag.class, em);
	}
	
	static Logger log = LoggerFactory.getLogger(TagQuery.class);
}
