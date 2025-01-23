package dev.lumen.app;

import dev.lumen.App;
import dev.lumen.models.Degree;
import dev.sol.core.application.FXController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DegreeViewController extends FXController {

    @FXML
    private TableView<Degree> degreeTable;
    @FXML
    private TableColumn<Degree, Integer> degreeIdColumn;
    @FXML
    private TableColumn<Degree, String> degreeColumn;

    private ObservableList<Degree> degreeMasterList;

    public void handleUpdate() {
            
    }

    @Override
    protected void load_bindings() {

        degreeMasterList = App.COLLECTIONS_REGISTRY.getList("DEGREE");

        degreeIdColumn.setCellValueFactory(cell -> cell.getValue().degreeIDProperty().asObject());
        degreeColumn.setCellValueFactory(cell -> cell.getValue().degreeProperty());
        degreeTable.setItems(degreeMasterList);
    }

    @Override
    protected void load_fields() {

    }

    @Override
    protected void load_listeners() {

    }

}
