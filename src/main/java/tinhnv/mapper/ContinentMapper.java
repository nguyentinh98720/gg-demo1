package tinhnv.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import tinhnv.dto.nation.continentdto.ContinentDTOForDetail;
import tinhnv.dto.nation.continentdto.ContinentDTOForList;
import tinhnv.entity.nation.Continent;

@Mapper(componentModel = "spring")
public interface ContinentMapper {

	@Named(value="dtoForList")
	@Mappings({
		@Mapping(target="continentId", source="entity.id"),
		@Mapping(target="continentName", source="entity.name")
	})
	ContinentDTOForList toDTOForList(Continent entity);
	
	@IterableMapping(qualifiedByName = "dtoForList")
	List<ContinentDTOForList> toDTOForList(List<Continent> entities);

	default ContinentDTOForDetail toDTOForDetail(Continent entity) {
		RegionMapper regionMapper = new RegionMapperImpl();
		ContinentDTOForDetail detail = new ContinentDTOForDetail();
		detail.setContinentId(entity.getId());
		detail.setContinentName(entity.getName());
		detail.setRegions(regionMapper.toDTOForList(entity.getRegions()));
		return detail;
	};
}
