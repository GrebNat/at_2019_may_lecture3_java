package countries.service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestCountriesService {
	private RequestSpecification REQUEST_SPECIFICATION;

	public RestCountriesService() {
		REQUEST_SPECIFICATION = new RequestSpecBuilder()
				.setBaseUri("https://restcountries-v1.p.rapidapi.com/")
				.addHeader("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
				.addHeader("X-RapidAPI-Key", "4b878bce9bmshe4d66d5adfc366fp12bc6ejsnbbdd67752f91")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter()).build();
	}

	public Response getNoParams(String uri) {
		return given(REQUEST_SPECIFICATION).get(uri);
	}

	public Response getWithParams(String uri, Map<String, Object> params) {
		RequestSpecification specification = given(REQUEST_SPECIFICATION);

		for (Map.Entry<String, Object> param : params.entrySet())
			specification.param(param.getKey(), param.getValue());

		return specification.get(uri);
	}
}
