package sample.model;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;

public abstract class Calculator {

    private static DataModel model;

    public static void calculate(DataModel mod, TextArea textField){

        model = mod;

        if(model.getCurrentCreature() == null){
            //TODO: Handle UI display
            System.out.println("You must select a creature from the left-hand list.");
        }else {

            System.out.printf("Player: %s (Level %d) against Creature: %s (Level %d) with AC %d\n",
                    model.getCurrentPlayer().getName(),
                    model.getCurrentPlayer().getLevel(),
                    model.getCurrentCreature().getName(),
                    model.getCurrentCreature().getLevel(),
                    model.getCurrentCreature().getAC());

            System.out.printf("Player Stats:\n" +
                    "Attack: %d\n" +
                    "Dice: %d\n" +
                    "Weapon Damage: %d\n" +
                    "Bonus Damage: %d\n",
                    model.getCurrentPlayer().getAttack(),
                    model.getCurrentPlayer().getDamageDice(),
                    model.getCurrentPlayer().getWeaponDamage(),
                    model.getCurrentPlayer().getDamageBonus());

            displayCalculationResult(textField);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void displayCalculationResult(TextArea textField) {
        String result = "CHARACTER (Level " + model.getCurrentPlayer().getLevel()  + "): \n";
        result += String.format("Expected chance to hit for %d attack against %s:%n", model.getCurrentPlayer().getAttack(), model.getCurrentCreature().getAC());

        // P = (21  + Atk - AC) / 20
        float chanceToHitMonster = calculateToHit(model.getCurrentPlayer().getAttack(), model.getCurrentCreature().getAC(), false);
        float chanceToCritMonster = calculateToHit(model.getCurrentPlayer().getAttack(), model.getCurrentCreature().getAC(), true);

        result += String.format("%s (CRIT: %s)%n", model.getCurrentCreature().getAC() - model.getCurrentPlayer().getAttack() > 30 ? "CANNOT HIT!" : chanceToHitMonster,
                chanceToCritMonster);

        float maxDamage = ((model.getCurrentPlayer().getDamageDice() * model.getCurrentPlayer().getWeaponDamage()) + model.getCurrentPlayer().getDamageBonus());

        result += "Damage to Monster: \n" + calculateDamage() + " (max "
                + maxDamage + ")";
        result += String.format(" dies in %s (max %s) rounds!%n", Math.round(model.getCurrentCreature().getHP() / calculateDamage()),
                Math.round((float)model.getCurrentCreature().getHP() / maxDamage));

        /*
        result += "\nMONSTER: \n";
        result += String.format("Expected chance to hit for %d attack against %s:%n", model.getCurrentCreature().getAttack(), model.getCurrentPlayer().getAC());

        float chanceToHitCharacter = calculateToHit(model.getCurrentCreature().getAttack(), model.getCurrentPlayer().getAC(), false);
        float chanceToCritCharacter = calculateToHit(model.getCurrentCreature().getAttack(), model.getCurrentPlayer().getAC(), true);

        result += String.format("%s (CRIT: %s)%n", model.getCurrentPlayer().getAC() - model.getCurrentCreature().getAttack() > 30 ? "CANNOT HIT!" : chanceToHitCharacter,
                chanceToCritCharacter);

        float damageToCharacter = (calculateToHit(this.monsterAttack, model.getCurrentPlayer().getAC(), false) * monsterDamage)
                + (calculateToHit(this.monsterAttack, model.getCurrentPlayer().getAC(), true) * monsterDamage);

        result += "Damage to Player: \n" + damageToCharacter + " (max " + ((monsterDamage * 1.5) - 0.5) + ")";
        result += String.format(" dies in %s (max %s) actions!%n", Math.round(model.getCurrentPlayer().getHP() / damageToCharacter),
                Math.round(model.getCurrentPlayer().getHP() / ((monsterDamage * 1.5) - 0.5)));

         */

        textField.setText(result);
    }

    // Chance to hit = (21 + attack bonus - target AC) / 20 (min. 5%, max. 95%)

    // Chance to crit = (11 + attack bonus - target AC) / 20 (min. 5%, max. 95%)

    //////////////////////////////////////////////////////////////////////////////////////
    public static float calculateToHit(int attack, int ac, boolean isCrit) {

        float value;

        if (!isCrit) value = (float) (21 + attack - ac) / 20;
        else value = (float) (11 + attack - ac) / 20;

        float clampedValue = (float) Math.max(0.05, Math.min(0.95, value));

        return clampedValue;
    }

    // Average die damage = (damage die size / 2) + 0.5

    // Average attack damage damage = (chance to hit * (((# of damage die * average die damage) + damage bonus) + (# of extra damage die * average extra die damage)))
    // + (chance to crit * (((# of damage die * average die damage) + damage bonus) + (# of extra damage die * average extra die damage))))

    //////////////////////////////////////////////////////////////////////////////////////
    public static float calculateDamage() {

        /*
        for (int i = 0; i < numberOfAttacks; i++) {
            penalty = Math.min(10, this.MAP * i);

            -> attack - penalty

        }*/

        float damage;
        float average = (model.getCurrentPlayer().getWeaponDamage() / 2) + 0.5f;

        damage = Math.max((
                calculateToHit(model.getCurrentPlayer().getAttack(), model.getCurrentCreature().getAC(), false)
                * ((model.getCurrentPlayer().getDamageDice() * average) + model.getCurrentPlayer().getDamageBonus())
                + (calculateToHit(model.getCurrentPlayer().getAttack(), model.getCurrentCreature().getAC(), true)
                * ((model.getCurrentPlayer().getDamageDice() * average) + model.getCurrentPlayer().getDamageBonus())
                )), 0);

        return damage;
    }
}
