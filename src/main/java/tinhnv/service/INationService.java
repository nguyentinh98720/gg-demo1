package tinhnv.service;

import java.util.List;

import tinhnv.dto.nation.continentDTO.ContinentDTOForDetail;
import tinhnv.dto.nation.continentDTO.ContinentDTOForList;
import tinhnv.dto.nation.countryDTO.CountryDTOForDetail;
import tinhnv.dto.nation.countryDTO.CountryDTOForList;
import tinhnv.dto.nation.regionDTO.RegionDTOForDetail;
import tinhnv.dto.nation.regionDTO.RegionDTOForList;

public interface INationService {

	List<ContinentDTOForList> allContinent();
	ContinentDTOForDetail detailContinent(Integer id);
	
	List<RegionDTOForList> allRegions();
	RegionDTOForDetail detailRegion(Integer id);
	
	List<CountryDTOForList> allCountries();
	CountryDTOForDetail detailCountry(Integer id);
}
