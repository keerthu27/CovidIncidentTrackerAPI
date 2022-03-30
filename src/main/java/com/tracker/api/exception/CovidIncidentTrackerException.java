package com.tracker.api.exception;

public class CovidIncidentTrackerException extends RuntimeException{

	public CovidIncidentTrackerException() {
		super("RKI Service is not available");
	}
}
