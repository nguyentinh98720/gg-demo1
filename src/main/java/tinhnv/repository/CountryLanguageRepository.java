package tinhnv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.CountryLanguages;
import tinhnv.entity.nation.idclass.CountryLanguageId;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguages, CountryLanguageId> {

}
