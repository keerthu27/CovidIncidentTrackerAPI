package com.tracker.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.api.RKIDataService.RKIDataServiceClient;
import com.tracker.api.RKIDataService.model.Feature;
import com.tracker.api.RKIDataService.model.RKIResponseRoot;
import com.tracker.api.exception.CovidIncidentTrackerException;
import com.tracker.api.service.response.CovidIncidentTrackerResponse;
import com.tracker.api.service.response.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * This is service class contains Application logic It has 2 key methods,
 * supporting 2 REST service endpoints 1. Get Incident data by Location from RKI
 * API without caching 2. Get Incident data by Location from RKI API with
 * caching Caching approach follows simple design using Java collection Map
 * 
 * key would be the current date and value would be data response from RKI API
 * Whenever date is changed the cache is cleared and updated with new data
 * 
 * @author Keerthana
 *
 */
@Service
@Slf4j
public class CovidIncidentTrackerService {

	@Autowired
	RKIDataServiceClient RKIDataServiceClient;

	Map<String, RKIResponseRoot> basicCache;

	/**
	 * This is Cache based method returning incident data by landKries
	 * 
	 * @param landKries
	 * @return
	 */
	public CovidIncidentTrackerResponse getCovidDataByLandKreise(String landKries) {

		RKIResponseRoot response = lookupCache(getFormattedDate());
		if (response == null) {
			response = RKIDataServiceClient.getCovidDataFromRKI();
			if (response == null) {
				log.error("RKI Service is not available");
				throw new CovidIncidentTrackerException();
			}
			addToCache(getFormattedDate(), response);

		}

		List<Result> listOfResult = lookupResult(response, landKries);

		return convertToResponse(listOfResult);
	}

	/**
	 * This is non cache based methos returning incident data by landKries
	 * 
	 * @param landKries
	 * @return
	 */
	public CovidIncidentTrackerResponse getCovidDataByLandKreiseNC(String landKries) {

		RKIResponseRoot response = RKIDataServiceClient.getCovidDataFromRKIByCity(landKries);
		if (response == null) {

			log.error("RKI Service is not available");
			throw new CovidIncidentTrackerException();
		}

		if (response.getFeatures().isEmpty()) {
			log.error("RKI Service Empty result");
			return new CovidIncidentTrackerResponse();
		}

		List<Result> listOfResult = lookupResult(response, landKries);

		return convertToResponse(listOfResult);
	}

	/**
	 * This methos performs search on incident data by location applying direct
	 * string match or part of the string match
	 * 
	 * @param response
	 * @param cityName
	 * @return
	 */
	private List<Result> lookupResult(RKIResponseRoot response, String cityName) {
		List<Result> listOfResult = new ArrayList<Result>();

		for (Feature feature : response.getFeatures()) {

			if (feature.getAttributes().getGen().equalsIgnoreCase(cityName)) {
				listOfResult.add(Result.builder().landkreise(feature.getAttributes().getGen())
						.cases7Per100k(feature.getAttributes().getCases7Per100k()).build());

			} else if (feature.getAttributes().getGen().contains(cityName)) {

				listOfResult.add(Result.builder().landkreise(feature.getAttributes().getGen())
						.cases7Per100k(feature.getAttributes().getCases7Per100k()).build());

			}
		}
		return listOfResult;
	}

	/**
	 * This is utility method to build API response object
	 * 
	 * @param listOfResult
	 * @return
	 */
	private CovidIncidentTrackerResponse convertToResponse(List<Result> listOfResult) {

		return CovidIncidentTrackerResponse.builder().listOfResult(listOfResult).build();

	}

	/**
	 * This is utility method to get date in format yyyy/MM/dd
	 * 
	 * @return
	 */
	private static String getFormattedDate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		return (dtf.format(now));
	}

	/**
	 * This method performs cache lookup based on date. Additionally this method
	 * clears previous entries when date changes
	 * 
	 * @param date
	 * @return
	 */
	private RKIResponseRoot lookupCache(String date) {
		if (this.basicCache == null) {
			this.basicCache = new HashMap<String, RKIResponseRoot>();
		}
		if (this.basicCache.containsKey(date))
			return this.basicCache.get(date);
		else
			this.basicCache.clear();
		return null;
	}

	/**
	 * This method is to add to cache
	 * 
	 * @param dateStr
	 * @param response
	 */
	private void addToCache(String dateStr, RKIResponseRoot response) {
		if (this.basicCache == null) {
			this.basicCache = new HashMap<String, RKIResponseRoot>();
		}

		this.basicCache.put(dateStr, response);

	}

}
