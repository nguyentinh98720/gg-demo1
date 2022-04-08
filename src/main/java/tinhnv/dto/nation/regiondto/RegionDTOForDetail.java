package tinhnv.dto.nation.regiondto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tinhnv.controller.NationController;
import tinhnv.dto.nation.continentdto.ContinentDTOForList;
import tinhnv.dto.nation.countrydto.CountryDTOForList;

@NoArgsConstructor
public class RegionDTOForDetail extends RegionDTOForList {

	@Getter @Setter
	private ContinentDTOForList continent;
	@Getter @Setter
	private BigDecimal area;
	@Getter
	private List<EntityModel<CountryDTOForList>> countries;

	public void setCountries(List<CountryDTOForList> countries) {
		this.countries = countries.stream()
				.map(country -> EntityModel.of(country,
						linkTo(methodOn(NationController.class).oneCountry(country.getCountryId())).withSelfRel()
						))
				.collect(Collectors.toList());
	}
}
