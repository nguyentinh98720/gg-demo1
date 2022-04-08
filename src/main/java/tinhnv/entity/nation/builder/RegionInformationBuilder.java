package tinhnv.entity.nation.builder;

import java.util.List;

import tinhnv.entity.nation.Continent;
import tinhnv.entity.nation.Country;
import tinhnv.entity.nation.Region;

public interface RegionInformationBuilder {

	RegionInformationBuilder setName(String name);
	RegionInformationBuilder setContinent(Continent continent);
	RegionInformationBuilder setCountries(List<Country> countries);
	
	Region buildEntity();
}
