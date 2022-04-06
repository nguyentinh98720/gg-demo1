package tinhnv.dto.nation.continentdto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;

import tinhnv.controller.NationController;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.entity.nation.Continent;

public class ContinentDTOForDetail extends ContinentDTOForList {

	private List<EntityModel<RegionDTOForList>> regions;

	public ContinentDTOForDetail(Integer id, String name, List<RegionDTOForList> regions) {
		super(id, name);
		this.setRegions(regions);
	}

	public List<EntityModel<RegionDTOForList>> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionDTOForList> regions) {
		this.regions = regions.stream()
				.map(region -> EntityModel.of(region,
						linkTo(methodOn(NationController.class).oneRegion(region.getRegionId())).withSelfRel()))
				.collect(Collectors.toList());
	}

	public static ContinentDTOForDetail toDTO(Continent continent) {
		List<RegionDTOForList> regions = continent.getRegions().stream()
				.map(RegionDTOForList::toDTO)
				.collect(Collectors.toList());
		return new ContinentDTOForDetail(continent.getId(), continent.getName(), regions);
	}
}
