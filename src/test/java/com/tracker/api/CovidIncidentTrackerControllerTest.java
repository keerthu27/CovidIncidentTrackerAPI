package com.tracker.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class CovidIncidentTrackerControllerTest {

	public final String CovidIncidentTrackerURL = "http://localhost:8080/api/v1/getCovidIncidentByLandkreise";
	public final String CovidIncidentTrackerNCURL = "http://localhost:8080/api/v1/getCovidIncidentByLandkreiseNC";
	@Autowired
	private MockMvc mockMvc;
	
	
	ResultActions getCovidDataByLandKreise(String landKreise) throws Exception {

		return mockMvc.perform(get(CovidIncidentTrackerURL+"/"+landKreise).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));

	}
	
	@Test
	public void testGetCovidDataByLandKreise() throws Exception {
		getCovidDataByLandKreise("Berlin");
	}
	
	ResultActions getPathValueValidation(String landKreise) throws Exception {

		return mockMvc.perform(get(CovidIncidentTrackerURL+"/"+landKreise).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));

	}
	@Test
	public void testPathValueValidation() throws Exception {
		getPathValueValidation("k");
	}
	
	ResultActions getCovidDataByLandKreiseNC(String landKreise) throws Exception {

		return mockMvc.perform(get(CovidIncidentTrackerURL+"/"+landKreise).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));

	}
	
	@Test
	public void testGetCovidDataByLandKreiseNC() throws Exception {
		getCovidDataByLandKreiseNC("Frankfurt am Main");
	}
	
	
}
