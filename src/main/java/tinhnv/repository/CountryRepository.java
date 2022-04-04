package tinhnv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
