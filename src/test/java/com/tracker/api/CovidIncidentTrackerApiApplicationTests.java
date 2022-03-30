package com.tracker.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.tracker.api.controller.CovidIncidentTrackerController;

@RunWith(Suite.class)
@SuiteClasses(CovidIncidentTrackerController.class)
@SpringBootTest
class CovidIncidentTrackerApiApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

}
