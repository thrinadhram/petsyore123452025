 package api.endpoints;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//given()---
//UserEndpoints.java
//created for perform create,read,updata,delete requests the user api

public class Userendpoints {
	
	public static Response createuser(user payload) {
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.post_url);
		return res;
				
		}
	
	public static Response Readuser(String userName) {
		Response res=given()
				.pathParam("username", userName)
				
				.when()
				.get(Routes.get_url);
		return res;
				
		}
	
	public static Response Updateuser(String userName,user payload) {
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				.when()
				.put(Routes.update_url);
		return res;
				
		}
	public static Response deleteuser(String userName) {
		Response res=given()
				.pathParam("username", userName)
				
				.when()
				.delete(Routes.delete_url);
		return res;
				
		}

}
