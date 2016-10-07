package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class NullableColumnException extends RuntimeException {
	public NullableColumnException(String columnName) {
		super("Value of column '" + columnName + "' can not be null.");
	}
}
