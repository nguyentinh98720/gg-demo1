package tinhnv.entity.nation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tinhnv.entity.nation.idClass.CountryLanguageId;

@Entity
@Table(name="country_languages")
@IdClass(CountryLanguageId.class)
public class CountryLanguages {

	@Id
	@ManyToOne
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	
	@Id
	@ManyToOne
	@JoinColumn(name="language_id", nullable=false)
	private Language language;
	
	@Column(name="official", nullable=false)
	private boolean official;

	public CountryLanguages(Country country, Language language, boolean official) {
		super();
		this.country = country;
		this.language = language;
		this.official = official;
	}

	public CountryLanguages() {
		super();
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public boolean isOfficial() {
		return official;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
