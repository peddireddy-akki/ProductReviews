/**
* The CustomizedResponseEntityExceptionHandler captures the exceptions thrown by REST end points and format error response 
* before returning to the REST end point invoker.  It also logs the exception details.
* @author  Akki
* @version 1.0
* @since   2018-09-24 
*/

package com.akki.productreviews.common.exceptions;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
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
	public final ResponseEntity<ExceptionResponse> handleApplicationException(ApplicationException appException,
			WebRequest request) {
		logger.error(appException, appException);
		ExceptionResponse exceptionResponse = new ExceptionResponse(appException.getExceptionTimeStamp(),
				appException.getErrorCode(), appException.getMessage(), appException.getErrorDetails(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, appException.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleException(ApplicationException exception, WebRequest request) {
		logger.error(exception, exception);
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "9999", "Undled Exception",
				"Internal server error. Please contact customer service team", request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
