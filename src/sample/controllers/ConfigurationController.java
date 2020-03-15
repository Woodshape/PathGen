package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.model.DataModel;
import sample.model.Entity;
import sample.model.Player;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigurationController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField acField;
    @FXML
    private TextField hpField;
    @FXML
    private ChoiceBox levelChoiceField;

    @FXML
    public TextField damageDiceField;
    @FXML
    public ChoiceBox weaponDamageChoiceField;
    @FXML
    public TextField damageBonusField;
    @FXML
    public TextField attackField;


    private DataModel model;

    private static final int[] playerLevels = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    private static final int[] weaponDamage = {4, 6, 8, 10, 12};


    public void initModel(DataModel model) {
        if (this.model != null)
            throw new IllegalStateException("Model can only be initialized once!");

        this.model = model;

        model.currentPlayerProperty().addListener((obs, oldPlayer, newPlayer) -> {
            if (oldPlayer != null) {
                nameField.textProperty().unbindBidirectional(oldPlayer.nameProperty());
                levelChoiceField.itemsProperty().unbindBidirectional(oldPlayer.levelProperty());
                acField.textProperty().unbindBidirectional(oldPlayer.acProperty());
                hpField.textProperty().unbindBidirectional(oldPlayer.hpProperty());
                /*fortField.textProperty().unbindBidirectional(oldCreature.fortProperty());
                refField.textProperty().unbindBidirectional(oldCreature.refProperty());
                willField.textProperty().unbindBidirectional(oldCreature.willProperty());*/

                attackField.textProperty().unbindBidirectional(oldPlayer.attackProperty());
                damageDiceField.textProperty().unbindBidirectional(oldPlayer.attackProperty());
                weaponDamageChoiceField.itemsProperty().unbindBidirectional(oldPlayer.attackProperty());
                damageBonusField.textProperty().unbindBidirectional(oldPlayer.damageBonusProperty());
            }
            if (newPlayer == null) {
                nameField.setText("");
                levelChoiceField.setValue(1);
                acField.setText("");
                hpField.setText("");
                /*fortField.setText("");
                refField.setText("");
                willField.setText("");*/

                attackField.setText("");
                damageDiceField.setText("");
                weaponDamageChoiceField.setValue("d4");
                damageBonusField.setText("");

            } else {
                nameField.textProperty().bindBidirectional(newPlayer.nameProperty());
                levelChoiceField.valueProperty().bindBidirectional(newPlayer.levelProperty());
                acField.textProperty().bindBidirectional(newPlayer.acProperty(), NumberFormat.getNumberInstance());
                hpField.textProperty().bindBidirectional(newPlayer.hpProperty(), NumberFormat.getNumberInstance());
                /*fortField.textProperty().bindBidirectional(newCreature.fortProperty(), NumberFormat.getNumberInstance());
                refField.textProperty().bindBidirectional(newCreature.refProperty(), NumberFormat.getNumberInstance());
                willField.textProperty().bindBidirectional(newCreature.willProperty(), NumberFormat.getNumberInstance());*/

                attackField.textProperty().bindBidirectional(newPlayer.attackProperty(), NumberFormat.getNumberInstance());
                damageDiceField.textProperty().bindBidirectional(newPlayer.damageDiceProperty(), NumberFormat.getNumberInstance());
                weaponDamageChoiceField.valueProperty().bindBidirectional(newPlayer.weaponDamageProperty());
                damageBonusField.textProperty().bindBidirectional(newPlayer.damageBonusProperty(), NumberFormat.getNumberInstance());
            }
        });

        for (int i = 0; i < playerLevels.length; i++){
            levelChoiceField.getItems().add(playerLevels[i]);
        }
        levelChoiceField.setValue(playerLevels[0]);

        for (int i = 0; i < weaponDamage.length; i++){
            weaponDamageChoiceField.getItems().add(weaponDamage[i]);
        }
        weaponDamageChoiceField.setValue(weaponDamage[0]);

        model.setCurrentPlayer(new Player.EntityBuilder("", 1, 0, 0, 0, 0, 0)
                .withAttack(0)
                .withDamageDice(1)
                .withWeaponDamage(weaponDamage[0])
                .withDamageBonus(0)
                .build());

        System.out.println(model.getCurrentPlayer().getWeaponDamage());
        System.out.println(model.getCurrentPlayer().getWeaponDamage());
    }
}
