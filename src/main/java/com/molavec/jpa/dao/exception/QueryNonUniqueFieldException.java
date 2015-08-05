package com.molavec.jpa.dao.exception;

/**
 * Es arrojada cuando se intenta ingresar o actualizar un campo en  
 * una entidad u este se encuentra ya en otra entidad en la base de
 * datos.
 * 
 *  En manejadores de base de datos es posible restringir que algunos
 *  campos sean únicos (aparte de los "id"). Esta exception debe ser 
 *  arrojada cuando se incumpla dicha condición.
 * 
 * @author angel
 *
 */
@SuppressWarnings("serial")
public class QueryNonUniqueFieldException extends Exception{
	
	public QueryNonUniqueFieldException(){
	}
	
	public QueryNonUniqueFieldException(String msg){
		super(msg);
	}
	
	public QueryNonUniqueFieldException(Throwable e){
		super(e);
	}
	
	public QueryNonUniqueFieldException(String msg,Throwable e){
		super(msg, e);
	}

}
