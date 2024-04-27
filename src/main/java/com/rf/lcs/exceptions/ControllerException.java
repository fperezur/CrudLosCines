package com.rf.lcs.exceptions;

public class ControllerException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException() {
		super();
	}

}
