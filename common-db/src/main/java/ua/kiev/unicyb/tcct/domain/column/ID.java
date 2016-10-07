package ua.kiev.unicyb.tcct.domain.column;

import org.springframework.stereotype.Component;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class ID extends Column {
	private static final long serialVersionUID = 1268937105387132L;

	public ID() {
	}

	public ID(String type) {
		super(type);
		setNullable(false);
		setDefaultValue(null);
	}
}
