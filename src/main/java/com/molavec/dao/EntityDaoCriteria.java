package com.molavec.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molavec.iotags.dao.exception.QueryException;
import com.molavec.iotags.dao.exception.QueryExceptionKind;
import com.molavec.iotags.persistenceUnit.IOTagsPersistenceUnitFactory;

/**
 * Implements EntityDao using CriteriaQuery to construct queries.
 * @author angel
 *
 * @param <T> Type of class
 * @param <PK> type of primary key
 */
public class EntityDaoCriteria<T,PK extends Serializable> implements EntityDao<T, PK>{
	static Logger log = LoggerFactory.getLogger(EntityDaoCriteria.class);
	private Class<T> tclass ;
	private EntityManager em;
	
	public EntityDaoCriteria(Class<T> tclass, EntityManager em) throws ClassCastException{
		this.tclass = tclass;
		this.em = em;
	}

	public void save(T entity) throws QueryException{
		try {
			em.persist(entity);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} 
	}

	//TODO: ver como se comporta esta clase realmente. Pobrar Mockito y PowerMock sean lo que sean.
	@Override
	public T attach(T entity) throws QueryException {
		try {
			em.persist(entity);
			em.flush();
			em.refresh(entity);
			return entity;
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		}
	}

	@Override
	public T get(PK id) throws QueryException {
		
		try {
			
			T entity = em.find(tclass,id);
			if(entity != null){
				return entity;	
			}else{
				String msg = String.format("Can't  find in database: %s", tclass.getName());
				throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
			}
		} catch (IllegalStateException e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} catch (IllegalArgumentException e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		}	
//		    NoResultException - if there is no result 
//		    NonUniqueResultException - if more than one result 
//		    IllegalStateException - if called for a Java Persistence query language UPDATE or DELETE statement 
//		    QueryTimeoutException - if the query execution exceeds the query timeout value set and only the statement is rolled back 
//		    TransactionRequiredException - if a lock mode has been set and there is no transaction 
//		    PessimisticLockException - if pessimistic locking fails and the transaction is rolled back 
//		    LockTimeoutException - if pessimistic locking fails and only the statement is rolled back 
//		    PersistenceException - if the query execution exceeds the query timeout value set and the transaction is rolled back
		 
	}

	public T get(PrepareQuery<T> prepareQuery) throws QueryException{
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
			Order[] orderArray = prepareQuery.getOrderArray(root, criteriaBuilder);
			if(predicate != null){ 
				criteriaQuery.where(predicate);
			}
			if(orderArray != null){
				criteriaQuery.orderBy(orderArray);
			}
			TypedQuery<T> query = em.createQuery(criteriaQuery);
			return query.getSingleResult();
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (NonUniqueResultException e) {
			String msg = String.format("More than %s entity in database", tclass.getName());
			throw new QueryException(QueryExceptionKind.NON_UNIQUE_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} 
	}


	@Override
	public void delete(PK id) throws QueryException {
		try {
			T entity = em.find(tclass,id);
			if(entity != null){
				em.remove(entity);
			}else{
				String msg = String.format("Can't  find in database: %s", tclass.getName());
				throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
			}
		} catch (IllegalStateException e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} catch (IllegalArgumentException e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		}	 finally{
			em.close();
		}
	}
	
	@Override
	public void deleteAll() throws QueryException {
		for(T t: this.find()){
			try{
				em.remove(t);
			}catch(Exception e){
				throw new QueryException("Error removing entity: " + t);
			}
		}
		
	}

	@Override
	public List<T> find() throws QueryException {
		PrepareQuery<T> prepareQuery=null;
		return find(prepareQuery);
	}
	
	@Override
	public List<T> find(PrepareQuery<T> prepareQuery) throws QueryException {
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			
			criteriaQuery.select(root);
			
			if(prepareQuery!=null){
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				criteriaQuery.where(predicate);
			}
			TypedQuery<T> query = em.createQuery(criteriaQuery);
			return query.getResultList();
		}  catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName(), e);
		}
	}

	@Override
	public List<T> find(int page, int pageSize) throws QueryException {
		PrepareQuery<T> prepareQuery=null;
		return find(page, pageSize,prepareQuery);
	}
	@Override
	public List<T> find(int page, int pageSize,PrepareQuery<T> prepareQuery) throws QueryException {
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			
			criteriaQuery.select(root);
			
			if(prepareQuery!=null){
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				criteriaQuery.where(predicate);
			}
			TypedQuery<T> query = em.createQuery(criteriaQuery);
			query.setFirstResult(page*pageSize);
			query.setMaxResults(pageSize);

			return query.getResultList();
//		    IllegalStateException - if called for a Java Persistence query language UPDATE or DELETE statement 
//		    QueryTimeoutException - if the query execution exceeds the query timeout value set and only the statement is rolled back 
//		    TransactionRequiredException - if a lock mode has been set and there is no transaction 
//		    PessimisticLockException - if pessimistic locking fails and the transaction is rolled back 
//		    LockTimeoutException - if pessimistic locking fails and only the statement is rolled back 
//		    PersistenceException - if the query execution exceeds the query timeout value set and the transaction is rolled back
		} catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		}	
	}
	@Override
	public long count() throws QueryException {
		PrepareQuery<T> prepareQuery=null;
		return count(prepareQuery);
	}
	
	
	@Override
	public long count(PrepareQuery<T> prepareQuery) throws QueryException {
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			Expression<Long> expression = criteriaBuilder.count(root);
			CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
			cq.select( expression);
			if(prepareQuery!=null){
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				cq.where(predicate);
			}
			TypedQuery<Long> typedQuery = em.createQuery(cq);
			return typedQuery.getSingleResult();
		}catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} finally {
			em.close();
		}
	}



	protected EntityManager getEntityManager(){
		EntityManagerFactory entityManagerFactory = IOTagsPersistenceUnitFactory
														.getInstance()
														.getEntityManagerFactory();
		return entityManagerFactory.createEntityManager();
	}

}
