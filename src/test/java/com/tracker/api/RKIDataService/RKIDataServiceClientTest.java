package com.tracker.api.RKIDataService;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
public class RKIDataServiceClientTest {

	@Autowired
	RKIDataServiceClient rkiDataServiceClient;
	
	
	@Test
	public void getCovidDataFromRKI() {
		assertNotNull(rkiDataServiceClient.getCovidDataFromRKI());		
	}
	
	@Test
	public void getCovidDataFromRKIByCity() {
		assertNotNull(rkiDataServiceClient.getCovidDataFromRKIByCity("Frankfurt am Main"));		
	}
	
}
