package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sample.model.Calculator;
import sample.model.DataModel;

public class ResultController {

    @FXML
    private TextArea textArea;

    private DataModel model;

    public void initModel(DataModel model) {
        if (this.model != null)
            throw new IllegalStateException("Model can only be initialized once");

        this.model = model;
    }

    public void showResult(){
        Calculator.calculate(model, textArea);
    }
}
