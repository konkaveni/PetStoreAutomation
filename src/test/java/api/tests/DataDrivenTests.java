package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	@Test(priority=1,dataProviderClass=DataProviders.class,dataProvider="Data")
	public void testPostUser(String UserID,String UserName,String FirstName,String LastName, String Email,String Password,String Phone) {
		User userPayLoad= new User();
		userPayLoad.setId(Integer.parseInt(UserID));
		userPayLoad.setUsername("UserName");
		userPayLoad.setFirstName("FirstName");
		userPayLoad.setLastName("LastName");
		userPayLoad.setEmail("Email");
		userPayLoad.setPassword("Password");
		userPayLoad.setPhone("Phone");
	
		Response response=UserEndPoints.createUser(userPayLoad);
		
		Assert.assertEquals(response.getStatusCode(), 200);
			
		
	}
	@Test(priority=2,dataProviderClass=DataProviders.class,dataProvider="UserNames")
	public void testDeleteUser(String UserName) {
		
		Response response=UserEndPoints.deleteUser(UserName);
	   
		Assert.assertEquals(response.getStatusCode(), 404);
	
	}

}
