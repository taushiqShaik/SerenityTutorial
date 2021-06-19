package com.studentapp.junit.studensinfo;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {
	
	static String firstName = "USER" +TestUtils.getRandomValue();
	static String lastName = "USER" +TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue()+"qwe@gmail.com";
	static int studentId;
	
	
	@Steps
	StudentSerenitySteps steps;
	@Title("This test will create a new student")
	@Test
	public void Test001() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
	}
	
	@Title("Verify if the student is added to the application")
	@Test
	public void Test002() {
		
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
				
//		assertThat(value, hasValue);
//		assertThat(value, hasValue(firstName));
//		assertThat(value,hasValue(firstName));
		studentId =  (int) value.get("id");
	}
	
	@Title("Update the user and verify the updated information")
	@Test
	public void Test003() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		firstName = firstName+"_Updated";
		steps.updateStudent(studentId, firstName, lastName, email, programme, courses);
		
//		.statusCode(201);
		
		
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		
//		System.out.println("THe Value is " + value);
		
	}
	
	@Title("Delete the student and verify if they are deleted")
	@Test
	public void Test004() {
		
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).statusCode(404);
	
	}

}
