package tinhnv.response;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public class SuccessResponse {

	private static final boolean success = true;
	private String status;
	private String message;
	private Object data;

	public boolean isSuccess() {
		return success;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public SuccessResponse(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public SuccessResponse() {
		super();
	}
}
