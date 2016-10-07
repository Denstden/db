package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class RecordExistsException extends RuntimeException {
	public RecordExistsException(Object recordId) {
		super("Record with ID '" + recordId.toString() + "' already exists.");
	}
}
