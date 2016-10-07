package ua.kiev.unicyb.tcct.web.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractConverter<T, E> {
	public abstract T toDto(E entity);

	public Iterable<T> toDtos(Iterable<E> entities) {
		List<T> result = new ArrayList<>();
		for (E e : entities) {
			result.add(toDto(e));
		}
		return result;
	}

	public abstract E toEntity(T dto);
}
