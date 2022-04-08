package tinhnv.mapper;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import tinhnv.dto.nation.regiondto.RegionDTOForDetail;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.entity.nation.Region;

@Mapper(componentModel = "spring")
public interface RegionMapper {

	@Named(value="forList")
	@Mappings({
		@Mapping(target="regionId", source="entity.id"),
		@Mapping(target="regionName", source="entity.name")
	})
	RegionDTOForList toDTOForList(Region entity);
	
	@IterableMapping(qualifiedByName = "forList")
	List<RegionDTOForList> toDTOForList(List<Region> entities);
	
	default RegionDTOForDetail toDTOForDetail(Region entity) {
		CountryMapper countryMapper = new CountryMapperImpl();
		ContinentMapper continentMapper = new ContinentMapperImpl();
		RegionDTOForDetail detail = new RegionDTOForDetail();
		detail.setRegionId(entity.getId());
		detail.setRegionName(entity.getName());
		detail.setCountries(countryMapper.toDTOForList(entity.getCountries()));
		detail.setContinent(continentMapper.toDTOForList(entity.getContinent()));
		return detail;
	}
}
