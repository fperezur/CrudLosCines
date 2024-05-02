package com.rf.lcs.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.rf.lcs"})
public class GestionExcepciones {
	
	Logger logger = LoggerFactory.getLogger(GestionExcepciones.class);

	@ExceptionHandler(ControllerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handlerController(RuntimeException rex){
		logger.error(rex.getMessage(), rex);
		return new ResponseEntity<String>(rex.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(DaoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handlerDao(RuntimeException rex){
		logger.error(rex.getMessage(), rex);
		return new ResponseEntity<String>(rex.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> gestionExcepcionGenerica(Throwable t) {
		ResponseEntity<?> respEntity = null;
		respEntity = ResponseEntity.internalServerError().body(t.getMessage());
		logger.error(t.getMessage(), t);
		return respEntity;
	}
}
