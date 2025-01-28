package dev.lumen.app.login;

import dev.lumen.App;
import dev.lumen.data.AccountDAO;
import dev.lumen.models.Account;
import dev.sol.core.application.FXController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

public class CreateAccountController extends FXController {

    @FXML
    TextField createUsername;
    @FXML
    TextField createPassword;

    ObservableList<Account> accountMasterList;

    @FXML
    private void handleCreateAccount() {
        String username = createUsername.getText().trim();
        String password = createPassword.getText().trim();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Input Error", "Username and password cannot be empty.");
            return;
        }

        // Check if username is already taken
        boolean usernameExists = accountMasterList.stream()
                .anyMatch(account -> account.getUsername().equalsIgnoreCase(username));

        if (usernameExists) {
            showAlert("Registration Error", "Username is already in use. Please choose another.");
            return;
        }

        // Create and insert new account
        Account newAccount = new Account(username, password);
        try {

            accountMasterList.add(newAccount);
            AccountDAO.insert(newAccount);
            closeCurrentStage();
        } catch (Exception e) {
            showAlert("Registration Error", "Failed to create account. Please try again.");
        }
    }

    @FXML
    private void handleCancleCreateAccount() {
        closeCurrentStage();
    }

    @Override
    protected void load_bindings() {
        accountMasterList = App.COLLECTIONS_REGISTRY.getList("ACCOUNTS");
    }

    @Override
    protected void load_fields() {
    }

    @Override
    protected void load_listeners() {
    }

    private void closeCurrentStage() {
        Stage stage = (Stage) createUsername.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
