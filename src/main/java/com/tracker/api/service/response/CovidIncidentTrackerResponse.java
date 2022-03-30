package com.tracker.api.service.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Model class representing response
 * 
 * @author Keerthana
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CovidIncidentTrackerResponse {

	private List<Result> listOfResult;
}
