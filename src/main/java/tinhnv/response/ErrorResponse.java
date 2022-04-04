package tinhnv.response;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public class ErrorResponse {

	private final boolean success = false;
	private String message;
	private String errorCode;
	private Object data;

	public ErrorResponse(String message, String errorCode, Object data) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	
	
}
