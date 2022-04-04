package tinhnv.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tinhnv.dto.nation.continentDTO.ContinentDTOForDetail;
import tinhnv.dto.nation.continentDTO.ContinentDTOForList;
import tinhnv.dto.nation.countryDTO.CountryDTOForDetail;
import tinhnv.dto.nation.countryDTO.CountryDTOForList;
import tinhnv.dto.nation.regionDTO.RegionDTOForDetail;
import tinhnv.dto.nation.regionDTO.RegionDTOForList;
import tinhnv.entity.nation.Continent;
import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.Region;
import tinhnv.entity.nation.RegionArea;
import tinhnv.repository.ContinentRepository;
import tinhnv.repository.CountryRepository;
import tinhnv.repository.RegionAreaRepository;
import tinhnv.repository.RegionRepository;
import tinhnv.service.INationService;

@Service
@Transactional
public class NationService implements INationService {
	
	@Autowired
	ContinentRepository continent;
	@Autowired
	RegionRepository region;
	@Autowired
	CountryRepository country;
	@Autowired
	RegionAreaRepository regionArea;

	@Override
	public List<ContinentDTOForList> allContinent() {
		return continent.findAll().stream()
				.map(ContinentDTOForList::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ContinentDTOForDetail detailContinent(Integer id) {
		Optional<Continent> detail = continent.findById(id);
		if(detail.isPresent()) {
			return ContinentDTOForDetail.toDTO(detail.get());
		}
		throw new EntityNotFoundException("continent not found");
		
//		return detail.isPresent()? ContinentDTOForDetail.toDTO(detail.get()) : null;
	}

	@Override
	public List<RegionDTOForList> allRegions() {
		return region.findAll().stream()
				.map(RegionDTOForList::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public RegionDTOForDetail detailRegion(Integer id) {
		Optional<Region> detail = region.findById(id);
		RegionDTOForDetail dto = null;
		if(detail.isPresent()) {
			Optional<RegionArea> area = regionArea.findByName(detail.get().getName());
			
			dto = RegionDTOForDetail.toDTO(detail.get());
			dto.setArea(area.isPresent()?area.get().getArea():null);
			return dto;
		}
		throw new EntityNotFoundException("region not found");
	}

	@Override
	public List<CountryDTOForList> allCountries() {
		return country.findAll().stream()
				.map(CountryDTOForList::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CountryDTOForDetail detailCountry(Integer id) {
		Optional<Country> detail = country.findById(id);
		if(detail.isPresent()) {
			return CountryDTOForDetail.toDTO(detail.get());
		}
		throw new EntityNotFoundException("country not found");
	}

}
