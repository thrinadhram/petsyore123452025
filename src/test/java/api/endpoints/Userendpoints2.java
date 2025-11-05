 package api.endpoints;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.ResourceBundle;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//given()---
//UserEndpoints.java
//created for perform create,read,updata,delete requests the user api

public class Userendpoints2 {
	
	
	//method creted form getting url's from properties file
	
	static  ResourceBundle getURL() {
		ResourceBundle routes=ResourceBundle.getBundle("routes");//load properties file //name of the properties file
		return routes;
		
	}
	
	public static Response createuser(user payload) {
	String post_url=	getURL().getString("post_url");
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);
		return res;
				
		}
	
	public static Response Readuser(String userName) {
		String get_url=	getURL().getString("get_url");
		Response res=given()
				.pathParam("username", userName)
				
				.when()
				.get(get_url);
		return res;
				
		}
	
	public static Response Updateuser(String userName,user payload) {
		String update_url=	getURL().getString("update_url");
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				.when()
				.put(update_url);
		return res;
				
		}
	public static Response deleteuser(String userName) {
		String delete_url=	getURL().getString("delete_url");
		Response res=given()
				.pathParam("username", userName)
				
				.when()
				.delete(delete_url);
		return res;
				
		}

}
