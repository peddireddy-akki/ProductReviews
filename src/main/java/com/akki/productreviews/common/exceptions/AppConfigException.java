package com.akki.productreviews.common.exceptions;

public class AppConfigException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3672226799772989561L;
	private static final String errorCode = "1002";
	private static final String errorMessage = "Application Configuration Issues";

	public AppConfigException(String errorDetails, Throwable cause) {
		super(errorCode, errorMessage, errorDetails, cause);

	}
	
	public AppConfigException(String errorDetails) {
		super(errorCode, errorMessage, errorDetails);

	}

}
