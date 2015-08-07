package com.molavec.iotags.queries;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molavec.dao.EntityDaoCriteria;
import com.molavec.iotags.entities.AnalogData;

/**
 * Implements queries for AnalogData.
 * 
 * @author molavec
 *
 */
public class AnalogDataQuery extends EntityDaoCriteria<AnalogData, Integer> {
	
	public AnalogDataQuery(EntityManager em){
		super(AnalogData.class, em);
	}
	
	static Logger log = LoggerFactory.getLogger(AnalogDataQuery.class);
}
