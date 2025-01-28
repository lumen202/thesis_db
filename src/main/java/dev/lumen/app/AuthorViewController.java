package dev.lumen.app;

import dev.lumen.App;
import dev.lumen.data.StudentDAO;
import dev.lumen.models.Researcher;
import dev.lumen.models.Student;
import dev.sol.core.application.FXController;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    private TextField authorManagementSearchField;

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
    private FilteredList<Student> authorFilteredList;

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
        authorMasterList.remove(selectedStudent);
        StudentDAO.delete(selectedStudent);

    }

    @Override
    protected void load_bindings() {
        authorMasterList = App.COLLECTIONS_REGISTRY.getList("STUDENT");
        researcherMasterlist = App.COLLECTIONS_REGISTRY.getList("RESEARCHER");
        authorFilteredList = new FilteredList<>(authorMasterList);

        authorIDColumn.setCellValueFactory(cell -> cell.getValue().studentIDProperty().asObject());
        authorNameColumn.setCellValueFactory(cell -> cell.getValue().fullnameProperty());
        authorTableView.setItems(authorFilteredList);
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

        authorManagementSearchField.textProperty().addListener((o, ov, nv) -> {
            if (nv == null) {
                authorFilteredList.setPredicate(p -> true);
            } else {
                authorFilteredList.setPredicate(author -> {

                    String filter = authorManagementSearchField.getText().toUpperCase();

                    if (Integer.toString(author.getStudentID()).contains(filter))
                        return true;

                    if (author.getFirstname().toUpperCase().contains(filter)
                            || author.getMiddleInitial().toUpperCase().contains(filter)
                            || author.getSurname().toUpperCase().contains(filter))
                        return true;
                    return false;

                });
            }
        });
    }

}
