package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class EmptyRecordException extends RuntimeException {
	public EmptyRecordException() {
		super("Record is empty.");
	}
}
