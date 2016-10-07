package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class IdColumnExistsException extends RuntimeException {
	public IdColumnExistsException(String message) {
		super("Id column in the table '" + message + "' already exists.");
	}
}
