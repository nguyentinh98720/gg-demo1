package tinhnv.entity.nation.concreate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.CountryLanguages;
import tinhnv.entity.nation.Region;
import tinhnv.entity.nation.Statistic;
import tinhnv.entity.nation.builder.CountryInformationBuilder;

public class CountryInformationConcreateBuilder implements CountryInformationBuilder {
	
	private String name;
	private BigDecimal area;
	private Date nationalDay;
	private String countryCodeTwoChars;
	private String countryCodeThreeChars;
	private Region region;
	private List<CountryLanguages> languages;
	private List<Statistic> stats;

	@Override
	public CountryInformationBuilder setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public CountryInformationBuilder setArea(BigDecimal area) {
		this.area = area;
		return this;
	}

	@Override
	public CountryInformationBuilder setNationalDay(Date day) {
		this.nationalDay = day;
		return this;
	}

	@Override
	public CountryInformationBuilder setCountryCodeTwoChars(String code) {
		this.countryCodeTwoChars = code;
		return this;
	}

	@Override
	public CountryInformationBuilder setCountryCodeThreeChars(String code) {
		this.countryCodeThreeChars = code;
		return this;
	}

	@Override
	public CountryInformationBuilder setRegion(Region region) {
		this.region = region;
		return this;
	}

	@Override
	public CountryInformationBuilder setLanguages(List<CountryLanguages> languages) {
		this.languages = languages;
		return this;
	}

	@Override
	public CountryInformationBuilder setStatistics(List<Statistic> stats) {
		this.stats = stats;
		return this;
	}

	@Override
	public Country buildEntity() {
		Country country = new Country();
		country.setName(name);
		country.setArea(area);
		country.setCountryCodeTwoChars(countryCodeTwoChars);
		country.setCountryCodeThreeChars(countryCodeThreeChars);
		country.setNationalDay(nationalDay);
		country.setRegion(region);
		country.setLanguages(languages);
		country.setStats(stats);
		return country;
	}
}
