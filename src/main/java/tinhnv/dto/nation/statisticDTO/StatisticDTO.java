package tinhnv.dto.nation.statisticDTO;

import java.math.BigDecimal;

import tinhnv.entity.nation.Statistic;

public class StatisticDTO {

	private Integer year;
	private Integer population;
	private BigDecimal gdp;

	public StatisticDTO(Integer year, Integer population, BigDecimal gdp) {
		super();
		this.year = year;
		this.population = population;
		this.gdp = gdp;
	}

	public StatisticDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getYear() {
		return year;
	}

	public Integer getPopulation() {
		return population;
	}

	public BigDecimal getGdp() {
		return gdp;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public void setGdp(BigDecimal gdp) {
		this.gdp = gdp;
	}

	public static StatisticDTO toDTO(Statistic statistic) {
		return new StatisticDTO(statistic.getYear(), statistic.getPopulation(), statistic.getGdp());
	}
}
