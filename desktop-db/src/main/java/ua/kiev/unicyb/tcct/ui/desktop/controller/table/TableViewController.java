package ua.kiev.unicyb.tcct.ui.desktop.controller.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.util.converter.TableRecordsConverter;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class TableViewController extends AbstractController {
	@Autowired
	private TableService tableService;

	@FXML
	private TableView tableView;

	private Table table;

	public void fillValues() {
		List<String> columnNames = table.getColumns().stream().map(Column::getColumnName).collect(Collectors.toList());
		Integer countColumns = table.getColumns().size();
		for (int i = 0; i < columnNames.size(); i++) {
			final int finalIdx = i;
			TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnNames.get(i));
			column.prefWidthProperty().bind(tableView.widthProperty().divide(countColumns).subtract(1));
			column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
			tableView.getColumns().add(column);
		}

		for (Record record : table.getRecords()) {
			tableView.getItems()
					.add(FXCollections.observableArrayList(TableRecordsConverter.convert(record, tableView)));
		}
		tableView.setFixedCellSize(25);
		tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems()).multiply(tableView.getFixedCellSize()));
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
