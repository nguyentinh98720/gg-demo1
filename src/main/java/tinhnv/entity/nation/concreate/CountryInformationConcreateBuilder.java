package tinhnv.entity.nation.concreate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import tinhnv.dto.nation.countrydto.CountryDTOForCreate;
import tinhnv.dto.nation.countrydto.CountryDTOForDetail;
import tinhnv.dto.nation.countrydto.CountryDTOForList;
import tinhnv.dto.nation.languagedto.LanguageDTO;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.dto.nation.statisticdto.StatisticDTO;
import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.CountryLanguages;
import tinhnv.entity.nation.Region;
import tinhnv.entity.nation.Statistic;
import tinhnv.entity.nation.builder.CountryInformationBuilder;

public class CountryInformationConcreateBuilder implements CountryInformationBuilder {
	
	private Integer id;
	private String name;
	private BigDecimal area;
	private Date nationalDay;
	private String countryCodeTwoChars;
	private String countryCodeThreeChars;
	private Region region;
	private List<CountryLanguages> languages;
	private List<Statistic> stats;

	@Override
	public CountryInformationBuilder setId(Integer id) {
		this.id = id;
		return this;
	}

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

	@Override
	public CountryDTOForDetail buildDetailDTO() {
		CountryDTOForDetail dto = new CountryDTOForDetail();
		dto.setCountryId(id);
		dto.setCountryName(name);
		dto.setNationalDay(nationalDay);
		dto.setArea(area);
		dto.setCountryCode2(countryCodeTwoChars);
		dto.setCountryCode3(countryCodeThreeChars);
		dto.setRegion(RegionDTOForList.toDTO(region));
		dto.setStatistics(stats.stream().map(StatisticDTO::toDTO).collect(Collectors.toList()));
		dto.setLanguages(languages.stream().map(LanguageDTO::toDTO).collect(Collectors.toList()));
		return dto;
	}

	@Override
	public CountryDTOForList buildListDTO() {
		return new CountryDTOForList(id, name);
	}

	@Override
	public CountryDTOForCreate buildCreateDTO() {
		CountryDTOForCreate dto = new CountryDTOForCreate();
		dto.setCountryId(id);
		dto.setCountryName(name);
		dto.setNationalDay(nationalDay);
		dto.setArea(area);
		dto.setCountryCode2(countryCodeTwoChars);
		dto.setCountryCode3(countryCodeThreeChars);
		return dto;
	}
}
