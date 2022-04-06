package tinhnv.dto.nation.continentdto;

import tinhnv.entity.nation.Continent;

public class ContinentDTOForList {

	private Integer continentId;
	private String continentName;

	public ContinentDTOForList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContinentDTOForList(Integer continentId, String continentName) {
		super();
		this.continentId = continentId;
		this.continentName = continentName;
	}

	public Integer getContinentId() {
		return continentId;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentId(Integer continentId) {
		this.continentId = continentId;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public static ContinentDTOForList toDTO(Continent continent) {
		return new ContinentDTOForList(continent.getId(), continent.getName());
	}
}
