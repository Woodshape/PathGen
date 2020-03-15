package sample.editor;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import sample.model.DataModel;

import java.text.NumberFormat;

public class EditorController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField levelField;
    @FXML
    private TextField acField;
    @FXML
    private TextField hpField;
    @FXML
    private TextField fortField;
    @FXML
    private TextField refField;
    @FXML
    private TextField willField;
    @FXML
    private TextField attackField;

    private DataModel model;

    public void initModel(DataModel model) {
        if (this.model != null)
            throw new IllegalStateException("Model can only be initialized once");

        this.model = model;

        model.currentCreatureProperty().addListener((obs, oldCreature, newCreature) -> {
            if (oldCreature != null) {
                nameField.textProperty().unbindBidirectional(oldCreature.nameProperty());
                levelField.textProperty().unbindBidirectional(oldCreature.levelProperty());
                acField.textProperty().unbindBidirectional(oldCreature.acProperty());
                hpField.textProperty().unbindBidirectional(oldCreature.hpProperty());
                fortField.textProperty().unbindBidirectional(oldCreature.fortProperty());
                refField.textProperty().unbindBidirectional(oldCreature.refProperty());
                willField.textProperty().unbindBidirectional(oldCreature.willProperty());
                attackField.textProperty().unbindBidirectional(oldCreature.attackProperty());
            }
            if (newCreature == null) {
                nameField.setText("");
                levelField.setText("");
                acField.setText("");
                hpField.setText("");
                fortField.setText("");
                refField.setText("");
                willField.setText("");
                attackField.setText("");
            } else {
                nameField.textProperty().bindBidirectional(newCreature.nameProperty());
                levelField.textProperty().bindBidirectional(newCreature.levelProperty(), NumberFormat.getNumberInstance());
                acField.textProperty().bindBidirectional(newCreature.acProperty(), NumberFormat.getNumberInstance());
                hpField.textProperty().bindBidirectional(newCreature.hpProperty(), NumberFormat.getNumberInstance());
                fortField.textProperty().bindBidirectional(newCreature.fortProperty(), NumberFormat.getNumberInstance());
                refField.textProperty().bindBidirectional(newCreature.refProperty(), NumberFormat.getNumberInstance());
                willField.textProperty().bindBidirectional(newCreature.willProperty(), NumberFormat.getNumberInstance());
                attackField.textProperty().bindBidirectional(newCreature.attackProperty(), NumberFormat.getNumberInstance());
            }
        });
    }
}
