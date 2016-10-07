package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class NotFoundException extends RuntimeException {
	public NotFoundException(EntityType type, String message) {
		super(type.toString() + " with name " + message +" does not found.");
	}
}
