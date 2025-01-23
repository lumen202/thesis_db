package dev.lumen.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;

public class Degree extends FXModel {



    private FXIntegerProperty degreeID;
    private FXStringProperty degree;

    public Degree(int degreeID, String degree) {
        this.degreeID = new FXIntegerProperty(degreeID);
        this.degree = new FXStringProperty(degree);
    }

    // Property
    public FXIntegerProperty degreeIDProperty() {
        return this.degreeID;
    }

    public FXStringProperty degreeProperty() {
        return this.degree;
    }

    // Getters and Setters
    public int getDegreeID() {
        return this.degreeIDProperty().get();
    }

    public void setDegreeID(int degreeID) {
        degreeIDProperty().set(degreeID);
    }

    public String getDegree() {
        return this.degreeProperty().get();
    }

    public void setDegree(String degree) {
        degreeProperty().set(degree);
    }

    public static String getDegreeField(int degreeID) {

        switch (degreeID) {
            case 1:
                return "Bachelor of Science in  Computer Science";

            case 2:
                return "Master in Information Technology";

            case 3:
                return "Bachelor of Science in Information Technology";

            case 4:
                return "Bachelor of Science in Agricultural Engineering";

            case 5:
                return "Master of Applied Science in Computer Studies";

            default:
                return null;

        }

    }

    @Override
    public FXModel clone() {
        Degree degree = new Degree(getDegreeID(), getDegree());
        return degree;
    }

    @Override
    public void copy(FXModel arg0) {
        Degree c = (Degree) arg0;
        setDegree(c.getDegree());
        setDegreeID(c.getDegreeID());
    }

}
