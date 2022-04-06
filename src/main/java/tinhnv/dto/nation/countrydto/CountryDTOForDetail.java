package tinhnv.dto.nation.countrydto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.EntityModel;

import tinhnv.controller.NationController;
import tinhnv.dto.nation.languagedto.LanguageDTO;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.dto.nation.statisticdto.StatisticDTO;
import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.builder.CountryInformationBuilder;
import tinhnv.entity.nation.concreate.CountryInformationConcreateBuilder;

public class CountryDTOForDetail extends CountryDTOForCreate {

	private EntityModel<RegionDTOForList> region;
	private List<StatisticDTO> statistics;
	private List<LanguageDTO> languages;
	

	public CountryDTOForDetail() {
		super();
	}

	public EntityModel<RegionDTOForList> getRegion() {
		return region;
	}

	public List<StatisticDTO> getStatistics() {
		return statistics;
	}

	public void setRegion(RegionDTOForList region) {
		this.region = EntityModel.of(region, linkTo(methodOn(NationController.class).oneRegion(region.getRegionId())).withSelfRel());
	}

	public void setStatistics(List<StatisticDTO> statistics) {
		this.statistics = statistics;
	}
	
	public List<LanguageDTO> getLanguages() {
		return languages;
	}

	public void setLanguages(List<LanguageDTO> languages) {
		this.languages = languages;
	}

	public static CountryDTOForDetail toDTO(Country country) {
		CountryInformationBuilder builder = new CountryInformationConcreateBuilder();
		builder.setId(country.getId())
		.setName(country.getName())
		.setArea(country.getArea())
		.setNationalDay(country.getNationalDay())
		.setCountryCodeTwoChars(country.getCountryCodeTwoChars())
		.setCountryCodeThreeChars(country.getCountryCodeThreeChars())
		.setRegion(country.getRegion())
		.setLanguages(country.getLanguages())
		.setStatistics(country.getStats());
		return builder.buildDetailDTO();
	}
}
