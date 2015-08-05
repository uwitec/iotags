package com.molavec.jpa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;







import com.molavec.jpa.dao.exception.QueryException;
import com.molavec.jpa.dao.exception.QueryExceptionKind;
import com.molavec.jpa.dao.server.PersistenceUnitFactory;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EntityDaoCriteria<T,PK extends Serializable> implements EntityDao<T, PK>{
	static Logger log = LoggerFactory.getLogger(EntityDaoCriteria.class);
	private Class<T> tclass ;
	
	public EntityDaoCriteria(Class<T> tclass) throws ClassCastException{
		this.tclass = tclass;
	}

	public void save(T entity) throws QueryException{
		EntityManager entityManager = getEntityManager();
		try {
			EntityTransaction entityTransaction = 
					entityManager.getTransaction();
			
			entityTransaction.begin();
			entityManager.persist(entity);
			entityTransaction.commit();
			
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public T attach(T entity) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.refresh(entity);
			return entity;
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public T get(PK id) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			
			T entity = entityManager.find(tclass,id);
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
		 finally {
			entityManager.close();
		}
	}

	public T get(PrepareQuery<T> prepareQuery) throws QueryException{
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
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
			TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
			return query.getSingleResult();
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (NonUniqueResultException e) {
			String msg = String.format("More than %s entity in database", tclass.getName());
			throw new QueryException(QueryExceptionKind.NON_UNIQUE_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} finally {
			entityManager.close();
		}
	}

	public T update(T entity) throws QueryException{
		log.info("=)otro update");
		EntityManager entityManager = getEntityManager();
		try{
			entityManager.getTransaction().begin();
			T entityCommited = entityManager.merge(entity);
			entityManager.getTransaction().commit();
			log.info("UPDATE USER <------------------");
			return entityCommited;  
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} finally{
			entityManager.close();
		}
	}
	
	@Override
	public void delete(T entity) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try{
			entityManager.getTransaction().begin();
			T entityAttached = entityManager.merge(entity);
			entityManager.remove(entityAttached);
			entityManager.getTransaction().commit();
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		}  finally{
			entityManager.close();
		}
		
	}

	@Override
	public void deleteByPK(PK id) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			T entity = entityManager.find(tclass,id);
			if(entity != null){
				entityManager.getTransaction().begin();
				entityManager.remove(entity);
				entityManager.getTransaction().commit();	
			}else{
				String msg = String.format("Can't  find in database: %s", tclass.getName());
				throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
			}
		} catch (IllegalStateException e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} catch (IllegalArgumentException e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		}	 finally{
			entityManager.close();
		}
	}
	
	@Override
	public void deleteAll() throws QueryException {
		for(T t: this.find()){
			this.delete(t);
		}
		
	}

	@Override
	public List<T> find() throws QueryException {
		PrepareQuery<T> prepareQuery=null;
		return find(prepareQuery);
	}
	
	@Override
	public List<T> find(PrepareQuery<T> prepareQuery) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			
			criteriaQuery.select(root);
			
			if(prepareQuery!=null){
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				criteriaQuery.where(predicate);
			}
			TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
			return query.getResultList();
		}  catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName(), e);
		}	 finally {
			entityManager.close();
		}
	}

	@Override
	public List<T> find(int page, int pageSize) throws QueryException {
		PrepareQuery<T> prepareQuery=null;
		return find(page, pageSize,prepareQuery);
	}
	@Override
	public List<T> find(int page, int pageSize,PrepareQuery<T> prepareQuery) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			
			criteriaQuery.select(root);
			
			if(prepareQuery!=null){
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				criteriaQuery.where(predicate);
			}
			TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
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
		}	 finally {
			entityManager.close();
		}
	}
	@Override
	public long count() throws QueryException {
		PrepareQuery<T> prepareQuery=null;
		return count(prepareQuery);
	}
	
	
	@Override
	public long count(PrepareQuery<T> prepareQuery) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			Expression<Long> expression = criteriaBuilder.count(root);
			CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
			cq.select( expression);
			if(prepareQuery!=null){
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				cq.where(predicate);
			}
			TypedQuery<Long> typedQuery = entityManager.createQuery(cq);
			return typedQuery.getSingleResult();
		}catch (Exception e) {
			throw new QueryException("Error trying to get: " + tclass.getName());
		} finally {
			entityManager.close();
		}
	}



	protected EntityManager getEntityManager(){
		PersistenceUnitFactory server = PersistenceUnitFactory.getInstance();
		EntityManagerFactory entityManagerFactory = server.getEntityManagerFactory();
		return entityManagerFactory.createEntityManager();
	}

}
