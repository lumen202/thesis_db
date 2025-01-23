package dev.lumen.app;

import dev.lumen.App;
import dev.lumen.data.ResearcherDAO;
import dev.lumen.data.ThesisDAO;
import dev.lumen.enums.Month;
import dev.lumen.models.Degree;
import dev.lumen.models.Researcher;
import dev.lumen.models.Student;
import dev.lumen.models.Thesis;

import dev.sol.core.application.FXController;
import dev.sol.core.application.loader.FXLoaderFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController extends FXController {

    // Thesis Table
    @FXML
    private TableView<Thesis> thesisTable;
    @FXML
    private TableColumn<Thesis, Integer> thesisIDColumn;
    @FXML
    private TableColumn<Thesis, String> thesisTitleColumn;
    @FXML
    private TableColumn<Thesis, Integer> thesisYearColumn;
    @FXML
    private TextField searchfield;

    // Author Table
    @FXML
    private TableView<Researcher> authorTable;
    @FXML
    private TableColumn<Researcher, Integer> researchIDColumn;
    @FXML
    private TableColumn<Researcher, Student> researcherNameColumn;
    @FXML
    private TableColumn<Researcher, String> roleColumn;
    @FXML
    private ComboBox<String> researcherBox;
    @FXML
    private ComboBox<String> roleBox;
    @FXML
    private Button deleteAuthorButton;
    @FXML
    private Button updateAuthorButton;

    // Fields
    @FXML
    private TextField idfield;
    @FXML
    private TextArea thesisTitleField;
    @FXML
    private ComboBox<String> degreeField;
    @FXML
    private ComboBox<Month> monthSubmittedField;
    @FXML
    private ComboBox<Integer> yearSubmittedField;

    private ObservableList<Thesis> thesisMasterList;
    private ObservableList<Researcher> authorMasterList;
    private ObservableList<Degree> degreeMasterList;
    private ObservableList<Student> studentMasterList;
    private FilteredList<Researcher> authorFilteredList;
    private FilteredList<Thesis> thesisFilteredList;
    private ObservableList<String> degreeList;
    private ObservableList<Month> months;
    private ObservableList<Integer> years;
    private ObservableList<String> researcherList;
    private ObservableList<String> roleList;

    private Scene scene;

    @FXML
    private void handleAuthorManagement() {
        AuthorViewLoader loader = (AuthorViewLoader) FXLoaderFactory
                .createInstance(AuthorViewLoader.class,
                        getClass().getResource("/dev/lumen/app/AUTHORVIEW.fxml"))
                .addParameter("OWNER_STAGE", params.get("OWNER"))
                .initialize();
        loader.load();
    }

    @FXML
    private void handelDegreeManagement() {
        DegreeViewLoader loader = (DegreeViewLoader) FXLoaderFactory
                .createInstance(DegreeViewLoader.class,
                        getClass().getResource("/dev/lumen/app/DEGREEVIEW.fxml"))
                .addParameter("OWNER_STAGE", params.get("OWNER"))
                .initialize();
        loader.load();
    }

    @FXML
    private void HandleAuthorSaveButton() {
        // TODO : make it so whe i press the add button the researcher show up in the
        // table view

        authorFilteredList = new FilteredList<>(authorMasterList, p -> true);
        Thesis selectedThesis = thesisTable.getSelectionModel().getSelectedItem();
        int selectedStudentID = researcherBox.getSelectionModel().getSelectedIndex();
        String selectedRole = roleBox.getSelectionModel().getSelectedItem();
        Student student = studentMasterList.get(selectedStudentID);
        Researcher researcher = new Researcher(student, selectedThesis, selectedRole);

        authorMasterList.add(researcher);
        ResearcherDAO.insert(researcher);
    }

    @FXML
    private void HandleAuthorDeleteButton() {
        // delete resarcher based on researcher id
        Researcher researcher = authorTable.getSelectionModel().getSelectedItem();
        authorMasterList.remove(researcher);
        ResearcherDAO.delete(researcher);
    }

    // Delete Function
    @FXML
    private void handleDeteThesis() {
        Thesis selectedThesis = thesisTable.getSelectionModel().getSelectedItem();
        if (selectedThesis == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thesis Delete Error");
            alert.setHeaderText("Null selection Error!");
            alert.setContentText("No selected Thesis form the table");
            alert.initOwner(scene.getWindow());
            alert.show();
            return;
        }

        thesisMasterList.remove(selectedThesis);
        ThesisDAO.delete(selectedThesis);
    }

    // Add Function
    @FXML
    private void handleAdd() {

        Thesis selectedThesis = thesisTable.getSelectionModel().getSelectedItem();
        Thesis newThesis = new Thesis(selectedThesis.getThesisID() + 1, "------> Click Here To Add New Thesis <------",
                2000,
                1, 1);

        selectedThesis.setThesisTitle(thesisTitleField.getText());
        selectedThesis.setDegID(degreeField.getSelectionModel().getSelectedIndex() + 1);
        selectedThesis.setMonth(monthSubmittedField.getSelectionModel().getSelectedIndex() + 1);
        selectedThesis.setYear(years.get(yearSubmittedField.getSelectionModel().getSelectedIndex()));

        thesisMasterList.add(newThesis);
        ThesisDAO.update(selectedThesis);
        ThesisDAO.insert(newThesis);

    }

    @FXML
    private void handlEdit() {

        Thesis selectedThesis = thesisTable.getSelectionModel().getSelectedItem();
        resetValue();
        deleteAuthorButton.setVisible(true);
        updateAuthorButton.setVisible(true);
        degreeList.clear();
        researcherList.clear();
        roleList.clear();
        listContent();

        // Combobox Items
        yearSubmittedField.setItems(years);
        degreeField.setItems(degreeList);
        monthSubmittedField.setItems(months);
        researcherBox.setItems(researcherList);
        researcherBox.setValue(researcherList.get(0));
        roleBox.setItems(roleList);
        roleBox.setValue(roleList.get(0));

        if (thesisTable.getSelectionModel().getSelectedIndex() == thesisMasterList.size() - 1) {
            // default values for click to add
            yearSubmittedField.setValue(years.get(0));
            degreeField.setValue(degreeList.get(0));
            monthSubmittedField.setValue(months.get(0));
        } else {
            // For editing exisiting thesis
            yearSubmittedField.setValue(selectedThesis.getYear());
            degreeField.setValue(degreeList.get(selectedThesis.getDegID() - 1));
            monthSubmittedField.setValue(months.get(selectedThesis.getMonth() - 1));
        }

    }

    @Override
    protected void load_bindings() {

        thesisMasterList = App.COLLECTIONS_REGISTRY.getList("THESIS");
        authorMasterList = App.COLLECTIONS_REGISTRY.getList("RESEARCHER");
        degreeMasterList = App.COLLECTIONS_REGISTRY.getList("DEGREE");
        studentMasterList = App.COLLECTIONS_REGISTRY.getList("STUDENT");

        authorFilteredList = new FilteredList<>(authorMasterList);
        thesisFilteredList = new FilteredList<>(thesisMasterList);
        degreeList = FXCollections.observableArrayList();
        months = FXCollections.observableArrayList();
        years = FXCollections.observableArrayList();
        researcherList = FXCollections.observableArrayList();
        roleList = FXCollections.observableArrayList();

        scene = (Scene) getParameter("SCENE");

        // Thesis Table
        thesisIDColumn.setCellValueFactory(cell -> cell.getValue().thesisIDProperty().asObject());
        thesisTitleColumn.setCellValueFactory(cell -> cell.getValue().thesisTitleProperty());
        thesisYearColumn.setCellValueFactory(cell -> cell.getValue().yeaProperty().asObject());

        thesisTable.setItems(thesisFilteredList);

        // Thesis Selected Item
        thesisTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

            if (thesisTable.getSelectionModel().getSelectedIndex() == thesisMasterList.size() - 1) {
                handlEdit();

            }

            if (nv != null && thesisTable.getSelectionModel().getSelectedIndex() < thesisMasterList.size() - 1) {

                yearSubmittedField.setItems(null);
                monthSubmittedField.setItems(null);
                degreeField.setItems(null);

                // AuthorTable
                researchIDColumn
                        .setCellValueFactory(cell -> cell.getValue().getThesisID().thesisIDProperty().asObject());
                roleColumn.setCellValueFactory(cell -> cell.getValue().rolProperty());
                researcherNameColumn.setCellValueFactory(cell -> cell.getValue().studentIDProperty());
                authorFilteredList.setPredicate(author -> author.getThesisID().getThesisID() == nv.getThesisID());
                authorTable.setItems(authorFilteredList);

                // Author FIelds
                researcherBox.setValue(null);
                roleBox.setValue(null);

                // Thesis Fields
                degreeField.setValue(degreeMasterList.get(nv.getDegID() - 1).getDegree());
                idfield.setText(String.valueOf(nv.getThesisID()));
                thesisTitleField.setText(nv.getThesisTitle());
                yearSubmittedField.setValue(nv.getYear());
                monthSubmittedField.setValue(Month.fromNumber(nv.getMonth()));

            } else {

                authorFilteredList.setPredicate(author -> author.getThesisID().getThesisID() == 0);
                authorTable.setItems(authorFilteredList);
            }

        });

    }

    @Override
    protected void load_fields() {

        researcherNameColumn.setCellFactory(cell -> {
            TableCell<Researcher, Student> tableCell = new TableCell<>() {
                @Override
                protected void updateItem(Student item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                        return;
                    }

                    setGraphic(new Label(item.fullname()));
                }

            };
            return tableCell;
        });

        // Author selected Items
        authorTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

            if (authorTable.getSelectionModel().isEmpty()) {
                deleteAuthorButton.setVisible(false);
                updateAuthorButton.setVisible(false);
                researcherBox.setValue(null);
                roleBox.setValue(null);
            } else {
                deleteAuthorButton.setVisible(true);
                updateAuthorButton.setVisible(true);
                roleBox.setValue(nv.getRole());
                researcherBox.setValue(nv.getStudentID().getFullname());

            }

        });

    }

    @Override
    protected void load_listeners() {

        deleteAuthorButton.setVisible(false);
        updateAuthorButton.setVisible(false);

        searchfield.textProperty().addListener((o, ov, nv) -> {
            if (nv == null) {
                thesisFilteredList.setPredicate(p -> true);
            } else {
                thesisFilteredList.setPredicate(thesis -> {

                    String filter = searchfield.getText().toUpperCase();

                    if (Integer.toString(thesis.getThesisID()).contains(filter))
                        return true;

                    if (thesis.getThesisTitle().toUpperCase().contains(filter))
                        return true;

                    return Integer.toString(thesis.getYear()).contains(filter);

                });
            }
        });
    }

    public void resetValue() {
        thesisTitleField.setPromptText(null);
        degreeField.setValue(null);
        yearSubmittedField.setValue(null);
        monthSubmittedField.setValue(null);
    }

    public void listContent() {
        // Year
        for (int i = 0; i < 50; i++) {
            years.add(2000 + i);
        }
        // Degree
        for (int i = 0; i < degreeMasterList.size(); i++) {
            degreeList.add(degreeMasterList.get(i).getDegree());
        }
        // Months
        for (int i = 1; i <= 12; i++) {
            months.add(Month.fromNumber(i));
        }

        // Researcher
        for (int i = 0; i <= studentMasterList.size() - 1; i++) {
            researcherList.add(studentMasterList.get(i).getFullname());
        }

        // Role
        for (int i = 0; i <= 1; i++) {
            roleList.add(authorMasterList.get(i).getRole());
        }

    }

}
