package tinhnv.entity.nation;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_id")
	private Integer id;
	
	@Column(length=50, nullable=true)
	private String name;
	
	@Column(precision=10, scale=2)
	private BigDecimal area;
	
	@Column(nullable=true)
	private Date national_day;
	
	@Column(length=2, nullable=false, unique=true, columnDefinition="CHAR")
	private String country_code2;
	
	@Column(length=3, nullable=false, unique=true, columnDefinition="CHAR")
	private String country_code3;
	
	@ManyToOne
	@JoinColumn(name="region_id", nullable=false)
	private Region region;
	
	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<Statistic> stats;
	
	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<CountryLanguages> languages;

	public List<CountryLanguages> getLanguages() {
		return languages;
	}

	public void setLanguages(List<CountryLanguages> languages) {
		this.languages = languages;
	}

	public Country() {
		super();
	}

	public Country(String name, BigDecimal area, Date national_day, String country_code2, String country_code3,
			Region region, List<Statistic> stats) {
		super();
		this.name = name;
		this.area = area;
		this.national_day = national_day;
		this.country_code2 = country_code2;
		this.country_code3 = country_code3;
		this.region = region;
		this.stats = stats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public Date getNational_day() {
		return national_day;
	}

	public void setNational_day(Date national_day) {
		this.national_day = national_day;
	}

	public String getCountry_code2() {
		return country_code2;
	}

	public void setCountry_code2(String country_code2) {
		this.country_code2 = country_code2;
	}

	public String getCountry_code3() {
		return country_code3;
	}

	public void setCountry_code3(String country_code3) {
		this.country_code3 = country_code3;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Statistic> getStats() {
		return stats;
	}

	public void setStats(List<Statistic> stats) {
		this.stats = stats;
	}

	public Integer getId() {
		return id;
	}
	
}
