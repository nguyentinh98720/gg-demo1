package tinhnv.entity.nation.idclass;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CountryLanguageId  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Getter @Setter
	private Integer country;
	@Getter @Setter
	private Integer language;
}
