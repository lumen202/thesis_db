package dev.lumen.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;

public class Student extends FXModel {

    private FXIntegerProperty studentID;
    private FXStringProperty firstname;
    private FXStringProperty surname;
    private FXStringProperty middleInitial;
    private FXStringProperty fullname;

    public Student(int studentID, String firstname, String surname, String middleInitial) {
        this.studentID = new FXIntegerProperty(studentID);
        this.firstname = new FXStringProperty(firstname);
        this.surname = new FXStringProperty(surname);
        this.middleInitial = new FXStringProperty(middleInitial);
        this.fullname = new FXStringProperty(surname + ", " + firstname + " " + middleInitial);
    }

    // Property Methods
    public FXStringProperty fullnameProperty() {
        return this.fullname;
    }

    public FXIntegerProperty studentIDProperty() {
        return this.studentID;
    }

    public FXStringProperty firstnameProperty() {
        return this.firstname;
    }

    public FXStringProperty surnameProperty() {
        return this.surname;
    }

    public FXStringProperty middleInitialProperty() {
        return this.middleInitial;
    }

    // Getters and Setters

    public String getFullname() {
        return fullnameProperty().get();
    }

    public int getStudentID() {
        return studentIDProperty().get();
    }

    public void setStudentID(int studentID) {
        studentIDProperty().set(studentID);
    }

    public String getFirstname() {
        return firstnameProperty().get();
    }

    public void setFirstname(String firstname) {
        firstnameProperty().set(firstname);
    }

    public String getSurname() {
        return surnameProperty().get();
    }

    public void setSurname(String surname) {
        surnameProperty().set(surname);
    }

    public String getMiddleInitial() {
        return middleInitialProperty().get();
    }

    public void setMiddleInitial(String middleInitial) {
        middleInitialProperty().set(middleInitial);
    }

    // toString

    public String fullname() {
        return surnameProperty().get() + ", " + firstnameProperty().get() + " " + middleInitialProperty().get();
    }

    // Clone Method
    @Override
    public Student clone() {
        return new Student(getStudentID(), getFirstname(), getSurname(), getMiddleInitial());
    }

    // Copy Method
    @Override
    public void copy(FXModel arg0) {
        if (arg0 instanceof Student) {
            Student source = (Student) arg0;
            setStudentID(source.getStudentID());
            setFirstname(source.getFirstname());
            setSurname(source.getSurname());
            setMiddleInitial(source.getMiddleInitial());
        } else {
            throw new IllegalArgumentException("Argument must be an instance of Student");
        }
    }
}
