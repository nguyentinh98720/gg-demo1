package tinhnv.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Hidden
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SuccessResponse {

	@JsonProperty("success")
	private static final boolean SUCCESS = true;
	private String status;
	private String message;
	private Object data;

}
