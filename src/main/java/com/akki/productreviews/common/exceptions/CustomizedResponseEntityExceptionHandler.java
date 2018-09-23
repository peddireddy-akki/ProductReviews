package com.akki.productreviews.common.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LogManager.getLogger(CustomizedResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(ApplicationException appException,
			WebRequest request) {
		logger.error(appException);
		ExceptionResponse exceptionResponse = new ExceptionResponse(appException.getExceptionTimeStamp(),
				appException.getErrorCode(), appException.getMessage(), appException.getErrorDetails(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, appException.getHttpStatus());
	}

}
