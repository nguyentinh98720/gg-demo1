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

public class CountryDTOForDetail extends CountryDTOForList {

	private BigDecimal area;
	private Date nationalDay;
	private String countryCode2;
	private String countryCode3;
	private EntityModel<RegionDTOForList> region;
	private List<StatisticDTO> statistics;
	private List<LanguageDTO> languages;
	

	public CountryDTOForDetail(Integer countryId, String countryName) {
		super(countryId, countryName);
	}

	//TODO constructor khong nen qua ngieu tham so, tot nhat dung Builder pattern
	public CountryDTOForDetail(Integer countryId, String countryName, BigDecimal area,
			Date nationalDay, String countryCode2, String countryCode3,
			RegionDTOForList region, List<StatisticDTO> statistics, List<LanguageDTO> languages) {
		super(countryId, countryName);
		this.area = area;
		this.nationalDay = nationalDay;
		this.countryCode2 = countryCode2;
		this.countryCode3 = countryCode3;
		this.setRegion(region);
		this.statistics = statistics;
		this.setLanguages(languages);
	}

	public BigDecimal getArea() {
		return area;
	}

	public Date getNationalDay() {
		return nationalDay;
	}

	public String getCountryCode2() {
		return countryCode2;
	}

	public String getCountryCode3() {
		return countryCode3;
	}

	public EntityModel<RegionDTOForList> getRegion() {
		return region;
	}

	public List<StatisticDTO> getStatistics() {
		return statistics;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public void setNationalDay(Date nationalDay) {
		this.nationalDay = nationalDay;
	}

	public void setCountryCode2(String countryCode2) {
		this.countryCode2 = countryCode2;
	}

	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
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
