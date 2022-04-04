package tinhnv.dto.nation.regionDTO;

import tinhnv.entity.nation.Region;

public class RegionDTOForList {

	private Integer regionId;
	private String regionName;

	public RegionDTOForList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegionDTOForList(Integer regionId, String regionName) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public static RegionDTOForList toDTO(Region region) {
		return new RegionDTOForList(region.getId(), region.getName());
	}
}
