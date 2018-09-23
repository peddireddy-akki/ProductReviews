package com.akki.productreviews.common.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9020648227318008172L;
	private HttpStatus httpStatus = null;
	private LocalDateTime exceptionTimeStamp;
	private String errorCode; // 100, Objectionable Content Found
	private String errorDetails;

	public ApplicationException(String errorCode, String errorMessage, String errorDetails, Throwable cause) {
		super(errorMessage, cause);
		this.exceptionTimeStamp = LocalDateTime.now();
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;
	}
	
	public ApplicationException(String errorCode, String errorMessage, String errorDetails) {
		super(errorMessage);
		this.exceptionTimeStamp = LocalDateTime.now();
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getExceptionTimeStamp() {
		return exceptionTimeStamp;
	}

	public void setExceptionTimeStamp(LocalDateTime exceptionTimeStamp) {
		this.exceptionTimeStamp = exceptionTimeStamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

}
