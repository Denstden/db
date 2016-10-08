package ua.kiev.unicyb.tcct.dao.record;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;
import ua.kiev.unicyb.tcct.exception.NotFoundException;
import ua.kiev.unicyb.tcct.util.ColumnFactory;

import static org.junit.Assert.assertEquals;

/**
 * @Author Denys Storozhenko.
 */
public class RecordDaoImplInMemTest {
	private RecordDaoImplInMem recordDao = new RecordDaoImplInMem();

	private ColumnFactory columnFactory = new ColumnFactory();

	@Test(expected = IdColumnNotFoundException.class)
	public void createEmptyShouldRiseIdNotFoundException() {
		recordDao.create(new HashMap<>());
	}

	@Test(expected = IdColumnNotFoundException.class)
	public void createWithoutIdColumnShouldRiseIdNotFoundException() {
		Map<Column, Field> map = new HashMap<>();
		map.put(columnFactory.create("Name", SupportedType.STRING), new Field("Ivan"));

		recordDao.create(map);
	}

	@Test
	public void shouldCreate() {
		Map<Column, Field> map = new HashMap<>();
		map.put(columnFactory.create("Name", SupportedType.STRING), new Field("Ivan"));
		map.put(columnFactory.createId("ID", SupportedType.LONG), new Field(1L));
		assertEquals(0, recordDao.getRecords().size());

		recordDao.create(map);

		assertEquals(1, recordDao.getRecords().size());
	}

	@Test(expected = NotFoundException.class)
	public void readColumnNameNotExistsShouldRiseNotFoundException() {
		Record record = createRecord();
		recordDao.getRecords().add(record);

		recordDao.read((ID) (columnFactory.createId("ID1", SupportedType.LONG)), 1L);
	}

	@Test(expected = NotFoundException.class)
	public void readFieldValueNotExistsShouldRiseNotFoundException() {
		Record record = createRecord();
		recordDao.getRecords().add(record);

		recordDao.read((ID) (columnFactory.createId("ID", SupportedType.LONG)), 2L);
	}

	@Test
	public void shouldRead() {
		Record record = createRecord();
		recordDao.getRecords().add(record);

		Record record1 = recordDao.read((ID) (columnFactory.createId("ID", SupportedType.LONG)), 1L);

		assertEquals(record, record1);
	}

	private Record createRecord() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(columnFactory.createId("ID", SupportedType.LONG), new Field(1L));
		map.put(columnFactory.create("Name", SupportedType.STRING), new Field("Ivan"));
		record.setFields(map);
		return record;
	}
}
