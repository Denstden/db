package ua.kiev.unicyb.tcct.web.converter;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.web.dto.ColumnDto;

/**
 * @Author Denys Storozhenko.
 */
public class ColumnDtoConverter extends AbstractConverter<ColumnDto, Column> {
	@Override
	public ColumnDto toDto(Column entity) {
		ColumnDto columnDto = new ColumnDto();
		columnDto.setNullable(entity.isNullable());
		columnDto.setColumnName(entity.getColumnName());
		String type = entity.getType().name();
		columnDto.setType(Character.toUpperCase(type.charAt(0)) + type.substring(1).toLowerCase());
		if (entity.getDefaultValue() != null) {
			columnDto.setDefaultValue(entity.getDefaultValue().toString());
		}
		return columnDto;
	}

	@Override
	public Column toEntity(ColumnDto dto) {
		Column column;
		if (dto.getId()) {
			column = new ID(SupportedType.valueOf(dto.getType().toUpperCase()));
		} else {
			column = new Column(SupportedType.valueOf(dto.getType().toUpperCase()));
		}
		column.setColumnName(dto.getColumnName());
		if (dto.getDefaultValue() != null) {
			setDefaultValue(column, dto);
		}
		column.setNullable(dto.getNullable());
		return column;
	}

	private void setDefaultValue(Column column, ColumnDto dto) {
		if (dto.getType().equalsIgnoreCase("Long")) {
			column.setDefaultValue(Long.valueOf(dto.getDefaultValue()));
		} else if (dto.getType().equalsIgnoreCase("Integer")) {
			column.setDefaultValue(Integer.valueOf(dto.getDefaultValue()));
		} else if (dto.getType().equalsIgnoreCase("Double")) {
			column.setDefaultValue(Double.valueOf(dto.getDefaultValue()));
		} else {
			column.setDefaultValue(dto.getDefaultValue());
		}
	}
}
