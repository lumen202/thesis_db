package dev.lumen.app;

import dev.lumen.App;
import dev.lumen.data.DegreeDAO;
import dev.lumen.models.Degree;
import dev.lumen.models.Thesis;
import dev.sol.core.application.FXController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private ObservableList<Thesis> thesisMasterList;
    private Scene scene;

    public void handleUpdate() {
    }

    @FXML
    public void handleDeleteDegree() {
        Degree selectedDegree = degreeTable.getSelectionModel().getSelectedItem();
        if (selectedDegree == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Degree Delete Error");
            alert.setHeaderText("Null selection Error!");
            alert.setContentText("No selected Degree form the table");
            alert.initOwner(scene.getWindow());
            alert.show();
            return;
        }
        if (thesisMasterList.stream().anyMatch(thesis -> thesis.getDegID() == selectedDegree.getDegreeID())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Degree Delete Error");
            alert.setHeaderText("Selection Conflict");
            alert.setContentText("Degree in use!!! ");
            alert.initOwner(scene.getWindow());
            alert.show();
            return;

        } else {
            degreeMasterList.remove(selectedDegree);
            DegreeDAO.delete(selectedDegree);
        }

    }

    @Override
    protected void load_bindings() {

        degreeMasterList = App.COLLECTIONS_REGISTRY.getList("DEGREE");
        thesisMasterList = App.COLLECTIONS_REGISTRY.getList("THESIS");
        scene = (Scene) getParameter("scene");

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
