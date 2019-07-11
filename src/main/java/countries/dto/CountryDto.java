package countries.dto;

import countries.dto.subentity.Translations;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CountryDto {
	private String name;
	private String capital;
	private List<String> altSpellings;
	private String relevance;
	private String region;
	private String subregion;
	private Translations translations;
	private Long population;
	private List<Float> latlng;
	private String demonym;
	private Long area;
	private Float gini;
	private List<String> timezones;
	private List<String> callingCodes;
	private List<String> topLevelDomain;
	private List<String> currencies;
	private List<String> languages;
}
