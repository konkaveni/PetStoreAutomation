package api.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;

public class UserTests {
	
	Faker faker;
	User userPayload;
	Logger logger;
	
	@BeforeClass
	public void setUp() {
		faker= new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
				
	}
	@Test(priority=1)
	public void testCreateUser() throws IOException {
	    logger.info("Creating User");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("User is Created");
			
	}
	@Test(priority=2)
	public void testGetUser() throws IOException {
		logger.info("Reading the  User");
		Response response=UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("User is info is displayed");
	}
	@Test(priority=3)
	public void testPutUser() throws IOException {
		logger.info("Updating User");
		
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("User is updated");
		
		//checking data after update
		
		Response responseafterupdate=UserEndPoints.getUser(this.userPayload.getUsername());
		responseafterupdate.then().log().all();
	}
	
	@Test(priority=4)
	public void testDeleteUser() throws IOException {
		logger.info("Deleting User");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("User is deleted");
	}

}
