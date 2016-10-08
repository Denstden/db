package ua.kiev.unicyb.tcct.service.table;

import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
public interface IntersectionService {
	Table intersect(String databaseName1, String databaseName2, String tableName1, String tableName2);
}
