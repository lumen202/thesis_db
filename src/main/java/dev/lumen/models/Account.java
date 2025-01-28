package dev.lumen.models;

import dev.sol.core.properties.beans.FXStringProperty;

public class Account {

    private FXStringProperty username;
    private FXStringProperty password;

    public Account(String username, String password) {
        this.username = new FXStringProperty(username);
        this.password = new FXStringProperty(password);
    }

    public FXStringProperty usernameProperty() {
        return this.username;
    }

    public FXStringProperty passwordProperty() {
        return this.password;
    }

    public String getUsername() {
        return usernameProperty().get();
    }

    public String getPassword() {
        return passwordProperty().get();
    }

    public void setUsername(String username) {
        passwordProperty().set(username);
    }

    public void setPassword(String password) {
        passwordProperty().set(password);
    }

}
