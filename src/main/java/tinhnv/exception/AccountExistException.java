package tinhnv.exception;

public class AccountExistException extends RuntimeException {

	private static final long serialVersionUID = -2375100269194320928L;

	public AccountExistException() {
	}

	public AccountExistException(String message) {
		super(message);
	}
}
