package com.prushaltech.techtrix.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1180792728156425455L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
