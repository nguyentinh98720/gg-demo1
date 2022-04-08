package tinhnv.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import tinhnv.dto.nation.countrydto.CountryDTOForDetail;
import tinhnv.dto.nation.countrydto.CountryDTOForList;
import tinhnv.entity.nation.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {

	@Named(value="dtoForList")
	@Mappings({
		@Mapping(target="countryId", source="entity.id"),
		@Mapping(target="countryName", source="entity.name")
	})
	CountryDTOForList toDTOForList(Country entity);
	
	@IterableMapping(qualifiedByName = "dtoForList")
	List<CountryDTOForList> toDTOForList(List<Country> entities);
	
	default CountryDTOForDetail toDTOForDetail(Country entity) {
		LanguageMapper languageMapper = new LanguageMapperImpl();
		RegionMapper regionMapper = new RegionMapperImpl();
		StatisticMapper statisticMapper = new StatisticMapperImpl();
		CountryDTOForDetail detail = new CountryDTOForDetail();
		detail.setCountryId(entity.getId());
		detail.setCountryName(entity.getName());
		detail.setArea(entity.getArea());
		detail.setCountryCodeTwoChars(entity.getCountryCodeTwoChars());
		detail.setCountryCodeThreeChars(entity.getCountryCodeThreeChars());
		detail.setRegion(regionMapper.toDTOForList(entity.getRegion()));
		detail.setLanguages(languageMapper.toDTO(entity.getLanguages()));
		detail.setStatistics(statisticMapper.toDTO(entity.getStats()));
		return detail;
	};
}
