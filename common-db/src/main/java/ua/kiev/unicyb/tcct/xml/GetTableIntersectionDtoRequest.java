package ua.kiev.unicyb.tcct.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author Denys Storozhenko.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"db1Name", "table1Name", "db2Name", "table2Name"})
@XmlRootElement(name = "getTableIntersectionDtoRequest")
public class GetTableIntersectionDtoRequest {
	@XmlElement(required = true)
	private String db1Name;

	@XmlElement(required = true)
	private String table1Name;

	@XmlElement(required = true)
	private String db2Name;

	@XmlElement(required = true)
	private String table2Name;

	public String getDb1Name() {
		return db1Name;
	}

	public void setDb1Name(String db1Name) {
		this.db1Name = db1Name;
	}

	public String getTable1Name() {
		return table1Name;
	}

	public void setTable1Name(String table1Name) {
		this.table1Name = table1Name;
	}

	public String getDb2Name() {
		return db2Name;
	}

	public void setDb2Name(String db2Name) {
		this.db2Name = db2Name;
	}

	public String getTable2Name() {
		return table2Name;
	}

	public void setTable2Name(String table2Name) {
		this.table2Name = table2Name;
	}
}
