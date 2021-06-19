package com.studentapp.junit.studensinfo;

import org.junit.Test;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

public class TagsTest {
	
	
	@WithTag("studentfeature:NEGATIVE")
	@Title("Provide 405 status code when the incorrect http method is used to access a aresourse")
	@Test
	public void invalidmethod() {
		SerenityRest
		.rest().given().when()
		.post("/list")
		.then()
		.statusCode(405)
		.log().all();
	}
	
	@WithTags(
			{
				@WithTag("studentfeature:SMOKE"),
				@WithTag("studentfeature:POSITIVE")
			}
			)
//	@WithTag("studentfeature:POSITIVE")
	@Title("The test will verify if a status code of 200 is returned for the Get request")
	@Test
	public void verifyIfTheStatuscodeIs200() {
		SerenityRest
		.rest().given().when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	@WithTags(
			{
				@WithTag("studentfeature:SMOKE"),
				@WithTag("studentfeature:NEGATIVE")
			}
			)
//	@WithTag("studentfeature:NEGATIVE")
	@Title("The test will verify if a status code of 400 when the user tries to access invalid resourse")
	@Test
	public void invalidResourse() {
		SerenityRest
		.rest().given().when()
		.get("/listjfk")
		.then()
		.statusCode(400)
		.log().all();
	}
	
	

}
