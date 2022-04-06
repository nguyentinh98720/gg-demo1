package tinhnv.dto.nation.regiondto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;

import tinhnv.controller.NationController;
import tinhnv.dto.nation.continentdto.ContinentDTOForList;
import tinhnv.dto.nation.countrydto.CountryDTOForList;
import tinhnv.entity.nation.Region;

public class RegionDTOForDetail extends RegionDTOForList {

	private ContinentDTOForList continent;
	private BigDecimal area;
	private List<EntityModel<CountryDTOForList>> countries;

	public RegionDTOForDetail(Integer regionId, String regionName) {
		super(regionId, regionName);
	}

	public RegionDTOForDetail(Integer regionId, String regionName, ContinentDTOForList continent,
			BigDecimal area, List<CountryDTOForList> countries) {
		super(regionId, regionName);
		this.continent = continent;
		this.area = area;
		this.setCountries(countries);
	}

	public ContinentDTOForList getContinent() {
		return continent;
	}

	public List<EntityModel<CountryDTOForList>> getCountries() {
		return countries;
	}

	public void setContinent(ContinentDTOForList continent) {
		this.continent = continent;
	}

	public void setCountries(List<CountryDTOForList> countries) {
		this.countries = countries.stream()
				.map(country -> EntityModel.of(country,
						linkTo(methodOn(NationController.class).oneCountry(country.getCountryId())).withSelfRel()
						))
				.collect(Collectors.toList());
	}
	
	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public static RegionDTOForDetail toDTO(Region region) {
		ContinentDTOForList continent = ContinentDTOForList.toDTO(region.getContinent());
		List<CountryDTOForList> countries = region.getCountries().stream()
				.map(CountryDTOForList::toDTO)
				.collect(Collectors.toList());
		return new RegionDTOForDetail(region.getId(), region.getName(), continent,
				null, countries);
	}
}
