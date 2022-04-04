package tinhnv.dto.nation.countryDTO;

import tinhnv.entity.nation.Country;

public class CountryDTOForList {

	private Integer countryId;
	private String countryName;

	public CountryDTOForList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountryDTOForList(Integer countryId, String countryName) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public static CountryDTOForList toDTO(Country country) {
		return new CountryDTOForList(country.getId(), country.getName());
	}
}
