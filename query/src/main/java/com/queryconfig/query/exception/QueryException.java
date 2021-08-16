package com.queryconfig.query.exception;

public class QueryException extends RuntimeException{
    
	private final String message;

	public QueryException( String message ) {
		this.message = message;
	}

	public QueryException( String message , Object... valueToReplace) {
		this.message = String.format(message, valueToReplace);
	}
	
    @Override
	public String getMessage() {
		return message;
	}

}
