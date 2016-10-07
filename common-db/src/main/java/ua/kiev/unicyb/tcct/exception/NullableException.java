package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class NullableException extends RuntimeException {
	public NullableException(String message) {
		super("Record value in the column " + message + " should be not null.");
	}
}
