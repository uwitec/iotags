package com.molavec.jpa.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PrepareQueryAdapter<T> implements PrepareQuery<T> {

	@Override
	public Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
		return null;
	}

	@Override
	public Order[] getOrderArray(Root<T> root,
			CriteriaBuilder criteriaBuilder) {
		return null;
	}

}
