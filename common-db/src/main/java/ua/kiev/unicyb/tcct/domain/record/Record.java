package ua.kiev.unicyb.tcct.domain.record;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.field.Field;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class Record implements Serializable {
	private static final Long serialVersionUID = -691274196371259512L;

	private Map<Column, Field> fields = new HashMap<>();

	public Record() {
	}

	public Map<Column, Field> getFields() {
		return fields;
	}

	public void setFields(Map<Column, Field> fields) {
		this.fields = fields;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Record record = (Record) o;

		return fields != null ? fields.equals(record.fields) : record.fields == null;

	}

	@Override
	public int hashCode() {
		return fields != null ? fields.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Record{" +
				"fields=" + fields +
				'}';
	}
}
