package tinhnv.entity.nation.idClass;

import java.io.Serializable;

public class CountryStatisticId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer country;
	private Integer year;

	public CountryStatisticId(Integer country, Integer year) {
		this.country = country;
		this.year = year;
	}

	public CountryStatisticId() {
		super();
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		return country + year;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null | obj.getClass() != this.getClass()) return false;
		CountryStatisticId csi = (CountryStatisticId) obj;
		return (this.country == csi.getCountry() && this.year == csi.getYear());
	}
	
	

}
