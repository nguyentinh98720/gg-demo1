package tinhnv.entity.nation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "region_areas")
public class RegionArea {

	@Id
	@Column(name="region_name", length = 100)
	private String name;

	@Column(name = "region_area", precision = 15, scale = 2)
	private BigDecimal area;

}
