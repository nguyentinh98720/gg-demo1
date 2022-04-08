package tinhnv.entity.nation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tinhnv.entity.nation.idClass.CountryLanguageId;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

}