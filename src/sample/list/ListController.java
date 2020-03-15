package sample.list;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import sample.model.Creature;
import sample.model.DataModel;
import sample.model.Entity;

public class ListController {

    @FXML
    private ListView<Entity> listView;

    private DataModel model;

    public void initModel(DataModel model) {
        if (this.model != null)
            throw new IllegalStateException("Model can only be initialized once!");

        this.model = model;
        listView.setItems(model.getFilteredCreatureList());

        listView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> model.setCurrentCreature(newSelection)
        );

        model.currentCreatureProperty().addListener((obs, oldCreature, newCreature) -> {
            if (newCreature == null) {
                listView.getSelectionModel().clearSelection();
            } else {
                listView.getSelectionModel().select(newCreature);
            }
        });

        listView.setCellFactory(lv -> new ListCell<Entity>() {
            @Override
            public void updateItem(Entity creature, boolean empty) {
                super.updateItem(creature, empty);
                if (empty) {
                    setText(null);
                    setStyle(null);
                } else {
                    setText(creature.getName());// + " " + creature.getLevel());
                    setStyle(getIndex() % 2 == 0 ? "-fx-background-color: #9e579d" : "-fx-background-color: #574b90");
                }
            }
        });
    }
}
