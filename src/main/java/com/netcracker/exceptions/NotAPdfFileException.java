package com.netcracker.exceptions;

public class NotAPdfFileException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NotAPdfFileException(String msg) {
		super(msg);
	}
}
