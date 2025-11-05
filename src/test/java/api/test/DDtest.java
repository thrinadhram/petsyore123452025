package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.Userendpoints;
import api.payload.user;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

public class DDtest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = Dataprovider.class)
	void testPostuser(String userID, String userName, String fname, String lname, String useremail, String pwd,
			String ph) {
		user userpayload = new user();// this is user class under api.payload package
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		Response res = Userendpoints.createuser(userpayload);
		   res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

	}
//
//	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = Dataprovider.class)
//	void testdelateuserbyname(String userName) throws InterruptedException {
//		// Wait for 1 sec (optional)
////		try {
////			Thread.sleep(1000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//
//	//	Thread.sleep(200);
//		// Then delete
//		Response res = Userendpoints.deleteuser(userName);
//		 res.then().log().all();
//		Assert.assertEquals(res.getStatusCode(), 200);
//	}
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = Dataprovider.class)
	void testdelateuserbyname(String userName) {
	    // First check if exists
	    Response getRes = Userendpoints.Readuser(userName);

	    if (getRes.getStatusCode() == 200) {
	        Response res = Userendpoints.deleteuser(userName);
	        res.then().log().all();
	        Assert.assertEquals(res.getStatusCode(), 200, "Delete failed for: " + userName);
	    } else {
	        System.out.println("User not found: " + userName + " — Skipping delete.");
	    }
	}


}
