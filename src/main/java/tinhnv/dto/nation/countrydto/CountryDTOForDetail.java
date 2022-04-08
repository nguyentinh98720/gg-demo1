package tinhnv.dto.nation.countrydto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.EntityModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tinhnv.controller.NationController;
import tinhnv.dto.nation.languagedto.LanguageDTO;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.dto.nation.statisticdto.StatisticDTO;

@NoArgsConstructor
public class CountryDTOForDetail extends CountryDTOForCreate {

	@Getter
	private EntityModel<RegionDTOForList> region;
	@Getter @Setter
	private List<StatisticDTO> statistics;
	@Getter @Setter
	private List<LanguageDTO> languages;

	public void setRegion(RegionDTOForList region) {
		this.region = EntityModel.of(region, linkTo(methodOn(NationController.class).oneRegion(region.getRegionId())).withSelfRel());
	}

}
