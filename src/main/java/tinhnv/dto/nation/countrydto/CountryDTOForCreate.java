package tinhnv.dto.nation.countrydto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CountryDTOForCreate extends CountryDTOForList {

	private BigDecimal area;
	private Date nationalDay;
	private String countryCodeTwoChars;
	private String countryCodeThreeChars;
}
