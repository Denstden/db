package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class UnknownColumnException extends RuntimeException {
	public UnknownColumnException(String columnName) {
		super("Column '" + columnName + "' is absent in the table.");
	}
}
