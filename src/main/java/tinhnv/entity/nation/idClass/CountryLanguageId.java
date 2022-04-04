package tinhnv.entity.nation.idClass;

import java.io.Serializable;

public class CountryLanguageId  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer country;
	private Integer language;
	
	public CountryLanguageId(Integer country, Integer language) {
		this.country = country;
		this.language = language;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public CountryLanguageId() {
		super();
	}
}
