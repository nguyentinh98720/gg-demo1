package tinhnv.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tinhnv.dto.nation.statisticdto.StatisticDTO;
import tinhnv.entity.nation.Statistic;

@Mapper(componentModel = "spring")
public interface StatisticMapper {

	StatisticDTO toDTO(Statistic entity);
	
	List<StatisticDTO> toDTO(List<Statistic> entities);
}
