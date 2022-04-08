package tinhnv.entity.nation.idClass;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CountryStatisticId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer country;
	private Integer year;
}
