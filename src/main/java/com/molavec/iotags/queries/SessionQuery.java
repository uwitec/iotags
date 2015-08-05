package com.molavec.iotags.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.molavec.iotags.entities.Session;

public class SessionQuery {
	protected EntityManager em;

	public SessionQuery(EntityManager em) {
		this.em = em;
	}

	public Session createSession(String name) {
		Session session = new Session();
		session.setName(name);
		em.persist(session);
		return session;
	}

	public void removeSession(int id) {
		Session session = findSession(id);
		if (session != null) {
			em.remove(session);
		}
	}

	public Session findSession(int id) {
		return em.find(Session.class, id);
	}

	public List<Session> findAllSessions() {
		TypedQuery<Session> query = em.createQuery("SELECT e FROM Session e",
				Session.class);
		return query.getResultList();
	}
}
