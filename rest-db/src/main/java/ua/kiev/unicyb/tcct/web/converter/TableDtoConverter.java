package ua.kiev.unicyb.tcct.web.converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.web.dto.TableDto;

/**
 * @Author Denys Storozhenko.
 */
public class TableDtoConverter extends AbstractConverter<TableDto, Table> {
	private ColumnDtoConverter columnDtoConverter = new ColumnDtoConverter();

	private RecordDtoConverter recordDtoConverter = new RecordDtoConverter();

	@Override
	public TableDto toDto(Table entity) {
		TableDto tableDto = new TableDto();
		tableDto.setTableName(entity.getTableName());
		if (entity.getColumns() != null) {
			tableDto.setColumns(entity.getColumns().stream().map(column -> columnDtoConverter.toDto(column))
					.collect(Collectors.toList()));
		}
		if (entity.getRecords() != null) {
			tableDto.setRecords(entity.getRecords().stream().map(record -> recordDtoConverter.toDto(record))
					.collect(Collectors.toList()));
		}
		return tableDto;
	}

	@Override
	public Table toEntity(TableDto dto) {
		Table table = new Table();
		table.setTableName(dto.getTableName());
		if (dto.getColumns() != null) {
			Set<Column> columns = dto.getColumns().stream().map(columnDto -> columnDtoConverter.toEntity(columnDto))
					.collect(Collectors.toSet());
			table.setColumns(columns);
		}
		if (dto.getRecords() != null) {
			List<Record> records = dto.getRecords().stream().map(recordDto -> recordDtoConverter.toEntity(recordDto))
					.collect(Collectors.toList());
			table.setRecords(records);
		}
		return table;
	}
}
