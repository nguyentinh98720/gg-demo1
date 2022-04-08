package tinhnv.dto.nation.statisticdto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StatisticDTO {

	private Integer year;
	private Integer population;
	private BigDecimal gdp;

}
