package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class TypeException extends RuntimeException {
	public TypeException(String columnName, String columnType) {
		super("Type of column " + columnName + " should be " + columnType + ".");
	}
}
