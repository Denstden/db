package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public enum EntityType {
	DATABASE,
	TABLE,
	COLUMN,
	FIELD,
	RECORD;

	public String toString() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}
}
