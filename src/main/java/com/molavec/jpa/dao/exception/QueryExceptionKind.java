package com.molavec.jpa.dao.exception;

public enum QueryExceptionKind {

	//cuando se tiene que una excepcion que no se sabe que la causa
	//o no hay una regla especifica para manejarlo
	UNDEFINED,
	//Cuando se espera un unico resultado y es más de uno
	NON_UNIQUE_RESULT,
	//cuando en el DB se define que el campo debe ser único
	NON_UNIQUE_FIELD, 
	//Cuando no se obtiene un resultado
	NO_RESULT,
	//En caso de que exista la entidad
	ALREDY_EXISTS,
	//Un error critico
	FATAL_ERROR
	
}
