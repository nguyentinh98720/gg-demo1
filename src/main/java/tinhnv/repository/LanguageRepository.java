package tinhnv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tinhnv.entity.nation.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

}
