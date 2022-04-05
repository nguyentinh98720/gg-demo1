package tinhnv.dto.nation.countryDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;

import tinhnv.controller.NationController;
import tinhnv.dto.nation.languageDTO.LanguageDTO;
import tinhnv.dto.nation.regionDTO.RegionDTOForList;
import tinhnv.dto.nation.statisticDTO.StatisticDTO;
import tinhnv.entity.nation.Country;

public class CountryDTOForDetail extends CountryDTOForCreate {

	private EntityModel<RegionDTOForList> region;
	private List<StatisticDTO> statistics;
	private List<LanguageDTO> languages;
	

	public CountryDTOForDetail() {
		super();
	}

	public CountryDTOForDetail(Integer countryId, String countryName, BigDecimal area,
			Date nationalDay, String countryCode2, String countryCode3,
			RegionDTOForList region, List<StatisticDTO> statistics, List<LanguageDTO> languages) {
		super(countryId, countryName, area, nationalDay, countryCode2, countryCode3);
		this.setRegion(region);
		this.statistics = statistics;
		this.setLanguages(languages);
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
		List<StatisticDTO> statistics = country.getStats().stream()
				.map(StatisticDTO::toDTO).collect(Collectors.toList());
		List<LanguageDTO> languages = country.getLanguages().stream()
//				.map(ct -> ct.getLanguage())
				.map(LanguageDTO::toDTO)
				.collect(Collectors.toList());
		return new CountryDTOForDetail(country.getId(), country.getName(),
				country.getArea(), country.getNational_day(), country.getCountry_code2(),
				country.getCountry_code3(), RegionDTOForList.toDTO(country.getRegion()),
				statistics, languages);
	}
}
