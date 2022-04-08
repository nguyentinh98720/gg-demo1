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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="countries")
public class Country {

	@Getter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_id")
	private Integer id;
	

	@Getter @Setter
	@Column(length=50, nullable=true)
	private String name;

	@Getter @Setter
	@Column(precision=10, scale=2)
	private BigDecimal area;
	
	@Getter @Setter
	@Column(name="national_day", nullable=true)
	private Date nationalDay;
	
	@Getter @Setter
	@Column(nullable=false, name="country_code2",
			unique=true, columnDefinition="CHAR(2)")
	private String countryCodeTwoChars;
	
	@Getter @Setter
	@Column(nullable=false, name="country_code3",
			unique=true, columnDefinition="CHAR(3)")
	private String countryCodeThreeChars;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="region_id", nullable=false)
	private Region region;
	
	@Getter @Setter
	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<Statistic> stats;
	
	@Getter @Setter
	@OneToMany(mappedBy="country", fetch=FetchType.LAZY)
	private List<CountryLanguages> languages;

}
