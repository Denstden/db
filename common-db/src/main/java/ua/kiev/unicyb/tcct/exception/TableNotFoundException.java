package ua.kiev.unicyb.tcct.exception;

/**
 * @Author Denys Storozhenko.
 */
public class TableNotFoundException extends RuntimeException {
	public TableNotFoundException(String tableName, String databaseName) {
		super("Table with name '" + tableName + "' in the database '" + databaseName + "' not found.");
	}
}
