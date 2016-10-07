package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class ExistsException extends RuntimeException {

	public ExistsException(EntityType type, String name) {
		super(type.toString() + " \'" + name + "\' already exists.");
	}

}
