package tinhnv.response;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Hidden
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MyResponse<T> {

	private boolean success;
	private String message;
	private T data;
	
}
