package dev.lumen;

import dev.lumen.app.RootLoader;
import dev.lumen.data.DegreeDAO;
import dev.lumen.data.ResearcherDAO;
import dev.lumen.data.StudentDAO;
import dev.lumen.data.ThesisDAO;
import dev.sol.core.application.FXApplication;
import dev.sol.core.application.loader.FXLoaderFactory;
import dev.sol.core.registry.FXCollectionsRegister;
import dev.sol.core.registry.FXControllerRegister;
import dev.sol.core.registry.FXNodeRegister;
import dev.sol.core.scene.FXSkin;
import dev.sol.db.DBService;
import javafx.collections.FXCollections;

public class App extends FXApplication {

        public static final FXControllerRegister CONTROLLER_REGISTRY = FXControllerRegister.INSTANCE;
        public static final FXCollectionsRegister COLLECTIONS_REGISTRY = FXCollectionsRegister.INSTANCE;
        public static final FXNodeRegister NODE_REGISTER = FXNodeRegister.INSTANCE;

        public static final DBService DB_THESIS = DBService.INSTANCE
                        .initialize("jdbc:mysql://localhost/thesis_db?user=root&password=");

        @Override
        public void initialize() throws Exception {
                setTitle("Thesis Management System");
                setSkin(FXSkin.PRIMER_LIGHT);
                applicationStage.setResizable(false);
                applicationStage.getOnCloseRequest();

                initialize_dataset();
                initialize_application();

        }

        public void initialize_dataset() {

                COLLECTIONS_REGISTRY.register("STUDENT",
                                FXCollections.observableArrayList(StudentDAO.getStudentList()));
                COLLECTIONS_REGISTRY.register("THESIS",
                                FXCollections.observableArrayList(ThesisDAO.getThesisList()));
                COLLECTIONS_REGISTRY.register("RESEARCHER",
                                FXCollections.observableArrayList(ResearcherDAO.getResearcherList()));
                COLLECTIONS_REGISTRY.register("DEGREE",
                                FXCollections.observableArrayList(DegreeDAO.getDegreeList()));

        }

        public void initialize_application() {

                RootLoader rootLoader = (RootLoader) FXLoaderFactory
                                .createInstance(RootLoader.class, App.class.getResource("/dev/lumen/app/ROOT.fxml"))
                                .addParameter("scene", applicationScene)
                                .addParameter("OWNER", applicationStage)
                                .initialize();
                rootLoader.load();

        }

}