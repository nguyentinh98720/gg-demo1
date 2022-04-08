package tinhnv.entity.nation.builder;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.CountryLanguages;
import tinhnv.entity.nation.Region;
import tinhnv.entity.nation.Statistic;

public interface CountryInformationBuilder {

	CountryInformationBuilder setName(String name);
	CountryInformationBuilder setArea(BigDecimal area);
	CountryInformationBuilder setNationalDay(Date day);
	CountryInformationBuilder setCountryCodeTwoChars(String code);
	CountryInformationBuilder setCountryCodeThreeChars(String code);
	CountryInformationBuilder setRegion(Region region);
	CountryInformationBuilder setLanguages(List<CountryLanguages> languages);
	CountryInformationBuilder setStatistics(List<Statistic> stats);
	
	Country buildEntity();
}
