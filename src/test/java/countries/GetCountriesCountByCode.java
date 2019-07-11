package countries.functional_level;

import countries.dto.CountryDto;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import countries.service.RestCountriesAssertions;
import countries.service.RestCountriesSteps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GetCountriesCountByCode {

	//------------------------Simple assertion with testNG
	@Test(description = "Get country by one Code")
	void getCountryByName() {
		String countryName = "Aruba";

		CountryDto[] country = new RestCountriesSteps().getCountryByName(countryName);

		assertEquals(country.length, 1, "Incorrect list of countries");
		assertEquals(country[0].getName(), countryName, "Incorrect county name");
	}

	@Test(description = "Get country by one Code")
	void getCountryByCode()
	{
		String countryCode = "rus";
		String countryName = "Russia";

		CountryDto[] country = new RestCountriesSteps().getCountryByCode(countryCode);

		assertEquals(country.length, 1, "Incorrect list of countries");
		assertEquals(country[0].getName(), countryName, "Incorrect county name");
	}

	//----------------------------------------Custom assertion
	@Test(description = "Get country by one Code")
	void getCountrytByCode2() {
		String countryCode = "rus";
		String countryName = "Russia";

		CountryDto[] countries = new RestCountriesSteps()
				.getCountryByCode(countryCode);

		new RestCountriesAssertions(countries)
				.verifyCountries(countryName);
	}

	@Test(description = "Get country by one Code")
	void getCountryByTwoCode2() {
		String countryCode = "rus;nor";

		CountryDto[] countries = new RestCountriesSteps()
				.getCountryByCode(countryCode);

		new RestCountriesAssertions(countries)
				.verifyCountries("Russia", "Norway");
	}

	//-----------------------------------Data Provider

	@DataProvider
	public Object[][] countriesData() {
		return new Object[][]{
				{"rus", "Russia"},
				{"rus;nor", "Russia", "Norway"},
				{"rus;nor", "Russia", "Norway"},
				{"rus;nor", "Angola", "Norway"},
				{"rus;nor", "Russia", "Norway"},
		};
	}

	@Test(description = "Get country by one Code", dataProvider = "countriesData")
	void getCountryByTwoCodeMultiple(String countryCodes, String... expectedCountriesNames) {

		CountryDto[] countries = new RestCountriesSteps()
				.getCountryByCode(countryCodes);

		new RestCountriesAssertions(countries)
				.verifyCountries(expectedCountriesNames);
	}
}
