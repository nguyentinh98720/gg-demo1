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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "continents")
public class Continent {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "continent_id")
	private Integer id;

	@Getter @Setter
	@Column(name = "name", nullable = false)
	private String name;


	@Getter @Setter
	@OneToMany(mappedBy = "continent", fetch = FetchType.LAZY)
	private List<Region> regions;

	public Continent(String continentName) {
		this.name = continentName;
	}
	
}
