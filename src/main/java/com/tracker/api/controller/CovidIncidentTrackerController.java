package com.tracker.api.controller;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.api.service.response.CovidIncidentTrackerResponse;
import com.tracker.api.service.CovidIncidentTrackerService;

import lombok.extern.slf4j.Slf4j;

/**
 * This is Rest Controller for the API exposed by this solution Supports 2 API
 * end points 1. Get Incident data by Location from RKI API without caching 2.
 * Get Incident data by Location from RKI API with caching
 * 
 * @author Keerthana
 *
 */
@RestController
@RequestMapping("/api/v1")
@Validated
@Slf4j
public class CovidIncidentTrackerController {

	@Autowired
	CovidIncidentTrackerService covidIncidentTrackerService;

	/**
	 * This method returns Incident data by Location from RKI API with local caching
	 * support
	 * 
	 * @param landKreise
	 * @return
	 */
	@GetMapping(value = "/getCovidIncidentByLandkreise/{landKreise}")
	public CovidIncidentTrackerResponse getCovidDataByLandKreise(@PathVariable @Size(min = 4, message = "{validation.name.size.too_short}") String landKreise) {
		log.info("Land Kreise :" + landKreise);
		return covidIncidentTrackerService.getCovidDataByLandKreise(landKreise);
	}

	/**
	 * This method returns Incident data by Location from RKI API without caching
	 * 
	 * @param landKreise
	 * @return
	 */
	@GetMapping(value = "/getCovidIncidentByLandkreiseNC/{landKreise}")
	public CovidIncidentTrackerResponse getCovidIncidentByLandkreiseNC(@PathVariable @Size(min = 4, message = "{validation.name.size.too_short}") String landKreise) {
		log.info("Land Kreise :" + landKreise);
		return covidIncidentTrackerService.getCovidDataByLandKreiseNC(landKreise);
	}
}
