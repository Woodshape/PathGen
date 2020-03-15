package sample.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.controllers.Controller;
import sample.model.Creature;
import sample.model.DataModel;
import sample.model.Entity;

public class ToolbarController {

    @FXML
    private TextField searchField;
    @FXML
    private ChoiceBox choiceField;
    @FXML
    private Tooltip tooltip;
    @FXML
    private ImageView toolTipImage;

    @FXML
    private Button calculateButton;

    private DataModel model;

    private static final String TOOLTIP = "You can either search by name or by level.\n To search a level range, use a \"-\" separator.";

    public void initModel(DataModel model) {
        if (this.model != null)
            throw new IllegalStateException("Model can only be initialized once!");

        this.model = model;

        tooltip.setText(TOOLTIP);
        Tooltip.install(toolTipImage, tooltip);


        choiceField.getItems().addAll("Name", "Level");
        choiceField.setValue("Name");

        searchField.textProperty().addListener(
                (obs, oldCreature, newCreature) -> model.getFilteredCreatureList().setPredicate(
                        creature -> {
                            if (newCreature == null || newCreature.isEmpty())
                                return true;

                            if (choiceField.getValue().equals("Name")) {
                                String lowerCaseFilter = newCreature.toLowerCase();

                                return searchByName(creature, lowerCaseFilter);

                            } else if (choiceField.getValue().equals("Level"))
                                return searchByLevel(creature, newCreature);

                            return false;
                        }
                )
        );

        choiceField.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if(newVal != null){
                        searchField.setText("");
                        model.getFilteredCreatureList().setPredicate(null);
                    }
                });

        toolTipImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D p = toolTipImage.localToScene(0.0, 0.0);

                final Tooltip customTooltip = new Tooltip();
                customTooltip.setText(TOOLTIP);
        }});
    }

    private boolean searchByName(Entity creature, String name) {
        if ("Name".equals(choiceField.getValue())) {
            String lowerCaseFilter = name.toLowerCase();

            if (creature.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private boolean searchByLevel(Entity creature, String level) {
        try {
            int levelFilter = Integer.parseInt(level);
            if (creature.getLevel() == levelFilter) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            if (level.contains("-")) {
                try {
                    int a = Integer.parseInt(level.split("-")[0]);
                    int b = Integer.parseInt(level.split("-")[1]);

                    if (creature.getLevel() >= a && creature.getLevel() <= b) {
                        return true;
                    } else
                        return false;

                } catch (Exception ex) {
                    return false;
                }
            }
            return false;
        }
    }

    public void calculate(ActionEvent actionEvent) {
        Controller.getInstance().showResult();
    }
}
