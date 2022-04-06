package tinhnv.service;

import java.math.BigDecimal;
import java.util.List;

import tinhnv.dto.nation.continentdto.ContinentDTOForDetail;
import tinhnv.dto.nation.continentdto.ContinentDTOForList;
import tinhnv.dto.nation.countrydto.CountryDTOForCreate;
import tinhnv.dto.nation.countrydto.CountryDTOForDetail;
import tinhnv.dto.nation.countrydto.CountryDTOForList;
import tinhnv.dto.nation.languagedto.TinyLanguageDTO;
import tinhnv.dto.nation.regiondto.RegionDTOForDetail;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.dto.nation.statisticdto.StatisticDTO;
import tinhnv.entity.nation.Language;
import tinhnv.transfer.Paging;

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
	
	Paging<Language> listLanguages(Integer pageNo, Integer pageNumber);
	Paging<CountryDTOForList> listCountries(Integer pageNo, Integer pageNumber);
	List<RegionDTOForList> listRegion();
	List<ContinentDTOForList> listContinent();
}
