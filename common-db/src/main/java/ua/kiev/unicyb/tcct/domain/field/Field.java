package ua.kiev.unicyb.tcct.domain.field;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class Field implements Serializable {
	private static final Long serialVersionUID = -681723869123631613L;

	private Object value;

	public Field() {
	}

	public Field(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Field field = (Field) o;

		if (value != null ? !value.equals(field.value) : field.value != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Field{" +
				"value=" + value +
				'}';
	}
}
