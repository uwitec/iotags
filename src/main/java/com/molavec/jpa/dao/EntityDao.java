 package com.molavec.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.molavec.jpa.dao.exception.QueryException;



//extends Serializable
public interface EntityDao<T, PK>{
	
	void save(T e) 		throws QueryException;
	T attach(T e) 		throws QueryException;
	T get(PK id) throws QueryException;
	T get(PrepareQuery<T> prepareQuery) throws QueryException;
	T update(T e) 		throws QueryException;
	void delete(T e) 	throws QueryException;
	void deleteByPK(PK e) throws QueryException;
	void deleteAll() 	throws QueryException;
	List<T> find() throws QueryException;
	List<T> find(PrepareQuery<T> prepareQuery) throws QueryException ;
	List<T> find(int page, int pageSize)  throws QueryException;
	List<T> find(int page, int pageSize, PrepareQuery<T> prepareQuery) throws QueryException;
	long count() throws QueryException;
	long count(PrepareQuery<T> prepareQuery) throws QueryException;
	
}
