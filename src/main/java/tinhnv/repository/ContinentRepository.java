package tinhnv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.Continent;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {

}
