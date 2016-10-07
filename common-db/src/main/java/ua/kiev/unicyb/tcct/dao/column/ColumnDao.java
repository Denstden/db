package ua.kiev.unicyb.tcct.dao.column;

import ua.kiev.unicyb.tcct.domain.column.Column;

/**
 * @Author Denys Storozhenko.
 */
public interface ColumnDao {
	boolean create(Column column);
	Column read(Object id);
	boolean update(Column column);
	boolean delete(Object id);
}
