package org.puggu.magicandskills.ability.magic;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.Ability;
import org.puggu.magicandskills.ability.ReasonForCastFail;

public abstract class MagicSpell extends Ability {

    private final Double manaCost;
    protected MagicSpell(MagicAndSkills plugin, long cooldownTime, double manaCost) {
        super(plugin, cooldownTime, manaCost);
        this.manaCost = manaCost;
    }

    public Double getManaCost() {
        return manaCost;
    }


    @Override
    public void failedToCast(Player player, ReasonForCastFail reason){
        switch (reason){
            case COOLDOWN:
                player.playSound(player, Sound.BLOCK_ANVIL_LAND, .5f, 1f);
                break;
            case NOT_ENOUGH_ENERGY:
                player.playSound(player, Sound.ITEM_SHIELD_BREAK, .5f, 1f);
                break;
        }
    }

    @Override
    public boolean ability(){
        return false;
    }
    @Override
    public void depleteResource(Player player, Double amount) {
        if (playerEnergyManager.playerHasEnergyContainers(player)){
            playerEnergyManager.incrementPlayerMana(player, -amount);
        }
    }

    @Override
    public boolean enoughResource(Player player, Double cost) {
        double availableMana = playerEnergyManager.getPlayerMana(player);
        return availableMana > cost;
    }
}
