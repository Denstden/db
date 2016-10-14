package ua.kiev.unicyb.tcct.domain.column;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author Denys Storozhenko.
 */
@Component
@XmlRootElement(name = "id")
public class ID extends Column {
	private static final long serialVersionUID = 1268937105387132L;

	public ID() {
	}

	public ID(SupportedType type) {
		super(type);
		setNullable(false);
		setDefaultValue(null);
	}
}
