package ua.kiev.unicyb.tcct.dao;

import java.util.List;

import ua.kiev.unicyb.tcct.domain.database.Database;

/**
 * @Author Denys Storozhenko.
 */
public interface MockDao {
	List<Database> mockDb();
}
