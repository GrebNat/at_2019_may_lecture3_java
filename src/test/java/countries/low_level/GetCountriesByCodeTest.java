package countries.low_level;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

public class GetCountriesByCodeTest {

	@BeforeMethod
	public void setup() {
	}

	@AfterMethod
	public void teardown() {
	}

	@Test(description = "Get country by one code")
//----------------------simple fail test without headers
	void getCountryByOneCodeFail() {
		RestAssured
				.get("https://restcountries-v1.p.rapidapi.com/alpha/?codes=co;nor")
				.then().statusCode(401);
	}

	@Test(description = "Get country by one code")
//---------------------simple pass tests with headers
	void getCountryByOneCodePass() {
		RestAssured
				.given()
					.header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
					.header("X-RapidAPI-Key", "4b878bce9bmshe4d66d5adfc366fp12bc6ejsnbbdd67752f91")
				.when()
					.get("https://restcountries-v1.p.rapidapi.com/alpha/?codes=co;nor")
				.then()
					.statusCode(200);
	}

	@Test(description = "Get country by one code")
//simple pass test with  Request Specification and hamcrest
	void getCountryByOneCodeWithConfig() {
		RequestSpecification requestSpecification = given()
				.header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
				.header("X-RapidAPI-Key", "4b878bce9bmshe4d66d5adfc366fp12bc6ejsnbbdd67752f91");

		requestSpecification
				.param("codes", "co;nor")
				.get("https://restcountries-v1.p.rapidapi.com/alpha/")
				.then()
				.statusCode(200).and()
				.body("name[0]", is("Colombia"))
				.body("name[1]", is("Norway"))
				.body("name", is(asList("Colombia", "Norway")))
				.body("altSpellings[0]", hasItem("CO"))
				.body("name", not("Russia"));
	}

	@Test(description = "Get country by one code")
//test will pass
	void getCountryByOneCodeWitiConfigLog() {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.addHeader("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
				.addHeader("X-RapidAPI-Key", "4b878bce9bmshe4d66d5adfc366fp12bc6ejsnbbdd67752f91")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter()).build();

		given(requestSpecification)
				.param("codes", "co;nor")
				.get("https://restcountries-v1.p.rapidapi.com/alpha/")
				.then()
				.statusCode(200).and()
				.body("name[0]", is("Colombia"))
				.body("name[1]", is("Norway"))
				.body("name", is(asList("Colombia", "Norway")))
				.body("altSpellings[0]", hasItem("CO"))
				.body("name", not("Russia"));
	}
}
