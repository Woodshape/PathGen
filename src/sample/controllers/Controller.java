package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import sample.model.DataModel;

public class Controller {
    @FXML
    public Pane result;

    @FXML
    private ConfigurationController configurationController;
    @FXML
    private EditorController editorController;
    @FXML
    private ListController listController;
    @FXML
    private ToolbarController toolbarController;
    @FXML
    private ResultController resultController;

    private DataModel model;

    private static Controller instance;

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }

        return instance;
    }

    public void initModel(DataModel model) {
        if (this.model != null)
            throw new IllegalStateException("Model can only be initialized once");

        if (this.instance != null)
            throw new IllegalStateException("Instance can only be initialized once");

        //FIXME: Use proper Singleton
        instance = this;

        configurationController.initModel(model);
        editorController.initModel(model);
        listController.initModel(model);
        toolbarController.initModel(model);
        resultController.initModel(model);

        this.model = model;
    }

    public void showResult(){
        resultController.showResult();
    }
}
