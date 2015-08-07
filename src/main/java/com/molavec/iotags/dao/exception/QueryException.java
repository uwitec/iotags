package com.molavec.iotags.dao.exception;

@SuppressWarnings("serial")
public class QueryException extends Exception{
	
	private QueryExceptionKind exceptionKind = QueryExceptionKind.UNDEFINED; 
	
	public QueryException(){
	}
	
	public QueryException(QueryExceptionKind exceptionKind){
		this.exceptionKind = exceptionKind;
	}
	
	public QueryException(QueryExceptionKind exceptionKind, String msg){
		super(msg);
		this.exceptionKind = exceptionKind;
	}
	
	public QueryException(QueryExceptionKind exceptionKind, Throwable e){
		super(e);
		this.exceptionKind = exceptionKind;
	}
	
	public QueryException(String msg){
		super(msg);
	}
	
	public QueryException(Throwable e){
		super(e);
	}
	
	public QueryException(String msg,Throwable e){
		super(msg, e);
	}
	
	public QueryExceptionKind getExceptionKind(){
		return this.exceptionKind;
	}

}
