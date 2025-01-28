package dev.lumen.app.login;

import dev.lumen.App;
import dev.lumen.app.RootLoader;
import dev.lumen.models.Account;
import dev.sol.core.application.FXController;
import dev.sol.core.application.loader.FXLoaderFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends FXController {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    private ObservableList<Account> accountMasterList;

    String usernameInput;
    String passwordInput;

    @FXML
    private void createAccount() {
        CreateAccountLoader loader = (CreateAccountLoader) FXLoaderFactory
                .createInstance(CreateAccountLoader.class,
                        getClass().getResource("/dev/lumen/app/login/CREATE_ACCOUNT.fxml"))
                .initialize();
        loader.load();
    }

    @FXML
    private void handleLogin() {
        usernameInput = usernameField.getText();
        passwordInput = passwordField.getText();

        accountValidation();

    }

    @Override
    protected void load_bindings() {

        accountMasterList = App.COLLECTIONS_REGISTRY.getList("ACCOUNTS");
        for(int i = 0; i < accountMasterList.size(); i++){
            System.out.println("Acoount no."+(i+1) );
            System.out.println("Username : "+accountMasterList.get(i).getUsername());
            System.out.println("Passowrd: "+ accountMasterList.get(i).getPassword());
        }

    }

    @Override
    protected void load_fields() {

    }

    @Override
    protected void load_listeners() {

    }

    private void initialize_application() {

        RootLoader loader = (RootLoader) FXLoaderFactory
                .createInstance(RootLoader.class,
                        getClass().getResource("/dev/lumen/app/ROOT.fxml"))
                .initialize();
        loader.load();

    }

    private void closeCurrentStage() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    private void accountValidation() {

        // Check for empty fields first
        if (usernameInput.isEmpty() && passwordInput.isEmpty()) {
            showAlert("Input Error", "Username and password cannot be empty.");
            return;
        }
        if (usernameInput.isEmpty()) {
            showAlert("Input error", "Username is empty!!!");
            return;
        }
        if (passwordInput.isEmpty()) {
            showAlert("Input error", "Password is empty!!!");
            return;
        }

        // Find matching account
        Account matchedAccount = findAccountByUsername(usernameInput);

        if (matchedAccount == null) {
            showAlert("Error", "Username not found");
            return;
        }

        // Validate password
        if (!validatePassword(matchedAccount, passwordInput)) {
            showAlert("Error", "Incorrect password");
            return;
        }

        // If all validations pass
        initialize_application();
        closeCurrentStage();
    }

    private Account findAccountByUsername(String username) {
        return accountMasterList.stream()
                .filter(account -> account.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    private boolean validatePassword(Account account, String inputPassword) {
        return account.getPassword().equals(inputPassword);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
