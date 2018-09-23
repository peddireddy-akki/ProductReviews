package com.akki.productreviews.common.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime timestamp;
	private String errorCode;
	private String errorMessage;
	private String errorDetails;
	private String webDetails;

	public ExceptionResponse(LocalDateTime timestamp, String errorCode, String errorMessage, String errorDetails,
			String webDetails) {
		super();
		this.timestamp = timestamp;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
		this.webDetails = webDetails;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getWebDetails() {
		return webDetails;
	}

	public void setWebDetails(String webDetails) {
		this.webDetails = webDetails;
	}

}