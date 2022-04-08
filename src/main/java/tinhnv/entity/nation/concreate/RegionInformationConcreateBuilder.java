package tinhnv.entity.nation.concreate;

import java.util.List;

import tinhnv.entity.nation.Continent;
import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.Region;
import tinhnv.entity.nation.builder.RegionInformationBuilder;

public class RegionInformationConcreateBuilder implements RegionInformationBuilder {
	
	private String name;
	private Continent continent;
	private List<Country> countries;

	@Override
	public RegionInformationBuilder setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public RegionInformationBuilder setContinent(Continent continent) {
		this.continent = continent;
		return this;
	}

	@Override
	public RegionInformationBuilder setCountries(List<Country> countries) {
		this.countries = countries;
		return this;
	}

	@Override
	public Region buildEntity() {
		Region entity = new Region();
		entity.setName(name);
		entity.setContinent(continent);
		entity.setCountries(countries);
		return entity;
	}

}
