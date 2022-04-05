package tinhnv.dto.nation.countryDTO;

import java.math.BigDecimal;
import java.sql.Date;

public class CountryDTOForCreate extends CountryDTOForList {

	private BigDecimal area;
	private Date nationalDay;
	private String countryCode2;
	private String countryCode3;
	
	public CountryDTOForCreate() {
		super();
	};
	
	public CountryDTOForCreate(Integer countryId, String countryName, BigDecimal area,
			Date nationalDay, String countryCode2, String countryCode3) {
		super(countryId, countryName);
		this.area = area;
		this.nationalDay = nationalDay;
		this.countryCode2 = countryCode2;
		this.countryCode3 = countryCode3;
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
}
