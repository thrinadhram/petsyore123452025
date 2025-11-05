package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.endpoints.Userendpoints2;
import api.payload.user;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class Usertest2 {

	Faker faker;
	user userpayload;
	  String currentUsername;
	  
	  public Logger logger;//we have to right log4j report time write ending final step
	@BeforeClass
	void setupdata() {
		faker = new Faker();
		userpayload = new user();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		  currentUsername = userpayload.getUsername(); // save it once here
		  //logs
		  logger=org.apache.logging.log4j.LogManager.getLogger(this.getClass());
		  
	}

	@Test(priority = 1)
	void testpostuser() {
		logger.info("************* Creating User *******************");
		Response res = Userendpoints2.createuser(userpayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*************  User is created *******************");

	}

	@Test(priority = 2)
	void testGetuserByname() throws InterruptedException {
		Thread.sleep(2000);
		logger.info("************* Reading User info *******************");
		
		Response res = Userendpoints2.Readuser(currentUsername);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("*************  User info is displayed *******************");
	}
	@Test (priority = 3)
	void test_update_user_name() throws InterruptedException {
		Thread.sleep(1000);
		logger.info("************* updating User info *******************");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
			Response res = Userendpoints2.Updateuser(currentUsername,userpayload);
			res.then().log().all().statusCode(200);
			Assert.assertEquals(res.getStatusCode(), 200);
			logger.info("************* User updated *******************");
			
			//checking data after update
			Response responseafterupdate = Userendpoints.Readuser(currentUsername);
			responseafterupdate.then().log().all();
			
			Assert.assertEquals(responseafterupdate.getStatusCode(), 200);
			 Assert.assertEquals(responseafterupdate.jsonPath().get("firstName"), userpayload.getFirstName());

	}
	@Test(priority = 4)
	
	void testdelateuser_by_name() throws InterruptedException {
		
		logger.info("************* deleting User  *******************");
		Thread.sleep(1000);
		Response res=Userendpoints2.deleteuser(currentUsername);
		 res.then().log().all(); 
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************* user deleted *******************");
//		 // Confirm deletion
//        Response resAfterDelete = Userendpoints.Readuser(currentUsername);
//        Assert.assertEquals(resAfterDelete.getStatusCode(), 404);
//		
	}
	

}
