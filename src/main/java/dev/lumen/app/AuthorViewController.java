package dev.lumen.app;

import java.sql.SQLException;

import dev.lumen.App;
import dev.lumen.data.StudentDAO;
import dev.lumen.models.Researcher;
import dev.lumen.models.Student;
import dev.sol.core.application.FXController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AuthorViewController extends FXController {

    @FXML
    private TableView<Student> authorTableView;
    @FXML
    private TableColumn<Student, Integer> authorIDColumn;
    @FXML
    private TableColumn<Student, String> authorNameColumn;

    @FXML
    private TextField authorIDField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField middileInitialField;

    private ObservableList<Student> authorMasterList;
    private ObservableList<Researcher> researcherMasterlist;
    private Scene scene;

    @FXML
    public void hanldeUpdate() {
        Student selectedStudent = authorTableView.getSelectionModel().getSelectedItem();
        selectedStudent.setFirstname(firstNameField.getText());
        selectedStudent.setMiddleInitial(middileInitialField.getText());
        selectedStudent.setSurname(lastNameField.getText());
        StudentDAO.update(selectedStudent);
    }

    @FXML
    public void handleDelete() {
        Student selectedStudent = authorTableView.getSelectionModel().getSelectedItem();
        // Student studentID = studentList.stream()
        // .filter(student -> {
        // try {
        // return student.getStudentID() == (crs.getInt("RID"));
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // return false;
        // }).findFirst().get();

        if (researcherMasterlist.stream().anyMatch(e -> {
            return e.getStudentID().getStudentID()==(selectedStudent.getStudentID());
        })) {
            if (selectedStudent == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thesis Delete Error");
                alert.setHeaderText("Null selection Error!");
                alert.setContentText("No selected Thesis form the table");
                alert.initOwner(scene.getWindow());
                alert.show();
                return;
            }

        }
        authorMasterList.remove(selectedStudent);
        StudentDAO.delete(selectedStudent);

    }

    @Override
    protected void load_bindings() {
        authorMasterList = App.COLLECTIONS_REGISTRY.getList("STUDENT");
        researcherMasterlist = App.COLLECTIONS_REGISTRY.getList("RESEARCHER");
        scene = (Scene) getParameter("SCENE");
    
        authorIDColumn.setCellValueFactory(cell -> cell.getValue().studentIDProperty().asObject());
        authorNameColumn.setCellValueFactory(cell -> cell.getValue().fullnameProperty());
        authorTableView.setItems(authorMasterList);
        authorTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            authorIDField.setText(String.valueOf(nv.getStudentID()));
            firstNameField.setText(nv.getFirstname());
            lastNameField.setText(nv.getSurname());
            middileInitialField.setText(nv.getMiddleInitial());
        });

    }

    @Override
    protected void load_fields() {

    }

    @Override
    protected void load_listeners() {

    }

}
