package dev.lumen.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXObjectProperty;
import dev.sol.core.properties.beans.FXStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;

public class Researcher extends FXModel {

    public static class STUDENT_TABLECELL extends TableCell<Researcher, Student> {
        @Override
        protected void updateItem(Student item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
                return;
            }
            setGraphic(new Label(String.valueOf(item.getStudentID())));
        }

    }

    public static class THESIS_TABLECELL extends TableCell<Researcher, Thesis> {
        @Override
        protected void updateItem(Thesis item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
                return;
            }
            setGraphic(new Label(String.valueOf(item.getThesisID())));
        }

    }

    private FXObjectProperty<Student> studentID;
    private FXObjectProperty<Thesis> thesisID;
    private FXStringProperty role;

    // Constructor

    public Researcher(Student studentID, Thesis thesisID, String role) {
        this.studentID = new FXObjectProperty<Student>(studentID);
        this.thesisID = new FXObjectProperty<Thesis>(thesisID);
        this.role = new FXStringProperty(role);

    }

    // Property
    public FXStringProperty rolProperty() {
        return this.role;
    }

    public FXObjectProperty<Student> studentIDProperty() {
        return this.studentID;
    }

    public FXObjectProperty<Thesis> thesisIDProperty() {
        return this.thesisID;
    }

    // Setters and Getters
    public String getRole() {
        return rolProperty().get();
    }

    public void setRole(String role) {
        rolProperty().set(role);
    }

    public Student getStudentID() {
        return this.studentID.get();
    }

    public void setStudentID(Student studentID) {
        studentIDProperty().set(studentID);
    }

    public Thesis getThesisID() {
        return this.thesisID.get();
    }

    public void setThesisID(Thesis thesisID) {
        thesisIDProperty().set(thesisID);
    }

    @Override
    public String toString() {
        return "Thesis ID =" + getThesisID().getThesisID() + " Researcher = " + getStudentID().getStudentID();
    }

    @Override
    public FXModel clone() {
        Researcher researcher = new Researcher(getStudentID(), getThesisID(), getRole());
        return researcher;
    }

    @Override
    public void copy(FXModel arg0) {
        Researcher c = (Researcher) arg0;
        setStudentID(c.getStudentID());
        setThesisID(c.getThesisID());
    }

}
