package tinhnv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import tinhnv.dto.nation.continentdto.ContinentDTOForList;
import tinhnv.dto.nation.countrydto.CountryDTOForCreate;
import tinhnv.dto.nation.countrydto.CountryDTOForDetail;
import tinhnv.dto.nation.countrydto.CountryDTOForList;
import tinhnv.dto.nation.languagedto.TinyLanguageDTO;
import tinhnv.dto.nation.regiondto.RegionDTOForList;
import tinhnv.dto.nation.statisticdto.StatisticDTO;
import tinhnv.entity.nation.Language;
import tinhnv.service.INationService;
import tinhnv.transfer.Paging;

@RestController
@RequestMapping("/nation-manage")
@Tag(name = "Nation Manage", description = "Quản lý dữ liệu nation")
public class NationManageController {

	@Autowired
	INationService service;
	
	ObjectMapper mapper;
	
	public NationManageController() {
		mapper = new ObjectMapper();
	}
	
	@GetMapping("/languages/list")
	public CollectionModel<Language> listLanguages(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Paging<Language> page = service.listLanguages(pageNo, pageSize);
		ArrayList<Link> links = new ArrayList<>();
		links.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(NationManageController.class).listLanguages(pageNo, pageSize)
				).withSelfRel());
		if(!page.isFirstPage()) {
			links.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(NationManageController.class).listLanguages(pageNo - 1, pageSize)
					).withRel("prev"));
		}
		if(!page.isLastPage()) {
			links.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(NationManageController.class).listLanguages(pageNo + 1, pageSize)
					).withRel("next"));
		}
		return CollectionModel.of(page.getData(), links);
	}
	
	@GetMapping("/countries/list")
	public CollectionModel<CountryDTOForList> listCountries(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Paging<CountryDTOForList> page = service.listCountries(pageNo, pageSize);
		ArrayList<Link> links = new ArrayList<>();
		links.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(NationManageController.class).listCountries(pageNo, pageSize))
				.withSelfRel());
		if(!page.isFirstPage()) {
			links.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(NationManageController.class).listCountries(pageNo - 1, pageSize))
					.withRel("prev"));
		}
		if(!page.isLastPage()) {
			links.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(NationManageController.class).listCountries(pageNo + 1, pageSize))
					.withRel("next"));
		}
		return CollectionModel.of(page.getData(), links);
	}
	
	@GetMapping("/regions/list")
	public CollectionModel<RegionDTOForList> listRegions() {
		return CollectionModel.of(service.listRegion());
	}
	
	@GetMapping("/continents/list")
	public CollectionModel<ContinentDTOForList> listContinents() {
		return CollectionModel.of(service.listContinent());
	}
	
	@PostMapping("/languages")
	public EntityModel<Language> createLanguage(@RequestParam String language) {
		return EntityModel.of(service.createLanguage(language));
	}
	
	@PostMapping("/continents")
	public EntityModel<ContinentDTOForList> createContinent(@RequestParam String name) {
		return EntityModel.of(service.createContinent(name));
	}
	
	@PostMapping("/regions")
	public EntityModel<RegionDTOForList> createRegion(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true, description="Thông tin khu vực",
			content= {
					@Content(examples=@ExampleObject(value="name:string, continentId:number, area:number"))
			})
			@RequestBody ObjectNode data) {
			String name = data.get("name").asText();
			Integer continentId = data.get("continentId").asInt();
			BigDecimal area = new BigDecimal(data.get("area").asText("0"));
		return EntityModel.of(service.createRegion(name, continentId, area));
	}
	
	@PostMapping("/countries")
	public EntityModel<CountryDTOForList> createCountry(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true, description="Thông tin đất nước",
			content= {
					@Content(examples=@ExampleObject(value="country:{countryId:number, countryName:string, area:number, nationalDay:date, countryCode2:String, countryCode3:String}, regionId:number, languages:[{languageID:number, official:boolean]"))
			})
			@RequestBody ObjectNode data)
			throws JsonMappingException, JsonProcessingException {
		CountryDTOForCreate country = mapper.treeToValue(data.get("country"), CountryDTOForCreate.class);
		Integer regionId = data.get("regionId").asInt();
		ArrayNode languageNode = (ArrayNode) data.get("languages");
		List<TinyLanguageDTO> languages = new ArrayList<>();
		languageNode.forEach(lang -> {
			try {
				languages.add(mapper.treeToValue(lang, TinyLanguageDTO.class));
			} catch (JsonProcessingException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		});
		return EntityModel.of(service.createCountry(country, regionId, languages));
	}
	
	@PostMapping("/statistic")
	public EntityModel<StatisticDTO> createStatistic(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true, description="Thông tin thống kê",
			content= {
					@Content(examples=@ExampleObject(value="statistic: {year:number, population:number, gdp:number}, countryId:number"))
			})
			@RequestBody ObjectNode data)
			throws JsonMappingException, JsonProcessingException {
		StatisticDTO statistic = mapper.treeToValue(data.get("statistic"), StatisticDTO.class);
		Integer countryId = data.get("countryId").asInt();
		return EntityModel.of(service.createStatistic(statistic, countryId));
	}
	
	@PutMapping("/languages")
	public EntityModel<Language> updateLanguage(@RequestBody Language language) {
		return EntityModel.of(service.updateLanguage(language));
	}
	
	@PutMapping("/continents")
	public EntityModel<ContinentDTOForList> updateContinent(@RequestBody ContinentDTOForList continent) {
		return EntityModel.of(service.updateContinent(continent));
	}
	
	@PutMapping("/regions")
	public EntityModel<RegionDTOForList> updateRegion(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true, description="Thông tin khu vực",
			content= {
					@Content(examples=@ExampleObject(value="name:string, continentId:number, area:number"))
			})
			@RequestBody ObjectNode data) throws JsonProcessingException, IllegalArgumentException {
		RegionDTOForList region = mapper.treeToValue(data.get("region"), RegionDTOForList.class);
		Integer continentId = data.get("continentId").asInt();
		BigDecimal area = new BigDecimal(data.get("area").asText("0"));
		return EntityModel.of(service.updateRegion(region, continentId, area));
	}
	
	@PutMapping("/countries")
	public EntityModel<CountryDTOForList> updateCountry(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true, description="Thông tin đất nước",
			content= {
					@Content(examples=@ExampleObject(value="country:{countryId:number, countryName:string, area:number, nationalDay:date, countryCode2:String, countryCode3:String}, regionId:number, languages:[{languageID:number, official:boolean]"))
			})
			@RequestBody ObjectNode data) throws JsonProcessingException, IllegalArgumentException {
		CountryDTOForDetail c = mapper.treeToValue(data.get("country"), CountryDTOForDetail.class);
		Integer regionId = data.get("regionId").asInt(0);
		return EntityModel.of(service.updateCountry(c, regionId));
	}
	
	@PutMapping("/statistic")
	public EntityModel<StatisticDTO> updateStatistic(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true, description="Thông tin thống kê",
			content= {
					@Content(examples=@ExampleObject(value="statistic: {year:number, population:number, gdp:number}, countryId:number"))
			})
			@RequestBody ObjectNode data)
			throws JsonProcessingException, IllegalArgumentException {
		StatisticDTO s = mapper.treeToValue(data.get("statistic"), StatisticDTO.class);
		Integer countryId = data.get("countryId").asInt(0);
		return EntityModel.of(service.updateStatistic(s, countryId));
	}
	
	@DeleteMapping("/languages/{id}")
	public ResponseEntity<Integer> deleteLanguage(@PathVariable Integer id) {
		service.deleteLanguage(id);
		return ResponseEntity.ok(id);
	}
	
	@DeleteMapping("/continents/{id}")
	public ResponseEntity<Integer> deleteContinent(@PathVariable Integer id) {
		service.deleteContinent(id);
		return ResponseEntity.ok(id);
	}
	
	@DeleteMapping("/countries/{id}")
	public ResponseEntity<Integer> deleteCountry(@PathVariable Integer id) {
		service.deleteCountry(id);
		return ResponseEntity.ok(id);
	}
	
	@DeleteMapping("/statistic/{countryId}/{year}")
	public ResponseEntity<String> deleteStatistic(@PathVariable Integer countryId, @PathVariable Integer year) {
		service.deleteStatistic(countryId, year);
		return ResponseEntity.ok(countryId + " - " + year);
	}
	
	@DeleteMapping("/regions/{id}")
	public ResponseEntity<Integer> deleteRegion(@PathVariable Integer id) {
		service.deleteRegion(id);
		return ResponseEntity.ok(id);
	}
}
