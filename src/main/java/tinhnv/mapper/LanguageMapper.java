package tinhnv.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tinhnv.dto.nation.languagedto.LanguageDTO;
import tinhnv.dto.nation.languagedto.LanguageDTOCreate;
import tinhnv.dto.nation.languagedto.TinyLanguageDTO;
import tinhnv.entity.nation.CountryLanguages;
import tinhnv.entity.nation.Language;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

	@Mapping(target = "language", expression = "java(entity.getLanguage().getName())")
	LanguageDTO toDTO(CountryLanguages entity);
	
	List<LanguageDTO> toDTO(List<CountryLanguages> entities);
	
	@Mapping(target = "languageId", expression = "java(entity.getLanguage().getId())")
	TinyLanguageDTO toTinyDTO(CountryLanguages entity);
	
	List<TinyLanguageDTO> toTinyDTO(List<CountryLanguages> entities);
	
	Language toEntity(LanguageDTOCreate dto);
}
