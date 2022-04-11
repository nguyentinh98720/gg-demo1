package tinhnv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.Statistic;
import tinhnv.entity.nation.idclass.CountryStatisticId;

public interface StatisticRepository extends JpaRepository<Statistic, CountryStatisticId> {

}
