package tinhnv.dto.nation.continentdto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tinhnv.controller.NationController;
import tinhnv.dto.nation.regiondto.RegionDTOForList;

@NoArgsConstructor
public class ContinentDTOForDetail extends ContinentDTOForList {

	@Getter
	private List<EntityModel<RegionDTOForList>> regions;

	public ContinentDTOForDetail(Integer id, String name, List<RegionDTOForList> regions) {
		super(id, name);
		this.setRegions(regions);
	}

	public void setRegions(List<RegionDTOForList> regions) {
		this.regions = regions.stream()
				.map(region -> EntityModel.of(region,
						linkTo(methodOn(NationController.class).oneRegion(region.getRegionId())).withSelfRel()))
				.collect(Collectors.toList());
	}

}
