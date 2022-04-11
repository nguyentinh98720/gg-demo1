package tinhnv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import tinhnv.dto.nation.continentdto.ContinentDTOForDetail;
import tinhnv.dto.nation.continentdto.ContinentDTOForList;
import tinhnv.dto.nation.countrydto.CountryDTOForCreate;
import tinhnv.dto.nation.countrydto.CountryDTOForDetail;
import tinhnv.dto.nation.countrydto.CountryDTOForList;
import tinhnv.dto.nation.languagedto.LanguageDTOCreate;
import tinhnv.dto.nation.languagedto.TinyLanguageDTO;
import tinhnv.dto.nation.regiondto.RegionDTOForDetail;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.dto.nation.statisticdto.StatisticDTO;
import tinhnv.entity.nation.Continent;
import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.CountryLanguages;
import tinhnv.entity.nation.Language;
import tinhnv.entity.nation.QContinent;
import tinhnv.entity.nation.QCountry;
import tinhnv.entity.nation.QLanguage;
import tinhnv.entity.nation.QRegion;
import tinhnv.entity.nation.QRegionArea;
import tinhnv.entity.nation.QStatistic;
import tinhnv.entity.nation.Region;
import tinhnv.entity.nation.RegionArea;
import tinhnv.entity.nation.Statistic;
import tinhnv.entity.nation.builder.CountryInformationBuilder;
import tinhnv.entity.nation.concreate.CountryInformationConcreateBuilder;
import tinhnv.entity.nation.idclass.CountryStatisticId;
import tinhnv.mapper.ContinentMapper;
import tinhnv.mapper.CountryMapper;
import tinhnv.mapper.RegionMapper;
import tinhnv.mapper.StatisticMapper;
import tinhnv.repository.ContinentRepository;
import tinhnv.repository.CountryLanguageRepository;
import tinhnv.repository.CountryRepository;
import tinhnv.repository.LanguageRepository;
import tinhnv.repository.RegionAreaRepository;
import tinhnv.repository.RegionRepository;
import tinhnv.repository.StatisticRepository;
import tinhnv.service.INationService;
import tinhnv.transfer.Paging;

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
	@Autowired
	LanguageRepository languageRepo;
	@Autowired
	StatisticRepository statisticRepo;
	@Autowired
	CountryLanguageRepository countryLanguageRepo;
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	CountryMapper countryMapper;
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	ContinentMapper continentMapper;
	@Autowired
	StatisticMapper statisticMapper;
	
	private JPAQueryFactory queryFactory;
	
	public NationService() {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}
	
	@Override
	public List<ContinentDTOForList> allContinent() {
		return continentMapper.toDTOForList(continent.findAll());
	}

	@Override
	public ContinentDTOForDetail detailContinent(Integer id) {
		Optional<Continent> detail = continent.findById(id);
		if(detail.isPresent()) {
			return continentMapper.toDTOForDetail(detail.get());
		}
		throw new EntityNotFoundException("Không tìm thấy thực thể yêu cầu! <continent with id: " + id + ">");
	}

	@Override
	public List<RegionDTOForList> allRegions() {
		return regionMapper.toDTOForList(region.findAll());
	}

	@Override
	public RegionDTOForDetail detailRegion(Integer id) {
		Optional<Region> detail = region.findById(id);
		RegionDTOForDetail dto = null;
		if(detail.isPresent()) {
			Optional<RegionArea> area = regionArea.findByName(detail.get().getName());
			
			dto = regionMapper.toDTOForDetail(detail.get());
			dto.setArea(area.isPresent()?area.get().getArea():null);
			return dto;
		}
		throw new EntityNotFoundException("<Không tìm thấy thực thể yêu cầu!> region with id: " + id + " was not found.");
	}

	@Override
	public List<CountryDTOForList> allCountries() {
		return country.findAll().stream()
				.map(countryMapper::toDTOForList)
				.collect(Collectors.toList());
	}

	@Override
	public CountryDTOForDetail detailCountry(Integer id) {
		Optional<Country> detail = country.findById(id);
		if(detail.isPresent()) {
			return countryMapper.toDTOForDetail(detail.get());
		}
		throw new EntityNotFoundException("Không tìm thấy thực thể yêu cầu! <country with id: " + id + ">");
	}

	@Override
	public Language createLanguage(String language) {
		Language newLanguage = new Language(language);
		return languageRepo.save(newLanguage);
	}

	@Override
	public ContinentDTOForList createContinent(String continentName) {
		Continent newContinent = new Continent(continentName);
		return continentMapper.toDTOForList(continent.save(newContinent));
	}

	@Override
	public RegionDTOForList createRegion(String regionName, Integer continentId, BigDecimal area) {
		Continent currentContinent = continent.getById(continentId);
		Region newRegion = new Region(regionName, currentContinent);
		region.save(newRegion);
		RegionArea newRegionArea = new RegionArea(regionName, area);
		regionArea.save(newRegionArea);
		return regionMapper.toDTOForList(newRegion);
	}

	@Override
	public CountryDTOForList createCountry(CountryDTOForCreate countryData, Integer regionId, List<TinyLanguageDTO> listLang) {
		Region currentRegion = region.getById(regionId);
		CountryInformationBuilder builder = new CountryInformationConcreateBuilder();
		Country newCountry = builder.setName(countryData.getCountryName())
				.setArea(countryData.getArea())
				.setNationalDay(countryData.getNationalDay())
				.setCountryCodeTwoChars(countryData.getCountryCodeTwoChars())
				.setCountryCodeThreeChars(countryData.getCountryCodeThreeChars())
				.setRegion(currentRegion)
				.buildEntity();
		List<CountryLanguages> countryLanguages = new ArrayList<>();
		listLang.forEach(lang -> {
			Language language = languageRepo.getById(lang.getLanguageId());
			CountryLanguages countryLan = new CountryLanguages(newCountry, language, lang.isOfficial());
			countryLanguages.add(countryLan);
		});
		newCountry.setLanguages(countryLanguages);
		CountryDTOForList result = countryMapper.toDTOForList(country.save(newCountry));
		countryLanguageRepo.saveAll(countryLanguages);
		return result;
	}

	@Override
	public StatisticDTO createStatistic(StatisticDTO statistic, Integer countryId) {
		Country currentCountry = country.getById(countryId);
		Statistic newStatistic = new Statistic(statistic.getYear(), statistic.getPopulation(), statistic.getGdp(), currentCountry);
		return statisticMapper.toDTO(statisticRepo.save(newStatistic));
	}

	@Override
	public void deleteLanguage(Integer languageId) {
		languageRepo.deleteById(languageId);
	}

	@Override
	public void deleteStatistic(Integer countryId, Integer year) {
		statisticRepo.deleteById(new CountryStatisticId(countryId, year));
	}

	@Override
	public void deleteCountry(Integer countryId) {
		country.deleteById(countryId);
	}

	@Override
	public void deleteRegion(Integer regionId) {
		region.deleteById(regionId);
	}

	@Override
	public void deleteContinent(Integer continentId) {
		continent.deleteById(continentId);
	}

	@Override
	public LanguageDTOCreate updateLanguage(LanguageDTOCreate language) {
		
		QLanguage qLang = QLanguage.language;
		queryFactory.update(qLang)
				.where(qLang.id.eq(language.getId()))
				.set(qLang.name, language.getName())
				.execute();
		return language;
	}

	@Override
	public ContinentDTOForList updateContinent(ContinentDTOForList continent) {
		QContinent qContinent = QContinent.continent;
		queryFactory.update(qContinent)
		.where(qContinent.id.eq(continent.getContinentId()))
		.set(qContinent.name, continent.getContinentName())
		.execute();
		return continent;
	}

	@Override
	public RegionDTOForList updateRegion(RegionDTOForList region, Integer continentId, BigDecimal area) {
		QRegion qRegion = QRegion.region;
		QRegionArea qRegionArea = QRegionArea.regionArea;
		QContinent qContinent = QContinent.continent;
		queryFactory.update(qRegion)
		.where(qRegion.id.eq(region.getRegionId()))
		.set(qRegion.name, region.getRegionName())
		.execute();
		if(continentId > 0) {
			Continent c = queryFactory.selectFrom(qContinent)
					.where(qContinent.id.eq(continentId))
					.fetchOne();
			queryFactory.update(qRegion)
			.set(qRegion.continent, c)
			.execute();
		}
		if(!area.equals(BigDecimal.ZERO)) {
			queryFactory.update(qRegionArea)
			.where(qRegionArea.name.eq(region.getRegionName()))
			.set(qRegionArea.area, area)
			.execute();
		}
		return region;
	}

	@Override
	public CountryDTOForList updateCountry(CountryDTOForDetail country, Integer regionId) {
		QCountry qCountry = QCountry.country;
		QRegion qRegion = QRegion.region;
		queryFactory.update(qCountry)
		.where(qCountry.id.eq(country.getCountryId()))
		.set(qCountry.name, country.getCountryName())
		.set(qCountry.area, country.getArea())
		.set(qCountry.countryCodeTwoChars, country.getCountryCodeTwoChars())
		.set(qCountry.countryCodeThreeChars, country.getCountryCodeThreeChars())
		.set(qCountry.nationalDay, country.getNationalDay())
		.execute();
		if(regionId > 0) {
			Region r = queryFactory.selectFrom(qRegion)
					.where(qRegion.id.eq(regionId))
					.fetchOne();
			queryFactory.update(qCountry)
			.where(qCountry.id.eq(country.getCountryId()))
			.set(qCountry.region, r)
			.execute();
		}
		return country;
	}

	@Override
	public StatisticDTO updateStatistic(StatisticDTO statistic, Integer countryId) {
		QStatistic qStat = QStatistic.statistic;
		QCountry qCountry = QCountry.country;
		Country c = queryFactory.selectFrom(qCountry)
				.where(qCountry.id.eq(countryId))
				.fetchOne();
		queryFactory.update(qStat)
		.where(qStat.country.eq(c))
		.set(qStat.year, statistic.getYear())
		.set(qStat.population, statistic.getPopulation())
		.set(qStat.gdp, statistic.getGdp())
		.execute();
		return statistic;
	}

	@Override
	public Paging<Language> listLanguages(Integer pageNo, Integer pageSize) {
		Pageable page = PageRequest.of(pageNo, pageSize);
		Page<Language> languagePage = languageRepo.findAll(page);
		return Paging.paging(languagePage);
	}

	@Override
	public Paging<CountryDTOForList> listCountries(Integer pageNo, Integer pageSize) {
		Pageable page = PageRequest.of(pageNo, pageSize);
		Page<CountryDTOForList> countryPage = country.findAll(page)
				.map(countryMapper::toDTOForList);
		return Paging.paging(countryPage);
	}

	@Override
	public List<RegionDTOForList> listRegion() {
		return regionMapper.toDTOForList(region.findAll());
	}

	@Override
	public List<ContinentDTOForList> listContinent() {
		return continentMapper.toDTOForList(continent.findAll());
	}

}
