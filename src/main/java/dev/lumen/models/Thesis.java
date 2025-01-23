package dev.lumen.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;


public class Thesis extends FXModel {


    private FXIntegerProperty thesisID;
    private FXStringProperty thesisTitle;
    private FXIntegerProperty year;
    private FXIntegerProperty month;
    private FXIntegerProperty degID;

    public Thesis(int thesisID, String thesisTitle, int year, int month, int degID) {
        this.thesisID = new FXIntegerProperty(thesisID);
        this.thesisTitle = new FXStringProperty(thesisTitle);
        this.year = new FXIntegerProperty(year);
        this.month = new FXIntegerProperty(month);
        this.degID = new FXIntegerProperty(degID);
    }

    // Property methods
    public FXIntegerProperty thesisIDProperty() {
        return thesisID;
    }

    public FXStringProperty thesisTitleProperty() {
        return thesisTitle;
    }

    public FXIntegerProperty yeaProperty() {
        return this.year;
    }

    public FXIntegerProperty monthProperty() {
        return month;
    }

    public FXIntegerProperty degIDProperty() {
        return degID;
    }

    // Getters and Setters
    public int getThesisID() {
        return thesisIDProperty().get();
    }

    public void setThesisID(int thesisID) {
        thesisIDProperty().set(thesisID);
    }

    public String getThesisTitle() {
        return thesisTitleProperty().get();
    }

    public void setThesisTitle(String thesisTitle) {
        thesisTitleProperty().set(thesisTitle);
    }

    public int getYear() {
        return yeaProperty().get();
    }

    public void setYear(int year) {
        yeaProperty().set(year);
    }

    public int getMonth() {
        return monthProperty().get();
    }

    public void setMonth(int month) {
        monthProperty().set(month);
    }

    public int getDegID() {
        return degIDProperty().get();
    }

    public void setDegID(int degID) {
        degIDProperty().set(degID);
    }

    // Clone method
    @Override
    public Thesis clone() {
        return new Thesis(getThesisID(), getThesisTitle(), getYear(), getMonth(), getDegID());
    }

    // Copy method
    @Override
    public void copy(FXModel arg0) {
        if (arg0 instanceof Thesis) {
            Thesis c = (Thesis) arg0;
            setThesisID(c.getThesisID());
            setThesisTitle(c.getThesisTitle());
            setYear(c.getYear());
            setMonth(c.getMonth());
            setDegID(c.getDegID());
        } else {
            throw new IllegalArgumentException("Argument must be an instance of Thesis");
        }
    }

}
