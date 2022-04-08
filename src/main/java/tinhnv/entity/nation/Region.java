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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "regions")
public class Region {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Integer id;

	@Getter @Setter
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "continent_id", nullable = false)
	private Continent continent;

	@Getter @Setter
	@OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
	private List<Country> countries;

	public Region(String name, Continent continent, List<Country> countries) {
		super();
		this.name = name;
		this.continent = continent;
		this.countries = countries;
	}

	public Region(String regionName, Continent currentContinent) {
		this(regionName, currentContinent, null);
	}

}
