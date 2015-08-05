package com.molavec.iotags.queries;

import java.util.List;

import com.molavec.iotags.entities.Session;

public interface SessionQueryInterface {

	public Session createSession();
	public void removeSession();
	public Session findSession(int id);
	public List<Session> findAllSession();
	
	
}
