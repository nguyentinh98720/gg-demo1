package tinhnv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.RegionArea;

public interface RegionAreaRepository extends JpaRepository<RegionArea, Integer> {

	Optional<RegionArea> findByName(String name);

}
