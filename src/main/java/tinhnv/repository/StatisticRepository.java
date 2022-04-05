package tinhnv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.Statistic;
import tinhnv.entity.nation.idClass.CountryStatisticId;

public interface StatisticRepository extends JpaRepository<Statistic, CountryStatisticId> {

}
