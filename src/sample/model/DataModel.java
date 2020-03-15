package sample.model;

import com.opencsv.CSVReader;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataModel {

    private final ObjectProperty<Entity> currentCreature = new SimpleObjectProperty<>(null);
    private final ObjectProperty<Entity> currentPlayer = new SimpleObjectProperty<>(null);

    private ObservableList<Entity> creatureList = FXCollections.observableArrayList(creature -> new Observable[]{
            creature.nameProperty(),
            creature.levelProperty(),
            creature.acProperty(),
            creature.hpProperty(),
            creature.fortProperty(),
            creature.refProperty(),
            creature.willProperty(),
            creature.attackProperty()
    });

    private FilteredList<Entity> filteredCreature = new FilteredList<Entity>(creatureList, p -> true);

    public ObjectProperty<Entity> currentCreatureProperty() {
        return currentCreature;
    }

    public final Entity getCurrentCreature() {
        return currentCreatureProperty().get();
    }

    public final void setCurrentCreature(Entity creature) {
        currentCreatureProperty().set(creature);
    }

    public ObjectProperty<Entity> currentPlayerProperty() {
        return currentPlayer;
    }

    public final Entity getCurrentPlayer() {
        return currentPlayerProperty().get();
    }

    public final void setCurrentPlayer(Entity player){
        currentPlayerProperty().set(player);
    }

    public ObservableList<Entity> getCreatureList() {
        return creatureList;
    }

    public FilteredList<Entity> getFilteredCreatureList(){
        return filteredCreature;
    }

    public void loadData() throws Exception {

        try (
                Reader reader = Files.newBufferedReader(Paths.get("./lib/PF2BestiaryStats.csv"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading Records One by One in a String array
            String[] nextLine;
            int index = 0;
            while ((nextLine = csvReader.readNext()) != null) {
                if (index == 0) {
                    index++;
                    continue;
                }

                String name = nextLine[0];
                int level = Integer.parseInt(nextLine[1]);
                int ac = Integer.parseInt(nextLine[2]);
                int hp = Integer.parseInt(nextLine[9]);
                int fort = Integer.parseInt(nextLine[6]);
                int ref = Integer.parseInt(nextLine[7]);
                int will = Integer.parseInt(nextLine[8]);
                int attack = Integer.parseInt(nextLine[10]);

                creatureList.add(new Creature.EntityBuilder(name, level, ac, hp, fort, ref, will).withAttack(attack).build());
            }
        }

        /*currentCreature.addListener((obs, oldCreature, newCreature) -> {
            if(newCreature != null)
                calculateUpdate();
        });*/
    }

    /*public void calculateUpdate(){
        Calculator.calculate(this);
    }*/
}
