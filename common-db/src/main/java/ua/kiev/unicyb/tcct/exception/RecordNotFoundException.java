package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class RecordNotFoundException extends RuntimeException {
	public RecordNotFoundException(String id) {
		super("Record with id '" + id + "' not found.");
	}
}
