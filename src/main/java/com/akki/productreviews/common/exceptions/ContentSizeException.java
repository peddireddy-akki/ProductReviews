package com.akki.productreviews.common.exceptions;

public class ContentSizeException extends ApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4171506005774975130L;
	private static final String errorCode ="1001";
	private static final String errorMessage ="Invalid content size";
	
	public ContentSizeException(String errorDetails, Throwable cause)
	{
		super(errorCode, errorMessage, errorDetails, cause);
	}
	
	public ContentSizeException(String errorDetails)
	{
		super(errorCode, errorMessage, errorDetails);
	}
}
