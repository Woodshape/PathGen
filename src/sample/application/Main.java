package sample.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controllers.Controller;
import sample.model.DataModel;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root = new GridPane();
        FXMLLoader startLoader = new FXMLLoader(getClass().getResource("/sample/views/Test.fxml"));
        root = startLoader.load();

        Controller controller = startLoader.getController();

        DataModel model = new DataModel();
        model.loadData();

        controller.initModel(model);

        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("PathGen");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
