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
	
	@Column(name="national_day", nullable=true)
	private Date nationalDay;
	
	@Column(nullable=false, name="country_code2",
			unique=true, columnDefinition="CHAR(2)")
	private String countryCodeTwoChars;
	
	@Column(nullable=false, name="country_code3",
			unique=true, columnDefinition="CHAR(3)")
	private String countryCodeThreeChars;
	
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

	public Date getNationalDay() {
		return nationalDay;
	}

	public void setNationalDay(Date national_day) {
		this.nationalDay = national_day;
	}

	public String getCountryCodeTwoChars() {
		return countryCodeTwoChars;
	}

	public void setCountryCodeTwoChars(String countryCodeTwoChars) {
		this.countryCodeTwoChars = countryCodeTwoChars;
	}

	public String getCountryCodeThreeChars() {
		return countryCodeThreeChars;
	}

	public void setCountryCodeThreeChars(String countryCodeThreeChars) {
		this.countryCodeThreeChars = countryCodeThreeChars;
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
