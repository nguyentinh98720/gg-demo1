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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tinhnv.entity.nation.idclass.CountryStatisticId;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "country_stats")
@IdClass(CountryStatisticId.class)
public class Statistic {

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

}
