package tinhnv.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import tinhnv.dto.nation.continentDTO.ContinentDTOForDetail;
import tinhnv.dto.nation.continentDTO.ContinentDTOForList;
import tinhnv.dto.nation.countryDTO.CountryDTOForDetail;
import tinhnv.dto.nation.countryDTO.CountryDTOForList;
import tinhnv.dto.nation.regionDTO.RegionDTOForDetail;
import tinhnv.dto.nation.regionDTO.RegionDTOForList;
import tinhnv.response.SuccessResponse;
import tinhnv.service.INationService;

@RestController
@RequestMapping("/api/nation")
@Tag(name="Nation", description="Truy vấn thông tin các quốc gia")
public class NationController {
	
	@Autowired
	INationService service;

	@GetMapping("/continents")
	@Operation(description="Lấy danh sách các lục địa")
	public SuccessResponse allContinents() {
		List<EntityModel<ContinentDTOForList>> models = service.allContinent()
				.stream()
				.map(continent -> 
					EntityModel.of(
							continent,
							linkTo(methodOn(NationController.class).oneContinent(continent.getContinentId())).withSelfRel()
							)
				).collect(Collectors.toList());
		return new SuccessResponse("","",
				CollectionModel.of(
						models,
						links(null)
						)
				);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN','VIP_USER')")
	@GetMapping("/continents/{id}")
	@Operation(description="Lấy thông tin chi tiết của một lục địa")
	public SuccessResponse oneContinent(
			@Parameter(required=true, description="Mã id của lục địa cần xem thông tin chi tiết") @PathVariable Integer id) {
		ContinentDTOForDetail continent = service.detailContinent(id);
		Link selfLink = linkTo(methodOn(NationController.class).oneContinent(continent.getContinentId())).withSelfRel();
		return new SuccessResponse("","", 
				EntityModel.of(
						continent,
						links(selfLink)
					)
				);
	}
	
	@GetMapping("/regions")
	@Operation(description="Danh sách khu vực")
	public SuccessResponse allRegions() {
		List<EntityModel<RegionDTOForList>> models = service.allRegions()
				.stream()
				.map(region ->
					EntityModel.of(
							region,
							linkTo(methodOn(NationController.class).oneRegion(region.getRegionId())).withSelfRel()
							)
				).collect(Collectors.toList());
		return new SuccessResponse("","",
				CollectionModel.of(
						models,
						links(null)
						)
				);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN','VIP_USER')")
	@GetMapping("/regions/{id}")
	@Operation(description="Thông tin cụ thể và thống kê của một khu vực")
	public SuccessResponse oneRegion(
			@Parameter(required=true, description="Mã id của khu vực cần tìm hiểu") @PathVariable Integer id) {
		RegionDTOForDetail region = service.detailRegion(id);
		Link selfLink = linkTo(methodOn(NationController.class).oneRegion(region.getRegionId())).withSelfRel();
		return new SuccessResponse("", "", 
				EntityModel.of(
						region,
						links(selfLink)
						)
				);
	}
	
	@GetMapping("/countries")
	@Operation(description="Danh sách các quốc gia")
	public SuccessResponse allCountries() {
		List<EntityModel<CountryDTOForList>> models = service.allCountries()
				.stream()
				.map(country ->
					EntityModel.of(
							country,
							linkTo(methodOn(NationController.class).oneCountry(country.getCountryId())).withSelfRel()
							)
				).collect(Collectors.toList());
		return new SuccessResponse("", "",
				CollectionModel.of(
						models,
						links(null)
						)
				);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN','VIP_USER')")
	@GetMapping("/countries/{id}")
	@Operation(description="Thông tin cụ thể cho một quốc gia")
	public SuccessResponse oneCountry(
			@Parameter(required=true, description="Mã id của quốc gia cần tìm hiểu") @PathVariable Integer id) {
		CountryDTOForDetail country = service.detailCountry(id);
		Link selfLink = linkTo(methodOn(NationController.class).oneCountry(country.getCountryId())).withSelfRel();
		return new SuccessResponse("", "", 
				EntityModel.of(
						country,
						links(selfLink)
						)
				);
	}
	
	public static List<Link> links(Link link) {
		List<Link> list = new ArrayList<>();
		if(link != null) list.add(link);
		list.add(linkTo(methodOn(NationController.class).allContinents()).withRel("continents"));
		list.add(linkTo(methodOn(NationController.class).allRegions()).withRel("regions"));
		list.add(linkTo(methodOn(NationController.class).allCountries()).withRel("countries"));
		return list;
	}
	
}
