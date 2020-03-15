package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Creature extends Entity{

    private final StringProperty damage = new SimpleStringProperty();

    public Creature(EntityBuilder builder) {
        super(builder);
    }

    public StringProperty damageProperty() {
        return this.damage;
    }

    public String getDamage() {
        return this.damageProperty().get();
    }

    public final void setDamage(final String damage) {
        this.damageProperty().set(damage);
    }
}
