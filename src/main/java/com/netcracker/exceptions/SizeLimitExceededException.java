package com.netcracker.exceptions;

public class SizeLimitExceededException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public SizeLimitExceededException(String msg) {
		super(msg);
	}

}
