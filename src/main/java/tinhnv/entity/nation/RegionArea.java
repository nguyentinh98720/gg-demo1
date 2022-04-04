package tinhnv.entity.nation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region_areas")
public class RegionArea {

	@Id
	@Column(name="region_name", length = 100)
	private String name;

	@Column(name = "region_area", precision = 15, scale = 2)
	private BigDecimal area;

	public RegionArea(String name, BigDecimal area) {
		super();
		this.name = name;
		this.area = area;
	}

	public RegionArea() {
		super();
		// TODO Auto-generated constructor stub
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
}
