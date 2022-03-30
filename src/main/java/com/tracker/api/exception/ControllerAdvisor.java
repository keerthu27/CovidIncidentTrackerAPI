package com.tracker.api.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	/**
	 * To handle CovidIncidentTrackerException
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler({ CovidIncidentTrackerException.class })
	public void exceptionHandleNotFound(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	/**
	 * To handle parameter validation exception
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public void handleConstraintViolationError(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}
