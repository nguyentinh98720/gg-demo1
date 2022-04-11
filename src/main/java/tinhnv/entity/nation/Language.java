package tinhnv.entity.nation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "languages")
public class Language {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "language_id")
	private Integer id;

	@Getter @Setter
	@Column(name = "language", nullable = false, length = 50)
	private String name;
	
	public Language(String name) {
		this.name = name;
	}
	
}
