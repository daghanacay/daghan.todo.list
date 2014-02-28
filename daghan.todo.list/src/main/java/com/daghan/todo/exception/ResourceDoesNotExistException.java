package com.daghan.todo.exception;

public class ResourceDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public ResourceDoesNotExistException(String message) {
		super(message);
	}
}
