package com.app.college.exception;

public class ConfigDataNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigDataNotFound(String message) {
		super(String.format(message));
	}

}
