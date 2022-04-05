package tinhnv.entity.nation;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "continents")
public class Continent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "continent_id")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "continent", fetch = FetchType.LAZY)
	private List<Region> regions;

	public Continent() {
		super();
	}

	public Continent(String name, List<Region> regions) {
		super();
		this.name = name;
		this.regions = regions;
	}

	public Continent(String continentName) {
		this(continentName, null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

}
