package tinhnv.entity.nation;

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
@Table(name = "regions")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Integer id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "continent_id", nullable = false)
	private Continent continent;

	@OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
	private List<Country> countries;

	public Region() {
		super();
	}

	public Region(String name, Continent continent, List<Country> countries) {
		super();
		this.name = name;
		this.continent = continent;
		this.countries = countries;
	}

	public Region(String regionName, Continent continent) {
		this(regionName, continent, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Integer getId() {
		return id;
	}

}
