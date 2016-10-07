package ua.kiev.unicyb.tcct.web.converter;

import java.util.List;
import java.util.stream.Collectors;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.web.dto.DatabaseDto;

/**
 * @Author Denys Storozhenko.
 */
public class DatabaseDtoConverter extends AbstractConverter<DatabaseDto, Database> {
	private TableDtoConverter converter = new TableDtoConverter();

	@Override
	public DatabaseDto toDto(Database entity) {
		DatabaseDto databaseDto = new DatabaseDto();
		databaseDto.setDatabaseName(entity.getDatabaseName());
		if (entity.getTables() != null) {
			databaseDto.setTables(
					entity.getTables().stream().map(table -> converter.toDto(table)).collect(Collectors.toList()));
		}
		return databaseDto;
	}

	@Override
	public Database toEntity(DatabaseDto dto) {
		Database database = new Database();
		database.setDatabaseName(dto.getDatabaseName());
		if (dto.getTables() != null) {
			List<Table> tables =
					dto.getTables().stream().map(tableDto -> converter.toEntity(tableDto)).collect(Collectors.toList());
			database.setTables(tables);
		}
		return database;
	}
}
