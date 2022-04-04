package tinhnv.entity.nation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tinhnv.entity.nation.idClass.CountryStatisticId;

@Entity
@Table(name = "country_stats")
@IdClass(CountryStatisticId.class)
public class Statistic {

//	@Id
//	@Column(name = "country_id", insertable=false, updatable=false)
//	private Integer country_id;

	@Id
	@Column(nullable = false)
	private Integer year;

	private Integer population;

	@Column(precision = 15, scale = 0)
	private BigDecimal gdp;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable=false)
	private Country country;

	public Statistic() {
		super();
	}

	public Statistic(Country country, Integer year,
			Integer population, BigDecimal gdp) {
		super();
//		this.country_id = country_id;
		this.year = year;
		this.population = population;
		this.gdp = gdp;
		this.country = country;
	}

//	public Integer getCountry_id() {
//		return country_id;
//	}
//
//	public void setCountry_id(Integer country_id) {
//		this.country_id = country_id;
//	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public BigDecimal getGdp() {
		return gdp;
	}

	public void setGdp(BigDecimal gdp) {
		this.gdp = gdp;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
