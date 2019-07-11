package countries.service;

import countries.dto.CountryDto;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;
import static org.testng.Assert.assertEquals;

public class RestCountriesAssertions {

	private CountryDto[] counties;

	public RestCountriesAssertions(CountryDto[] response) {
		this.counties = response;
	}

	public void verifyCountry(String countryName) {
		assertEquals(counties.length, 1, "Incorrect list of countries size");
		assertEquals(counties[0].getName(), countryName, "Incorrect county name");
	}

	public void verifyCountries(String... countryName) {
		assertEquals(
				stream(counties).map(CountryDto::getName).collect(toList()).toArray(new String[]{}),
				countryName,
				"Incorrect list of countries");
	}
}
