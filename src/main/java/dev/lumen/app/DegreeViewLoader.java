package dev.lumen.app;

import dev.lumen.App;
import dev.sol.core.application.loader.FXLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DegreeViewLoader extends FXLoader {

    @Override
    public void load() {
        Stage ownerStage = (Stage) params.get("OWNER_STAGE");
        Stage stage = new Stage();
        stage.setTitle("Degree Management");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initOwner(ownerStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();

        DegreeViewController controller = loader.getController();
        App.CONTROLLER_REGISTRY.register("DEGREEVIEW", controller);
        controller.load();

    }
}
