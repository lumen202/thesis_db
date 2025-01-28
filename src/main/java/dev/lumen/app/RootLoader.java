package dev.lumen.app;

import dev.lumen.App;
import dev.sol.core.application.loader.FXLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RootLoader extends FXLoader {

    @Override
    public void load() {
        Stage stage = new Stage();
        stage.setTitle("Thesis Management");
        stage.setScene(new Scene(root));
        stage.setResizable(false);


        stage.show();

        RootController controller = loader.getController();
        App.CONTROLLER_REGISTRY.register("ROOT", controller);
        controller.load();

    }

}
