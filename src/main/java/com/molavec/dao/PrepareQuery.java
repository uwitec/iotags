package com.molavec.dao;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public interface PrepareQuery<T> {
	public Predicate getPredicate(Root<T> root,CriteriaBuilder criteriaBuilder);
	
	public Order[] getOrderArray(Root<T> root,CriteriaBuilder criteriaBuilder);
	
}
