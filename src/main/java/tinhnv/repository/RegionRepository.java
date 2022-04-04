package tinhnv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
