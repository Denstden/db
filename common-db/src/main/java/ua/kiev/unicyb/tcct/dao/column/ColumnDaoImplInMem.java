package ua.kiev.unicyb.tcct.dao.column;

import ua.kiev.unicyb.tcct.domain.column.Column;

/**
 * @Author Denys Storozhenko.
 */
public class ColumnDaoImplInMem implements ColumnDao {
	@Override
	public boolean create(Column column) {
		return false;
	}

	@Override
	public Column read(Object id) {
		return null;
	}

	@Override
	public boolean update(Column column) {
		return false;
	}

	@Override
	public boolean delete(Object id) {
		return false;
	}
}
