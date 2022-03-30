Covid Incident Tracker API 

This is REST Service Application. This service caters following operations
1. Get Incident data by Location from RKI API without caching 
2. Get Incident data by Location from RKI API with caching

Url(With Caching) : http://localhost:8080/api/v1/getCovidIncidentByLandkreise/{landKreise}

Url(Without Caching) : http://localhost:8080/api/v1/getCovidIncidentByLandkreiseNC/{landKreise} 


Technology and Framework adapted to build this solution:
1. Java 11
2. Spring Boot
3. Spring Validation
4. Lombok
5. Maven
6. Junit


RKIService.properties used to configure the RKI URL

Install and Run the service API
1. Install Java 11

mvn clean install

java -jar CovidIncidentTrackerAPI-0.0.1-SNAPSHOT.jar