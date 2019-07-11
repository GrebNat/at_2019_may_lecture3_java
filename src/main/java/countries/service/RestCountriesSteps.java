package countries.service;

import com.google.gson.Gson;
import countries.dto.CountryDto;

import java.util.HashMap;

public class RestCountriesSteps {

	public CountryDto[] getCountryByName(String name) {
		return
				new Gson().fromJson(
						new RestCountriesService()
						.getNoParams(String.format(URI.GET_BY_COUNTRY_NAME_URI, name))
						.getBody().asString(), CountryDto[].class);
	}

	public CountryDto[] getCountryByCode(String code) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("codes", code);

		return
				new Gson().fromJson(
						new RestCountriesService()
						.getWithParams(URI.GET_BY_COUNTRY_CODE_URI, params)
						.getBody().asString(), CountryDto[].class);
	}
}
