package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	@BeforeClass
	public void setData() {
	
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		//logger.info(****creating user********)
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
	
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info(****created user********)
	}
	
	@Test(priority=2)
	public void testGetUser() {
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		//response.statusCode(200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
		//response.then().log().body().statusCode(200);
		
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		//checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
