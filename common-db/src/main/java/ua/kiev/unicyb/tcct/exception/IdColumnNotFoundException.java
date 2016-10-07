package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class IdColumnNotFoundException extends RuntimeException {
	public IdColumnNotFoundException(String message) {
		super("Table \'" + message + "\' should has column ID.");
	}
}
