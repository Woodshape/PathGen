package sample.model;

import javafx.beans.property.*;

public class Entity {

    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty level = new SimpleIntegerProperty();
    private final IntegerProperty ac = new SimpleIntegerProperty();
    private final IntegerProperty hp = new SimpleIntegerProperty();
    private final IntegerProperty fort = new SimpleIntegerProperty();
    private final IntegerProperty ref = new SimpleIntegerProperty();
    private final IntegerProperty will = new SimpleIntegerProperty();

    private final IntegerProperty attack = new SimpleIntegerProperty();
    private final IntegerProperty damageDice = new SimpleIntegerProperty();
    private final IntegerProperty weaponDamage = new SimpleIntegerProperty();
    private final IntegerProperty damageBonus = new SimpleIntegerProperty();

    /*
    private final IntegerProperty numberOfAttacks = new SimpleIntegerProperty();
    private final IntegerProperty MAP = new SimpleIntegerProperty();

    private final BooleanProperty critOnly = new SimpleBooleanProperty();

     */

    public Entity(EntityBuilder builder){
        setName(builder.name.get());
        setLevel(builder.level.get());
        setAC(builder.ac.get());
        setHP(builder.hp.get());
        setFort(builder.fort.get());
        setRef(builder.ref.get());
        setWill(builder.will.get());

        setAttack(builder.attack.get());
        setDamageDice(builder.damageDice.get());
        setWeaponDamage(builder.weaponDamage.get());
        setDamageBonus(builder.damageBonus.get());
    }

    public final StringProperty nameProperty() {
        return this.name;
    }

    public String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String name) {
        this.nameProperty().set(name);
    }

    public IntegerProperty levelProperty() {
        return this.level;
    }

    public int getLevel() {
        return this.levelProperty().get();
    }

    public final void setLevel(final int level) {
        this.levelProperty().set(level);
    }

    public IntegerProperty acProperty() {
        return this.ac;
    }

    public int getAC() {
        return this.acProperty().get();
    }

    public final void setAC(final int ac) {
        this.acProperty().set(ac);
    }

    public IntegerProperty hpProperty() {
        return this.hp;
    }

    public int getHP() {
        return this.hpProperty().get();
    }

    public final void setHP(final int hp) {
        this.hpProperty().set(hp);
    }

    public IntegerProperty fortProperty() {
        return this.fort;
    }

    public int getFort() {
        return this.fortProperty().get();
    }

    public final void setFort(final int fort) {
        this.fortProperty().set(fort);
    }

    public IntegerProperty refProperty() {
        return this.ref;
    }

    public int getRef() {
        return this.refProperty().get();
    }

    public final void setRef(final int ref) {
        this.refProperty().set(ref);
    }

    public IntegerProperty willProperty() {
        return this.will;
    }

    public int getWill() {
        return this.willProperty().get();
    }

    public final void setWill(final int will) {
        this.willProperty().set(will);
    }

    public IntegerProperty attackProperty() {
        return this.attack;
    }

    public int getAttack() {
        return this.attackProperty().get();
    }

    public final void setAttack(final int attack) {
        this.attackProperty().set(attack);
    }

    public IntegerProperty damageDiceProperty() {
        return this.damageDice;
    }

    public int getDamageDice() {
        return this.damageDiceProperty().get();
    }

    public final void setDamageDice(final int damageDice) {
        this.damageDiceProperty().set(damageDice);
    }

    public IntegerProperty weaponDamageProperty() {
        return this.weaponDamage;
    }

    public int getWeaponDamage() {
        return this.weaponDamageProperty().get();
    }

    public final void setWeaponDamage(final int weaponDamage) {
        this.weaponDamageProperty().set(weaponDamage);
    }

    public IntegerProperty damageBonusProperty() {
        return this.damageBonus;
    }

    public int getDamageBonus() {
        return this.damageBonusProperty().get();
    }

    public final void setDamageBonus(final int damageBonus) {
        this.damageBonusProperty().set(damageBonus);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class EntityBuilder{
        private final StringProperty name = new SimpleStringProperty();
        private final IntegerProperty level = new SimpleIntegerProperty();
        private final IntegerProperty ac = new SimpleIntegerProperty();
        private final IntegerProperty hp = new SimpleIntegerProperty();
        private final IntegerProperty fort = new SimpleIntegerProperty();
        private final IntegerProperty ref = new SimpleIntegerProperty();
        private final IntegerProperty will = new SimpleIntegerProperty();

        private final IntegerProperty attack = new SimpleIntegerProperty();
        private final IntegerProperty damageDice = new SimpleIntegerProperty();
        private final IntegerProperty weaponDamage = new SimpleIntegerProperty();
        private final IntegerProperty damageBonus = new SimpleIntegerProperty();

        private final IntegerProperty numberOfAttacks = new SimpleIntegerProperty();
        private final IntegerProperty MAP = new SimpleIntegerProperty();

        private final BooleanProperty critOnly = new SimpleBooleanProperty();

        private EntityBuilder(){}

        public EntityBuilder(String name, int level, int ac, int hp, int fort, int ref, int will) {
            setName(name);
            setLevel(level);
            setAC(ac);
            setHP(hp);
            setFort(fort);
            setRef(ref);
            setWill(will);
        }

        public EntityBuilder withAttack(int attack){
            setAttack(attack);
            return this;
        }

        public EntityBuilder withDamageDice(int dice){
            setDamageDice(dice);
            return this;
        }

        public EntityBuilder withWeaponDamage(int damage){
            setWeaponDamage(damage);
            return this;
        }

        public EntityBuilder withDamageBonus(int bonus){
            setDamageBonus(bonus);
            return this;
        }

        public Entity build(){
            return new Entity(this);
        }

        public final StringProperty nameProperty() {
            return this.name;
        }

        public final void setName(final String name) {
            this.nameProperty().set(name);
        }

        public IntegerProperty levelProperty() {
            return this.level;
        }

        public final void setLevel(final int level) {
            this.levelProperty().set(level);
        }

        public IntegerProperty acProperty() {
            return this.ac;
        }

        public final void setAC(final int ac) {
            this.acProperty().set(ac);
        }

        public IntegerProperty hpProperty() {
            return this.hp;
        }

        public final void setHP(final int hp) {
            this.hpProperty().set(hp);
        }

        public IntegerProperty fortProperty() {
            return this.fort;
        }

        public final void setFort(final int fort) {
            this.fortProperty().set(fort);
        }

        public IntegerProperty refProperty() {
            return this.ref;
        }

        public final void setRef(final int ref) {
            this.refProperty().set(ref);
        }

        public IntegerProperty willProperty() {
            return this.will;
        }

        public final void setWill(final int will) {
            this.willProperty().set(will);
        }

        public IntegerProperty attackProperty() {
            return this.attack;
        }

        public final void setAttack(final int attack) {
            this.attackProperty().set(attack);
        }

        public IntegerProperty damageDiceProperty() {
            return this.damageDice;
        }

        public final void setDamageDice(final int damageDice) {
            this.damageDiceProperty().set(damageDice);
        }

        public IntegerProperty weaponDamageProperty() {
            return this.weaponDamage;
        }

        public final void setWeaponDamage(final int weaponDamage) {
            this.weaponDamageProperty().set(weaponDamage);
        }

        public IntegerProperty damageBonusProperty() {
            return this.damageBonus;
        }

        public final void setDamageBonus(final int damageBonus) {
            this.damageBonusProperty().set(damageBonus);
        }
    }
}
