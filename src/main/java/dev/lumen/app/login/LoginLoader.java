package dev.lumen.app.login;

import dev.lumen.App;
import dev.sol.core.application.loader.FXLoader;
import javafx.scene.Scene;

public class LoginLoader extends FXLoader {

    @Override
    public void load() {
        Scene scene = (Scene) params.get("scene");
        scene.setRoot(root);

        LoginController controller = loader.getController();
        App.CONTROLLER_REGISTRY.register("ROOT", controller);
        controller.addParameter("SCENE", scene)
                .addParameter("OWNER", params.get("OWNER"))
                .load();
    }

}
