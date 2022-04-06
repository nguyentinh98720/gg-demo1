package tinhnv.entity.nation.builder;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import tinhnv.dto.nation.countrydto.CountryDTOForCreate;
import tinhnv.dto.nation.countrydto.CountryDTOForDetail;
import tinhnv.dto.nation.countrydto.CountryDTOForList;
import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.CountryLanguages;
import tinhnv.entity.nation.Region;
import tinhnv.entity.nation.Statistic;

public interface CountryInformationBuilder {

	CountryInformationBuilder setId(Integer id);
	CountryInformationBuilder setName(String name);
	CountryInformationBuilder setArea(BigDecimal area);
	CountryInformationBuilder setNationalDay(Date day);
	CountryInformationBuilder setCountryCodeTwoChars(String code);
	CountryInformationBuilder setCountryCodeThreeChars(String code);
	CountryInformationBuilder setRegion(Region region);
	CountryInformationBuilder setLanguages(List<CountryLanguages> languages);
	CountryInformationBuilder setStatistics(List<Statistic> stats);
	
	Country buildEntity();
	CountryDTOForDetail buildDetailDTO();
	CountryDTOForList buildListDTO();
	CountryDTOForCreate buildCreateDTO();
}
