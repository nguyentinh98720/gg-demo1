package tinhnv.service;

import java.math.BigDecimal;
import java.util.List;

import tinhnv.dto.nation.continentDTO.ContinentDTOForDetail;
import tinhnv.dto.nation.continentDTO.ContinentDTOForList;
import tinhnv.dto.nation.countryDTO.CountryDTOForCreate;
import tinhnv.dto.nation.countryDTO.CountryDTOForDetail;
import tinhnv.dto.nation.countryDTO.CountryDTOForList;
import tinhnv.dto.nation.languageDTO.TinyLanguageDTO;
import tinhnv.dto.nation.regionDTO.RegionDTOForDetail;
import tinhnv.dto.nation.regionDTO.RegionDTOForList;
import tinhnv.dto.nation.statisticDTO.StatisticDTO;
import tinhnv.entity.nation.Language;

public interface INationService {

	List<ContinentDTOForList> allContinent();
	ContinentDTOForDetail detailContinent(Integer id);
	
	List<RegionDTOForList> allRegions();
	RegionDTOForDetail detailRegion(Integer id);
	
	List<CountryDTOForList> allCountries();
	CountryDTOForDetail detailCountry(Integer id);
	
	Language createLanguage(String language);
	ContinentDTOForList createContinent(String continentName);
	RegionDTOForList createRegion(String regionName, Integer continentId, BigDecimal area);
	CountryDTOForList createCountry(CountryDTOForCreate country, Integer regionId, List<TinyLanguageDTO> languages);
	StatisticDTO createStatistic(StatisticDTO statistic, Integer countryId);
	
	void deleteLanguage(Integer languageId);
	void deleteStatistic(Integer countryId, Integer year);
	void deleteCountry(Integer countryId);
	void deleteRegion(Integer regionId);
	void deleteContinent(Integer continentId);
	
	Language updateLanguage(Language language);
	ContinentDTOForList updateContinent(ContinentDTOForList continentName);
	RegionDTOForList updateRegion(RegionDTOForList region, Integer continentId, BigDecimal area);
	CountryDTOForList updateCountry(CountryDTOForDetail country, Integer regionId);
	StatisticDTO updateStatistic(StatisticDTO statistic, Integer countryId);
}
