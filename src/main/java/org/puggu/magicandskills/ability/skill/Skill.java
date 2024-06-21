package org.puggu.magicandskills.ability.skill;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.Ability;
import org.puggu.magicandskills.ability.ReasonForCastFail;

public abstract class Skill extends Ability {
    private final Double staminaCost;
    protected Skill(MagicAndSkills plugin, long cooldownTime, double cost) {
        super(plugin, cooldownTime, cost);
        this.staminaCost = cost;
    }

    @Override
    public void depleteResource(Player player, Double amount) {
        if (playerEnergyManager.playerHasEnergyContainers(player)){
            playerEnergyManager.incrementPlayerStamina(player, -amount);
        }
    }

    @Override
    public boolean enoughResource(Player player, Double cost) {
        double availableStamina = playerEnergyManager.getPlayerStamina(player);
        return availableStamina > cost;
    }

    @Override
    public void failedToCast(Player player, ReasonForCastFail reason) {
        switch (reason){
            case COOLDOWN:
                player.playSound(player, Sound.BLOCK_ANVIL_LAND, .5f, 1f);
                break;
            case NOT_ENOUGH_ENERGY:
                player.playSound(player, Sound.ITEM_SHIELD_BREAK, .5f, 1f);
                break;
        }
    }

    public Double getStaminaCost() {
        return staminaCost;
    }
}
