package com.tracker.api.RKIDataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tracker.api.RKIDataService.model.RKIResponseRoot;

import lombok.extern.slf4j.Slf4j;

/**
 * This is RKIDataServiceClient class - Exposes methods for retrieving data from
 * RKI Corona Landkreise API
 * 
 * @author Keerthana
 *
 */
@Service
@Slf4j
public class RKIDataServiceClient {

	private Properties connectionProperties;

	/**
	 * This is a RKI client returns data for all the landkreis from RKI database
	 * 
	 * @return Response From RKI service
	 */
	public RKIResponseRoot getCovidDataFromRKI() {

		if (this.getProperties() == null) {
			log.info("RKI Service Connection properties unavailable");
			return null;
		}

		String url = connectionProperties.getProperty("url");
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("where", connectionProperties.getProperty("where"));
		uriVariables.put("outFields", connectionProperties.getProperty("outFields"));
		uriVariables.put("returnGeometry", connectionProperties.getProperty("returnGeometry"));
		uriVariables.put("outSR", connectionProperties.getProperty("outSR"));
		uriVariables.put("f", connectionProperties.getProperty("f"));

		RKIResponseRoot response = getResponse(url, uriVariables);
		if (response.getFeatures().isEmpty()) {
			log.info("RKI Service Empty result");
			return null;
		}
		return response;
	}

	/**
	 * This method is RKI client returns data for specific city specified in Where
	 * clause
	 * 
	 * @param cityName
	 * @return Response From RKI service
	 */
	public RKIResponseRoot getCovidDataFromRKIByCity(String cityName) {

		if (this.getProperties() == null) {
			log.info("RKI Service Connection properties unavailable");
			return null;
		}

		String url = connectionProperties.getProperty("url");
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("where", "GEN = '" + cityName + "'");
		uriVariables.put("outFields", connectionProperties.getProperty("outFields"));
		uriVariables.put("returnGeometry", connectionProperties.getProperty("returnGeometry"));
		uriVariables.put("outSR", connectionProperties.getProperty("outSR"));
		uriVariables.put("f", connectionProperties.getProperty("f"));

		RKIResponseRoot response = getResponse(url, uriVariables);
		return response;

	}

	/**
	 * REST Client performing API call
	 * 
	 * @param url
	 * @param uriVariables
	 * @return Response from RKI Corona Landkreise API
	 */
	private RKIResponseRoot getResponse(String url, Map<String, Object> uriVariables) {

		RestTemplate restTemplate = new RestTemplate();
		RKIResponseRoot response = restTemplate.getForObject(url, RKIResponseRoot.class, uriVariables);
		log.info("RKI Service Invocation completed");
		return response;

	}

	/**
	 * This method retrieves connection properties for RKI API from
	 * RKIService.properties
	 * 
	 * @return
	 */
	private Properties getProperties() {

		if (this.connectionProperties == null) {
			try {
				this.connectionProperties = readPropertiesFile("RKIService.properties");
			} catch (IOException e) {
				log.error("Unable to read property file", e);
			}
		}
		return this.connectionProperties;

	}

	/**
	 * Load properties file from class path
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private static Properties readPropertiesFile(String fileName) throws IOException {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(RKIDataServiceClient.class.getClassLoader().getResourceAsStream(fileName));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return prop;
	}
}
