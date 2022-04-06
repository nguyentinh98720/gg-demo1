package tinhnv.dto.nation.languagedto;

import tinhnv.entity.nation.CountryLanguages;

public class LanguageDTO {

	private String language;
	private boolean official;

	public LanguageDTO() {
		super();
	}

	public LanguageDTO(String language, boolean official) {
		super();
		this.language = language;
		this.official = official;
	}

	public String getLanguage() {
		return language;
	}

	public boolean isOfficial() {
		return official;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}
	
	public static LanguageDTO toDTO(CountryLanguages country_languages) {
		return new LanguageDTO(country_languages.getLanguage().getLanguage(), country_languages.isOfficial());
	}

}
